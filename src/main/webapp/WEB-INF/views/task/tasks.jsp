<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="panel panel-default">

    <div class="panel-heading">
        Tasks
    </div>

    <div class="panel-body">
        <div class="project-search-form">
            <c:url value="/task/add" var="addTaskUrl">
                <c:if test="${fn:length(param['sprintId']) > 0}">
                    <c:param name="sprintId" value="${param['sprintId']}"/>
                </c:if>
            </c:url>
            <a href="<c:url value='${addTaskUrl}'/>" class="col-md-1 btn btn-warning pull-right">Add New</a>

            <c:url value="/task/list/search" var="taskSearchUrl">
                <c:if test="${fn:length(param['sprintId']) > 0}">
                    <c:param name="sprintId" value="${param['sprintId']}"/>
                </c:if>
            </c:url>
            <form class="col-md-10" method="GET" action="/task/list/search" >
                <div class="col-md-4">
                    <div class="input-group">
                        <c:if test="${fn:length(param['sprintId']) > 0}">
                            <input type="hidden" name="sprintId" value="${param['sprintId']}"/>
                        </c:if>
                        <input class="form-control" name="q" type="text" value="${param['q']}"
                               placeholder="title or type or status" />
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

    <c:if test="${pages > 0}">
        <div class="panel-body">
            <div class="pull-right">
                <nav aria-label="pager">
                    <ul class="pager">
                        <c:if test="${prevPage > 0}">
                            <c:url value="/task/list" var="prevPage">
                                <c:param name="page" value="${prevPage - 1}"/>
                                <c:param name="size" value="${pageSize}"/>
                            </c:url>
                            <li><a href="${prevPage}">Previous</a></li>
                        </c:if>

                        <c:if test="${nextPage <= pages - 1}">
                            <c:url value="/task/list" var="nextPage">
                                <c:param name="page" value="${nextPage}"/>
                                <c:param name="size" value="${pageSize}"/>
                            </c:url>
                            <li><a href="${nextPage}">Next</a></li>
                        </c:if>
                    </ul>
                </nav>
            </div>
        </div>
    </c:if>

</div>



