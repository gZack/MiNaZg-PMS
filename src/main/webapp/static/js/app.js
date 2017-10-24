var contextRoot = "/" + window.location.pathname.split('/')[1];
resetForm = function(id) {
    var element = document.getElementById(id);
    $(element)[0].reset();
}

function formatDate(date) {
    var monthNames = [
        "January", "February", "March",
        "April", "May", "June", "July",
        "August", "September", "October",
        "November", "December"
    ];

    var day = date.getDate();
    var monthIndex = date.getMonth();
    var year = date.getFullYear();

    return day + ' ' + monthNames[monthIndex] + ' ' + year;
}

// Translate form to array
// Then put in JSON format
function serializeObject (form)
{
    var jsonObject = {};
    var array = form.serializeArray();
    $.each(array, function() {
        jsonObject[this.name] = this.value;
    });
    return jsonObject;

};

function submitCommentAjax(){
    $("#ajaxSpinner").removeClass("hidden").show();
    setTimeout( () => {} , 3000);
    var dataToSend = JSON.stringify(serializeObject($('#commentForm')));
    var token = $('#csrfToken').val();
    var header = $('#csrfHeader').val();
    $.ajax({
        type : 'POST',
        url : '/api/comment/add',
        dataType : "json", // Accept header
        data : dataToSend,
        contentType : 'application/json', // Sends - Content-type
        beforeSend: function(xhr) {
            xhr.setRequestHeader("Accept", "application/json");
            xhr.setRequestHeader("Content-Type", "application/json");
            xhr.setRequestHeader(header, token);
        },
        success : function(response) {
            if(response.Error){
                alert("error");
                $('.commentMessage').text("Opps.."+ response.Error).fadeOut(3000);
                $("#ajaxSpinner").hide();
                return false;
            }
            $('.comment-list-wrap').prepend(`
                    <div class="media">
                        <div class="media-left">
                            <a href="#">
                                <img width="34px" src="/static/img/avatar.png" class="media-object" alt="minazg">
                            </a>
                        </div>
                        <div class="media-body">
                            <h4 class="media-heading">`+ response.commentFirstName +`</h4>
                                 ` + response.commentBody + `
                            <br>
                            <p class="comment-date">`+ formatDate(new Date(response.commentDate)) +`</p>

                                <a href="#">Edit</a>
                                <a href="/comment/del/`+ response.commentUserId +`/`+ response.commentId +`">Delete</a>
                        </div>
                    </div>`);
            var counter = parseInt($("#commentCounter").text()) + 1;
            $("#commentCounter").text(counter);
            $("#ajaxSpinner").hide();
            console.log("success...");
            $('.commentMessage').html("");
            resetForm('commentForm');
        },
        error : function(errorObject) {
            console.log("Error...");
            if (errorObject.responseJSON.errorType == "ValidationError") {
                $("#ajaxSpinner").hide();
                $('.commentMessage').html("");
                $(".commentMessage").append('<p>');

                var errorList = errorObject.responseJSON.errors;
                $.each(errorList, function(i, error) {
                    $(".commentMessage").append(error.message + '<br>');
                });
                $(".commentMessage").append('</p>');
            } else {
                console.log(errorObject.responseJSON.errors(0)); // "non" Validation Error
            }
        }
    });
};