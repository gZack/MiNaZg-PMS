<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h1>${project.name}</h1>
<hr/>
<p>${project.description}</p>
<p>Status: ${project.status}</p>
<p>
    No of Releases:
${projectDetails.numberOfReleases}
</p>
<p>
<c:forEach var="release" items="${projectDetails.releases}">
    <p>Release v. <a href="/release/detail/${release.id}">${release.versionNumber}</a></p>
</c:forEach>
</p>
<p>
<c:forEach var="sprints" items="${projectDetails.sprints}">
    <c:forEach var="sprint" items="${sprints}">
    <p>Sprint <a href="#">${sprint.title}</a></p>
    </c:forEach>
</c:forEach>
</p>

<p>
<c:forEach var="tasks" items="${projectDetails.tasks}">
    <c:forEach var="task" items="${tasks}">
        <p>Task <a href="#">${task.title}</a></p>
    </c:forEach>
</c:forEach>
</p>
<div class="col-md-11">
<jsp:include page="../common/comment.jsp" />
</div>
<div class="clearfix"></div>