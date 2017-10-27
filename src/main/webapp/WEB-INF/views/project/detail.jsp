<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="half-width container project-detail-container" >
    <a href='<c:url value="/project" />' class="btn btn-warning back-btn" type="button">Back</a>
    <h1>${project.name}</h1>

    <hr/>
    <p>${project.description}</p>
    <p>
        Status: <span class="badge">${project.status}</span>
    </p>
    <p>
        Number of Releases:  <span class="badge">${fn:length(projectDetails)}</span>
    </p>

    <hr/>
    <c:set var="ctr" scope="page" value="0"/>
    <p>
    <c:forEach var="release" items="${projectDetails}">
        <c:set var="ctr" scope="page" value="${ctr + 1}" />
        <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
             <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="headingOne">
                    <h4 class="panel-title">
                        <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapse<c:out value = "${ctr}"/>" aria-expanded="true" aria-controls="collapseOne">
                            <p>Release: ${release.versionNumber}</p>
                        </a>
                    </h4>
                </div>
                <div id="collapse<c:out value = "${ctr}"/>" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
                    <div class="panel-body">
                        <c:set var="sprintCtr" scope="page" value="0"/>
                        <c:forEach var="sprint" items="${release.sprints}">
                            <c:set var="sprintCtr" scope="page" value="1" />
                            <c:set var="sprintTotal" scope="page" value="0.0"/>
                            <c:set var="taskCtr" scope="page" value="0"/>
                            <p><strong>Sprint: <a target="blank" href='<c:url value="/task/list?sprintId=${sprint.id}" />'>${sprint.title}</a></strong></p>
                            <ul class="list-group">
                            <c:forEach var="workOrder" items="${sprint.workOrders}">
                                <li class="list-group-item">Task: <a target="blank" href='<c:url value="/task/list?sprintId=${sprint.id}" />' >${workOrder.title} ${workOrder.totalProgress} %</a></li>
                                <c:set var="sprintTotal" scope="page" value="${sprintTotal + workOrder.totalProgress}" />
                                <c:set var="taskCtr" scope="page" value="${taskCtr + 1}" />
                            </c:forEach>
                            </ul>
                            <c:if test="${taskCtr eq 0}">
                                <c:set var="taskCtr" scope="page" value="1" />
                            </c:if>
                            <div class="progress">
                                <div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: ${sprintTotal}%;">
                                        <c:out value = "${sprintTotal / taskCtr}"/> %
                                </div>
                            </div>
                        </c:forEach>
                        <c:if test="${sprintCtr eq 0}">
                            <p>No Sprint Found under this Release.</p>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>
    </p>

    <div class="">
    <jsp:include page="../common/comment.jsp" />
    </div>
    <div class="clearfix"></div>
</div>