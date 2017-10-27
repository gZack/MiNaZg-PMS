<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="panel panel-default">

    <div class="panel-heading">
        Projects
    </div>

    <div class="panel-body">
        <c:if test="${not empty flashMessage}" >
            <div class="alert alert-success">
                <strong>Success!</strong> ${flashMessage}
            </div>
        </c:if>
        <div class="project-search-form">
            <a href='<c:url value="/project/add" />' class="col-md-1 btn btn-warning pull-right">Add New</a>
            <form class="col-md-10" method="GET" action='<c:url value="/project/list" />' >
                <div class="col-md-4">
                    <div class="input-group">
                        <input class="form-control" placeholder="Filter by Project Name" required name="q" tupe="text" value="${q}"/>
                        <span class="input-group-btn">
                        <button type="submit" class="btn btn-default" type="button">Go!</button>
                        </span>
                    </div>
                </div>
                <div class="col-md-1">
                    <a href='<c:url value="/project" />' class="btn btn-warning" type="button">Reset</a>
                </div>
                <div class="clearfix"></div>
            </form>
            <div class="clearfix"></div>
        </div>
    </div>
    <c:if test="${empty projects}">
        <div class="table padd">No Result Found.</div>
    </c:if>
    <c:if test="${not empty projects}">
        <table class="table">
            <thead>
            <tr>
                <th>Name</th>
                <th>Release</th>
                <th>Status</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="project" items="${projects}">
                <tr>
                    <td>
                        <c:url value="/project/detail/${project.id}" var="projectViewUrl"/>
                        <a href="<c:out value='${projectViewUrl}'/>">${project.name}</a></td>
                    <td>
                        <c:url value="/release/list?projectId=${project.id}" var="releaseViewUrl"/>
                        <a class="fa fa-list" href="<c:out value='${releaseViewUrl}'/>"></a>
                        &nbsp;&nbsp;
                        <c:url value="/release/add?projectId=${project.id}" var="releaseAddUrl"/>
                        <a class="fa fa-plus" href="<c:out value='${releaseAddUrl}'/>"></a>
                    </td>
                    <td>${project.status}</td>
                    <td>
                        <c:url value="/project/edit/${project.id}" var="editUrl"/>
                        <a href="<c:out value='${editUrl}'/>" class="fa fa-edit"></a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
</div>