<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
href="<spring:url value="/project/" />"
--%>

<!-- Navigation-->
<nav class="navbar navbar-static-top">
    <!-- Sidebar toggle button-->
    <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
    </a>

    <div class="navbar-custom-menu">
        <ul class="nav navbar-nav">
            <li>
                <a href="?language=en">
                    <img src='<c:url value="/static/img/en.png" />' class="" alt="EN"></a>
            </li>
            <li class="tasks-menu"><a href="?language=am">
                    <img src='<c:url value="/static/img/et.png" />' class="" alt="ET"></a>
            </li>
            <!-- Task Notification -->
            <security:authorize access="hasRole('DEVELOPER')">
                <li class="dropdown tasks-menu">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <i class="fa fa-flag-o"></i>
                        <span class="label label-danger">${fn:length(developerTasks.newTasks)}</span>
                    </a>
                    <ul class="dropdown-menu">
                        <li class="header">You have ${fn:length(developerTasks.newTasks)} task(s)</li>
                        <li>
                            <!-- inner menu: contains the actual data -->
                            <ul class="menu">
                                <c:forEach var="newTask" items="${developerTasks.newTasks}">
                                    <li><!-- Task item -->
                                        <a href="/dev/detail?id=${newTask.id}">
                                            <h3>
                                                    ${newTask.title}
                                                <small class="pull-right">${newTask.totalProgress}%</small>
                                            </h3>
                                            <%--<div class="progress xs">--%>
                                                <%--<div class="progress-bar progress-bar-aqua" style="width: ${newTask.totalProgress}%;" role="progressbar"--%>
                                                     <%--aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">--%>
                                                    <%--<span class="sr-only">${newTask.totalProgress}% Complete</span>--%>
                                                <%--</div>--%>
                                            <%--</div>--%>
                                        </a>
                                    </li>
                                    <%--<li class="list-group-item">Task: <a target="blank" href="/task/list?sprintId=${sprint.id}">${workOrder.title} ${workOrder.totalProgress} %</a></li>--%>
                                </c:forEach>

                                <!-- end task item -->
                            </ul>
                        </li>
                        <li class="footer">
                            <a href="/dev/list">View all tasks</a>
                        </li>
                    </ul>
                </li>
            </security:authorize>

            <!-- User Account: style can be found in dropdown.less -->
            <li class="dropdown user user-menu">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">

                    <c:choose>
                        <c:when test="${hasProfPic == true}">
                            <img src='<c:url value="/static/img/${userDetail.id}.png" />' class="user-image" alt="User Image">
                        </c:when>
                        <c:otherwise>
                            <img src='<c:url value="/static/img/avatar.png" />' class="user-image" alt="User Image">
                        </c:otherwise>
                    </c:choose>
                    <span class="hidden-xs">
                        <%--<secuirty:authentication property="principal.username"/>--%>
                        &nbsp;
                    </span>
                </a>
                <ul class="dropdown-menu">
                    <!-- User image -->
                    <li class="user-header">
                        <p>
                            ${userDetail.firstName} ${userDetail.lastName}
                        </p>
                    </li>

                    <!-- Menu Footer-->
                    <li class="user-footer">
                        <div class="pull-left">
                            <a href="#" class="btn btn-default btn-flat">Profile</a>
                        </div>
                        <div class="pull-right">
                            <a href="<c:url value="/logout" /> " class="btn btn-default btn-flat">Sign out</a>
                        </div>
                    </li>
                </ul>
            </li>
        </ul>
    </div>
</nav>