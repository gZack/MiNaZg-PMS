<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="project-search-form">
    <form  method="GET" action="/release/search" >
        <div class="col-md-4">
            <div class="input-group">
                <input type="hidden" name="projectId" id="projectId" value="${projectId}">
                <input class="form-control" placeholder="Version Number" required name="versionNumber" tupe="text" value="${versionNumber}"/>
                <span class="input-group-btn">
                    <button type="submit" class="btn btn-default" type="button">Search</button>
                  </span>
            </div>
        </div>
        <div class="clearfix"></div>
    </form>
</div>
<div class="list-group">
    <a class="list-group-item active">
        <b>${projectTitle} list of Releases</b>
    </a>
    <div>
        <c:if test="${not empty releases}">
            <table class="table">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Version No.</th>
                    <th>Status</th>
                    <th>Release Date</th>
                    <th>Remark</th>
                    <th>Sprint</th>
                    <th>Modify</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="release" items="${releases}">
                    <tr>
                        <td>${release.id}</td>
                        <td>
                            <c:url value="/release/detail/${release.id}" var="releaseViewUrl"/>
                            <a href="<c:out value='${releaseViewUrl}'/>">${release.versionNumber}</a>
                        </td>
                        <td>${release.status}</td>
                        <td>${release.releaseDate}</td>
                        <td>${release.remark}</td>
                        <td>
                            <c:url value="/sprint/list/${release.id}" var="sprintViewUrl"/>
                            <a title="View Sprints" class="fa fa-list" href="<c:out value='${sprintViewUrl}'/>">
                            </a>
                            &nbsp;&nbsp;
                            <c:url value="/sprint/add/${release.id}" var="sprintAddUrl"/>
                            <a title="Add Sprints" class="fa fa-plus" href="<c:out value='${sprintAddUrl}'/>"></a>
                        </td>
                        <td>
                            <c:url value="/release/edit/${release.id}" var="editUrl"/>
                            <a title="Edit Release" class="fa fa-edit" href="<c:out value='${editUrl}'/>"></a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:if>
    </div>

    <div class="well well-sm">
        <a href="<c:url value="/release/add/${projectId}"/>">Add New Release</a>
    </div>
</div>




