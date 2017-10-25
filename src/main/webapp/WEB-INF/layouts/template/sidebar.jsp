<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<section class="sidebar">
    <div class="user-panel">
        <security:authorize access="isAuthenticated()">
        <div class="pull-left image">
            <img src='<c:url value="/static/img/9.png" />' class="img-circle" alt="User Image">
        </div>
        <div class="pull-left info">
            <p>${userDetail.firstName} ${userDetail.lastName}</p>
                <%--<security:authentication property="principal.username" />--%>
            <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
        </div>
        </security:authorize>
    </div>
    <ul class="sidebar-menu" data-widget="tree">
        <li class="header">MAIN NAVIGATION</li>

        <security:authorize access="isAuthenticated() && (hasRole('PROJECT_MANAGER') || hasRole('ADMIN'))">
            <li>
                <a href='<spring:url value="/project/" />'>
                    <i class="fa fa-dashboard"></i> <span><spring:message code="sidebar.dashboard" /></span>
                    <span class="pull-right-container">
                        </span>
                </a>
            </li>
        </security:authorize>

        <security:authorize access="isAuthenticated() && hasRole('PROJECT_MANAGER')">
            <li>
                <a href="<c:url value="/task/list"/> ">
                    <i class="fa fa-th"></i> <span><spring:message code="sidebar.managetasks" /></span>
                    <span class="pull-right-container">
                </span>
                </a>
            </li>
        </security:authorize>
        <security:authorize access="isAuthenticated() && hasRole('ADMIN')">
            <li>
                <a href="#">
                    <i class="fa fa-user"></i> <span><spring:message code="sidebar.manageusers" /></span>
                    <span class="pull-right-container">
                </span>
                </a>
            </li>
        </security:authorize>
        <security:authorize access="isAuthenticated() && hasRole('DEVELOPER')">
            <li>
                <a href="<c:url value="/dev/list"/> ">
                    <i class="fa fa-th"></i> <span><spring:message code="sidebar.mytasks" /></span>
                    <span class="pull-right-container">
                </span>
                </a>
            </li>
        </security:authorize>
    </ul>
</section>

