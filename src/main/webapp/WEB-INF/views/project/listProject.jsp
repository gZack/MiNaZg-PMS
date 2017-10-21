<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h2>Projects</h2>
<hr/>
<div class="">
    <c:if test="${not empty flashMessage}" >
        <div class="alert alert-success">
            <strong>Success!</strong> ${flashMessage}
        </div>
    </c:if>
    <c:if test="${not empty projects}">
        <table class="table">
            <thead>
            <tr>
                <th>#</th>
                <th>Name</th>
                <th>Release</th>
                <th>Status</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
                <c:forEach var="project" items="${projects}">
                    <tr>
                        <td>${project.id}</td>
                        <td>
                            <c:url value="/project/detail/${project.id}" var="projectViewUrl"/>
                            <a href="<c:out value='${projectViewUrl}'/>">${project.name}</a></td>
                        <td>
                            <c:url value="/release/list/${project.id}" var="releaseViewUrl"/>
                            <a class="fa fa-list" href="<c:out value='${releaseViewUrl}'/>"></a>
                            &nbsp;&nbsp;
                            <c:url value="/release/add/${project.id}" var="releaseAddUrl"/>
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
