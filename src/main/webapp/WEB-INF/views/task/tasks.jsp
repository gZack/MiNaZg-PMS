<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="panel panel-default">

    <div class="panel-heading">
        Tasks
    </div>

    <div class="panel-body">
        <div class="project-search-form">
            <a href="<c:url value='/task/add'/>" class="col-md-1 btn btn-warning pull-right">Add New</a>
            <form class="col-md-10" method="GET" action="/task/list" >
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
    </div>

    <table class="table">
        <thead>
        <tr>
            <th scope="col">Title</th>
            <th scope="col">Type</th>
            <th scope="col">Status</th>
            <th scope="col">Start Date</th>
            <th scope="col">Deadline</th>
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
                    <c:url value="/task/edit" var="editTaskUrl">
                        <c:param name="taskId" value="${task.id}"/>
                        <c:if test="${param['sprintId'].length() > 0}">
                            <c:param name="sprintId" value="${param['sprintId']}"/>
                        </c:if>
                    </c:url>
                    <a href="<c:url value='${editTaskUrl}'/>" class="fa fa-edit"/>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>



