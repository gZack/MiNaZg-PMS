<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<section class="sidebar">
    <div class="user-panel">
        <div class="pull-left image">
            <img src='<c:url value="/static/img/avatar.png" />' class="img-circle" alt="User Image">
        </div>
        <div class="pull-left info">
            <p>Alexander Pierce</p>
            <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
        </div>
    </div>

    <ul class="sidebar-menu" data-widget="tree">
        <li class="header">MAIN NAVIGATION</li>
        <li>
            <a href='<spring:url value="/project/" />'>
                <i class="fa fa-dashboard"></i> <span>Dashboard</span>
                <span class="pull-right-container">
            </span>
            </a>
        </li>
        <li>
            <a href="#">
                <i class="fa fa-th"></i> <span>Tasks</span>
                <span class="pull-right-container">
            </span>
            </a>
        </li>
        <li>
            <a href="#">
                <i class="fa fa-user"></i> <span>Manage Users</span>
                <span class="pull-right-container">
            </span>
            </a>
        </li>
    </ul>
</section>

