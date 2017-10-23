<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="card-header">
    <div class="well lead">Task List</div>
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

<div class="form-actions floatRight">

    <a href="<c:url value='/task/add'/>">Create Task</a>

</div>