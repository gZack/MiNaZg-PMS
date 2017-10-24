<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

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