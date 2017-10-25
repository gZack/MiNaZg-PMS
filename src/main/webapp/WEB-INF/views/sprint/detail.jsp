<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="half-width container">
    <div class="panel panel-default">
        <div class="panel-heading">
            <strong>${sprint.title}</strong>

            <span class="badge">${sprint.status}</span>
            <div class="btn-group pull-right">
                <button type="button"
                        class="btn btn-primary dropdown-toggle btn-xs"
                        data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Action <span class="caret"></span>
                </button>
                <ul class="dropdown-menu">
                    <c:if test="${sprint.status == 'CREATED'}">
                        <c:url value="/dev/status" var="statusChangeUrl">
                            <c:param name="status" value="INPROGRESS"/>
                            <c:param name="id" value="${sprint.id}"/>
                        </c:url>
                        <li><a href="${statusChangeUrl}">Start</a></li>
                    </c:if>
                    <c:if test="${sprint.status == 'CREATED'|| sprint.status == 'INPROGRESS'}">
                        <c:url value="/dev/status" var="statusChangeUrl">
                            <c:param name="status" value="RESOLVED"/>
                            <c:param name="id" value="${sprint.id}"/>
                        </c:url>
                        <li><a href="${statusChangeUrl}">Resolve</a></li>
                    </c:if>
                    <c:if test="${sprint.status == 'RESOLVED'}">
                        <c:url value="/dev/status" var="statusChangeUrl">
                            <c:param name="status" value="CLOSED"/>
                            <c:param name="id" value="${sprint.id}"/>
                        </c:url>
                        <li><a href="${statusChangeUrl}">Close</a></li>
                    </c:if>

                    <li role="separator" class="divider"></li>
                    <c:url value="/sprint/list" var="backToList"/>
                    <li><a href="${backToList}">Back</a></li>
                </ul>
            </div>

        </div>

        <div class="panel-body">
            <p class="text-info">${sprint.description}</p>
        </div>

        <ul class="list-group">

            <li class="list-group-item">
                Status
                <span class="badge">${sprint.status}</span>
            </li>
            <li class="list-group-item">
                Start Date
                <span class="badge">
                    <fmt:formatDate value="${sprint.startDate}" pattern="MM-dd-yyyy"/>
                </span>
            </li>
            <li class="list-group-item">
                Start Date
                <span class="badge">
                    <fmt:formatDate value="${sprint.endDate}" pattern="MM-dd-yyyy"/>
                </span>
            </li>

        </ul>
    </div>

    <%--<c:url value="/dev/report" var="reportUrl">--%>
        <%--<c:param name="taskId" value="${sprint.id}"/>--%>
    <%--</c:url>--%>

    <%--<a class="btn btn-default" href="${reportUrl}" role="button">Daily Report</a>--%>

    <%--<jsp:include page="../common/comment.jsp" />--%>
</div>

