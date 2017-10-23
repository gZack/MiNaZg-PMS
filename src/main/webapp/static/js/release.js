
function makeAjaxCall(){
    var sendToSend = JSON.stringify(serializeObject($('#searchReleaseForm')));
    var contextRoot = "/" + window.location.pathname.split( '/' )[1];

    $.ajax({
        url: contextRoot + '/release/search',
        type: 'POST',
        dataType: "json",
        data:sendToSend,
        contentType: 'application/json',
        success: function(release){
            $('#formInput').html("");
            $("#formInput").append( '<H3 align="center"> New Employee Information <H3>');
            $('#formInput').show();
            $('#errors').hide();
        },

        error: function(jqXHR,  textStatus,  exception ){

            if (jqXHR.responseJSON.errorType == "ValidationError") {
                $('#errors').html("");
                $("#errors").append( '<H3 align="center"> Error(s)!! <H3>');
                $("#errors").append( '<p>');

                var errorList = jqXHR.responseJSON.errors;
                $.each(errorList,  function(i,error) {
                    $("#errors").append( error.message + '<br>');
                });
                $("#errors").append( '</p>');
                $('#errors').show();
            }
            else {
                alert(jqXHR.responseJSON.message);
            }
        }

    });
}

toggle_visibility = function(id) {
    var e = document.getElementById(id);
    if(e.style.display == 'block')
        e.style.display = 'none';
    else
        e.style.display = 'block';
}

make_hidden = function(id) {
    var e = document.getElementById(id);
    e.style.display = 'none';
}

make_visible = function(id) {
    var e = document.getElementById(id);
    e.style.display = 'block';
}

resetForm = function(id) {
    var e = document.getElementById(id);
    $(e)[0].reset();

}

function serializeObject (form)
{
    var jsonObject = {};
    var array = form.serializeArray();
    $.each(array, function() {
        jsonObject[this.name] = this.value;
    });
    return jsonObject;

};

