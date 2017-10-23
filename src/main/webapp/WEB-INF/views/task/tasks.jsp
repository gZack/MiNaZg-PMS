<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="card-header">
    <div class="well lead">Tasks</div>
</div>

<div class="project-search-form">
    <a href="<c:url value='/task/add'/>" class="col-md-1 btn btn-warning">Add New</a>
    <form class="col-md-10" method="GET" action="/project/list" >
        <div class="col-md-4">
            <div class="input-group">
                <input class="form-control" placeholder="filter by title" required name="q" tupe="text" value="${q}"/>
                <span class="input-group-btn">
                        <button type="submit" class="btn btn-default" type="button">Go!</button>
                      </span>
            </div>
        </div>
        <div class="clearfix"></div>
    </form>
    <div class="clearfix"></div>
</div>

<table class="table">
    <thead>
    <tr>
        <th scope="col">Title</th>
        <th scope="col">Type</th>
        <th scope="col">Status</th>
        <th scope="col">Start Date</th>
        <th scope="col">End Date</th>
        <th scope="col" width="100"></th>
        </tr>
    </thead>
    <tbody>
    <c:forEach items="${tasks}" var="task">
        <tr>
            <td>${task.title}</td>
            <td>${task.type}</td>
            <td>${task.status}</td>
            <td><fmt:formatDate value="${task.startDate}" pattern="MM-dd-yyyy"/> </td>
            <td><fmt:formatDate value="${task.deadLine}" pattern="MM-dd-yyyy"/></td>
            <td>
                <a href="<c:url value='/task/edit/${task.id}' />" class="fa fa-edit"/>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>