<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<hr/>
<div class="col-md-11">
<div class="panel panel-default">
    <div class="panel-heading">Leave Comment</div>
    <div class="panel-body">
        <form class="comment-form" id="commentForm">
            <div class="col-md-11">
                <textarea required="required" name="statement" class="form-control" rows="3" > </textarea>
            </div>
            <div class="clearfix"></div>
            <div class="col-md-10 comment-submit-btn">
                <p>There are <span id="commentCounter">${commentCount}</span> Comment/s </p>
                <div id="ajaxResultStatus" class="hidden">
                    <span ><i width="30px" class='fa fa-spinner fa-spin '></i></span>
                </div>
                <span class="commentMessage">${commentMessage}</span>
            </div>
            <div class="col-md-1 comment-submit-btn">
                <input type="hidden" name="componentType" value="${componentType}" />
                <input type="hidden" name="componentId" value="${componentId}" />
                <input type="hidden" id="csrfToken" value="${_csrf.token}"/>
                <input type="hidden" id="csrfHeader" value="${_csrf.headerName}"/>
                <button class="btn btn-xs btn-primary" type="submit" onclick="makeAjaxCall(); return false;" >Submit</button>
            </div>
            <div class="clearfix"></div>
        </form>
    </div>
</div>
</div>
<div class="clearfix"></div>

<%--<form:form class="comment-form" modelAttribute="newComment" action="/comment/add" method="POST">--%>
    <%--<div class="col-md-11">--%>
        <%--<form:textarea path="statement" class="form-control" rows="3" />--%>
    <%--</div>--%>
    <%--<div class="col-md-1 comment-submit-btn">--%>
        <%--<form:hidden path="componentType" value="${componentType}" />--%>
        <%--<form:hidden path="componentId" value="${componentId}" />--%>
        <%--<button class="btn btn-xs btn-primary" type="submit" >Submit</button>--%>
    <%--</div>--%>
    <%--<div class="clearfix"></div>--%>
<%--</form:form>--%>

<div class="col-md-11 comment-list-wrap">

    <c:forEach items="${commentList}" var="comment" >
        <div class="media">
            <div class="media-left">
                <a href="#">
                    <img width="34px" src='<c:url value="/static/img/avatar.png" />' class="media-object" alt="${comment.proposer.firstName}">
                </a>
            </div>
            <div class="media-body">
                <h4 class="media-heading">${comment.proposer.firstName} ${comment.proposer.lastName}</h4>
                    ${comment.statement}
                <br/>
                <p class="comment-date"><fmt:formatDate pattern = "MM-dd-yyyy" value = "${comment.dateCommented}" /></p>
                <c:if test="${comment.proposer.id eq userDetail.id}">
                    <a href="#">Edit</a>
                    <a href="/comment/del/${comment.proposer.id}/${comment.id}">Delete</a>
                </c:if>
            </div>
        </div>
        <hr/>
    </c:forEach>
</div>
<div class="clearfix"></div>

<script>
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
    function makeAjaxCall(){
        $("#ajaxResultStatus").removeClass("hidden").show();
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
                    $("#ajaxResultStatus").hide();
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
                $("#ajaxResultStatus").hide();
//                $('#errors').html("");
                console.log("success...");

                console.log(response);
                console.log(response.commentId);
                resetForm('commentForm');
            },
            error : function(errorObject) {
                console.log("Error...");
                console.log(errorObject);
//                $('.commentMessage').text("Opps.."+ errorObject.Error).fadeOut(5000);
            }
        });
    };
</script>