<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<hr/>
<h3>Leave Comment</h3>
<span class="commentMessage">${commentMessage}</span>
<form:form class="comment-form" modelAttribute="newComment" action="/comment/add" method="POST">
    <div class="col-md-11">
    <form:textarea path="statement" class="form-control" rows="3" />
    </div>
    <div class="col-md-1 comment-submit-btn">
    <form:hidden path="componentType" value="${componentType}" />
    <form:hidden path="componentId" value="${componentId}" />
    <button class="btn btn-xs btn-primary" type="submit" >Submit</button>
    </div>
    <div class="clearfix"></div>
</form:form>
<div class="col-md-11 comment-list-wrap">
    <p>There are ${commentCount} Comments </p>
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
                    Edit
                    <%--<a href="/comment/del/${comment.proposer.id}/${comment.id}">Delete</a>--%>
                </c:if>
            </div>
        </div>
        <hr/>
    </c:forEach>
</div>
<div class="clearfix"></div>