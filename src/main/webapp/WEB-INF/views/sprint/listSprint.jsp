<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h2>Sprints</h2>
<hr/>
<div class="">
<c:if test="${not empty flashMessage}" >
    <div class="alert alert-success">
        <strong>Success</strong> ${flashMessage}
    </div>
</c:if>
<div class="project-search-form">
    <div>
        <button href="/sprint/add" class="col-md-1 btn btn-warning">Add New</button>
    </div>

    <form  method="GET" action="/sprint/search" >
        <div class="col-md-4">
            <div class="input-group">
                <input type="hidden" name="releaseId" id="releaseId" value="${releaseId}">
                <input class="form-control" placeholder="Sprint Title" required name="sprintTitle" tupe="text" value="${sprintTitle}"/>
                <span class="input-group-btn">
                    <button type="submit" class="btn btn-default" type="button">Search</button>
                  </span>
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
                <th>Sprint Id</th>
                <th>Title</th>
                <th>Description</th>
                <th>View/Edit WorkOrder</th>
                <th>Status</th>
                <th>Edit</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="sprint" items="${sprints}">
            <tr>
                    <td>${sprint.id}</td>
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
