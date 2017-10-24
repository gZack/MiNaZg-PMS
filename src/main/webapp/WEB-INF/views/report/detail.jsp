<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="half-width container">
    <div class="panel panel-default">
        <div class="panel-heading">
            <strong>${task.title}</strong>

            <span class="badge">${task.status}</span>
            <div class="btn-group pull-right">
                <button type="button"
                        class="btn btn-primary dropdown-toggle btn-xs"
                        data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Action <span class="caret"></span>
                </button>
                <ul class="dropdown-menu">
                    <c:if test="${task.status == 'CREATED'}">
                        <c:url value="/dev/status" var="statusChangeUrl">
                            <c:param name="status" value="INPROGRESS"/>
                            <c:param name="id" value="${task.id}"/>
                        </c:url>
                        <li><a href="${statusChangeUrl}">Start</a></li>
                    </c:if>
                    <c:if test="${task.status == 'CREATED'|| task.status == 'INPROGRESS'}">
                        <c:url value="/dev/status" var="statusChangeUrl">
                            <c:param name="status" value="RESOLVED"/>
                            <c:param name="id" value="${task.id}"/>
                        </c:url>
                        <li><a href="${statusChangeUrl}">Resolve</a></li>
                    </c:if>
                    <c:if test="${task.status == 'RESOLVED'}">
                        <c:url value="/dev/status" var="statusChangeUrl">
                            <c:param name="status" value="CLOSED"/>
                            <c:param name="id" value="${task.id}"/>
                        </c:url>
                        <li><a href="${statusChangeUrl}">Close</a></li>
                    </c:if>
                </ul>
            </div>

        </div>

        <div class="panel-body">
            <p class="text-info">${task.description}</p>
        </div>

        <ul class="list-group">
            <li class="list-group-item">
                Type
                <span class="badge">${task.type}</span>
            </li>
            <li class="list-group-item">
                Status
                <span class="badge">${task.status}</span>
            </li>
            <li class="list-group-item">
                Deadline
                <span class="badge">
                    <fmt:formatDate value="${task.startDate}" pattern="MM-dd-yyyy"/>
                </span>
            </li>
            <li class="list-group-item">
                Start Date
                <span class="badge">
                    <fmt:formatDate value="${task.deadLine}" pattern="MM-dd-yyyy"/>
                </span>
            </li>
            <li class="list-group-item">
                Total Progress
                <span class="badge">
                    <c:choose>
                        <c:when test="${task.totalProgress == null}">
                            0%
                        </c:when>
                        <c:otherwise>
                            ${task.totalProgress}
                        </c:otherwise>
                    </c:choose>
                </span>
            </li>
        </ul>
    </div>

    <c:url value="/dev/report" var="reportUrl">
        <c:param name="taskId" value="${task.id}"/>
    </c:url>

    <a class="btn btn-default" href="${reportUrl}" role="button">Link</a>

    <%--<a class="btn btn-primary" role="button" data-toggle="collapse" href="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
        Link with href
    </a>
    <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
        Button with data-target
    </button>
    <div class="collapse" id="collapseExample">
        <div class="well">
            ...
        </div>
    </div>

    <form:form modelAttribute="report" action="/dev/report">

        <div class="form-group">
            <label class="col-sm-3 control-label" for="hour">Hour:</label>
            <div class="col-sm-9">
                <form:input type="hour" path="hour" id="hour" class="form-control input-sm"/>
                <div class="has-error">
                    <form:errors path="hour" class="help-inline"/>
                </div>
            </div>
        </div>

        <div class="form-actions floatRight">
            <input type="submit" value="Create Task" class="btn btn-primary btn-sm"/>
        </div>

    </form:form>--%>

</div>

