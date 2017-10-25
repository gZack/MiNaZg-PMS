<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<hr/>
<div class="">
<div class="panel panel-default">
    <div class="panel-heading">Leave Comment</div>
    <div class="panel-body">
        <form class="comment-form" id="commentForm">
            <div class="col-md-11">
                <textarea required="required" name="statement" class="form-control" rows="3" > </textarea>
            </div>
            <div class="clearfix"></div>
            <div class="col-md-10 comment-submit-btn">
                <p>There are <span id="commentCounter">${commentCount}</span> Comment(s) </p>
                <div id="ajaxSpinner" class="hidden">
                    <span ><i width="30px" class='fa fa-spinner fa-spin '></i></span>
                </div>
                <span class="commentMessage">${commentMessage}</span>
            </div>
            <div class="col-md-1 comment-submit-btn">
                <input type="hidden" name="componentType" value="${componentType}" />
                <input type="hidden" name="componentId" value="${componentId}" />
                <input type="hidden" id="csrfToken" value="${_csrf.token}"/>
                <input type="hidden" id="csrfHeader" value="${_csrf.headerName}"/>
                <button class="btn btn-xs btn-primary" type="submit" onclick="submitCommentAjax(); return false;" >Submit</button>
            </div>
            <div class="clearfix"></div>
        </form>
    </div>
</div>
</div>
<div class="clearfix"></div>

<div class="comment-list-wrap">
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
                    <%--<a href="#">Edit</a>--%>
                    <%--<a href="/comment/del/${comment.proposer.id}/${comment.id}">Delete</a>--%>
                </c:if>
            </div>
        </div>
        <hr/>
    </c:forEach>
</div>
<div class="clearfix"></div>
