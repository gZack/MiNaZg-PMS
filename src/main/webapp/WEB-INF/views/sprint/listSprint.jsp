
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="panel panel-default">
    <div class="panel-heading">
        <b>Version ${versionNumber} list of Sprint </b>
    </div>
    <c:if test="${not empty flashMessage}" >
        <div class="alert alert-success">
            <strong>Success</strong> ${flashMessage}
        </div>
    </c:if>
    <div class="panel-body">
        <div class="project-search-form">



            <a href="/sprint/add/${releaseId}" class="col-md-2 btn btn-warning pull-right">Add New Sprint</a>
            <form  method="GET" action="/sprint/search" >
                <div class="col-md-4">
                    <div class="input-group">
                        <input type="hidden" name="releaseId" id="releaseId" value="${releaseId}">
                        <input class="form-control" placeholder="Sprint Title" required name="sprintTitle" type="text" value="${sprintTitle}"/>
                        <span class="input-group-btn">
                    <button type="submit" class="btn btn-default" type="button">Search</button>
                   <a href=="/sprint/add?releaseId=${releaseId}" class="btn btn-default">Reset</a>


                        </span>

                    </div>
                </div>
        </div>
        <div class="clearfix"></div>
        </form>
    </div>

    <c:if test="${empty sprints}">
        No Result Found.
    </c:if>
    <c:if test="${not empty sprints}">
        <table class="table">
            <thead>
            <tr>

                <th>Title</th>
                <th>Description</th>
                <th>Task</th>
                <th>Status</th>
                <th>Edit</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="sprint" items="${sprints}">
                <tr>

                    <td>
                        <c:url value="/sprint/detail/${sprint.id}" var="sprintViewUrl"/>
                        <a href="<c:out value='${sprintViewUrl}'/>">${sprint.title}</a></td>
                    <td>${sprint.description}</td>

                    <td>
                        <c:url value="/task/list" var="workOrderViewUrl">
                            <c:param name="sprintId" value="${sprint.id}"/>
                        </c:url>
                        <a class="fa fa-list" href="<c:out value='${workOrderViewUrl}'/>"></a>
                        &nbsp;&nbsp;
                        <c:url value="/task/add" var="workOrderAddUrl">
                            <c:param name="sprintId" value="${sprint.id}"/>
                        </c:url>
                        <a class="fa fa-plus" href="<c:out value='${workOrderAddUrl}'/>"></a>
                    </td>
                    <td>${sprint.status}</td>
                    <td>
                        <c:url value="/sprint/edit/${sprint.id}" var="editUrl"/>
                        <a href="<c:out value='${editUrl}'/>" class="fa fa-edit"></a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>

</div>
