<div class="half-width container">
    <div class="panel panel-default">
        <div class="panel-heading">
            <strong><b>${release.project.name} - Version No. ${release.versionNumber}</b></strong>

            <span class="badge">${release.status}</span>
            <div class="btn-group pull-right">

                <c:url value="/release/list?projectId=${release.id}" var="projectUrl"/>
                <a title="View Sprints" class="btn btn-primary" href="<c:out value='${projectUrl}'/>">
                </a>
            </div>

        </div>

        <div class="panel-body">
            <p class="text-info">${release.remark}</p>
        </div>

        <ul class="list-group">
            <li class="list-group-item">
                Release Date
                <span class="badge">${release.releaseDate}</span>
            </li>
        </ul>
    </div>
</div>