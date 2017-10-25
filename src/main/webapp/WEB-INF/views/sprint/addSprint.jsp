<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="half-width container">
    <div class="well lead">
        <c:choose>
            <c:when test="${action eq 'edit'}">
                Edit Sprint
            </c:when>
            <c:otherwise>
                Create Sprint
            </c:otherwise>
        </c:choose>
    </div>
    <form:form method="POST" modelAttribute="newSprint" class="form-horizontal">
    <%--<form:form modelAttribute="newSprint" class="form-horizontal formWithDateValidation">--%>



        <div class="form-group">
            <label class="col-sm-3 control-label" for="title">Title:</label>
            <div class="col-sm-9">
                <form:input type="text" path="title" id="title" class="form-control input-sm"/>
                <div class="has-error">
                    <form:errors path="title" class="help-inline"/>
                </div>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-3 control-label" for="description">Description:</label>
            <div class="col-sm-9">
                <form:textarea placeholder="description" type="text"
                               path="description" id="description" class="form-control input-sm" />
                <h6 class="pull-right" id="count_message"></h6>
                <div class="has-error">
                    <form:errors path="description" class="help-inline"/>
                </div>
            </div>
        </div>

        <div class="form-group" >
            <label class="control-label col-sm-2" for="status">Status:</label>
            <div class="col-sm-10">
                <form:select class="form-control" path="status" items="${statusTypes}"
                             itemLabel="StatusType"
                             itemValue="StatusType"/>
                <div class="has-error">
                    <form:errors path="status" />
                </div>
            </div>
        </div>

        <form:hidden path="release.id" value="${releaseId}" id="release.id"/>


        <div class="form-group" >
            <label class="control-label col-sm-2" for="startDate">Start Date:</label>
            <div class="col-sm-10">

                <div class="datepicker">
                    <div class='input-group date' >
                        <form:input id="startDate" path="startDate" class="form-control" />
                        <span class="input-group-addon">
                            <span class="glyphicon glyphicon-calendar"></span>
                        </span>
                    </div>
                </div>
                <div class="has-error">
                    <form:errors path="startDate" />
                </div>

            </div>
        </div>


        <div class="form-group" >
            <label class="control-label col-sm-2" for="endDate">End Date:</label>
            <div class="col-sm-10">

                <div class="datepicker">
                    <div class='input-group date'>
                        <form:input id="endDate" path="endDate" class="form-control" />
                        <span class="input-group-addon">
                            <span class="glyphicon glyphicon-calendar"></span>
                        </span>
                    </div>
                </div>
                <div class="has-error">
                    <form:errors path="endDate" />
                </div>

            </div>
        </div>


        <div class="form-actions floatRight">
            <c:choose>
                <c:when test="${action eq 'edit'}">
                    <input type="submit" value="Edit" class="btn btn-primary btn-sm"/>
                </c:when>
                <c:otherwise>
                    <input type="submit" value="Create Sprint" class="btn btn-primary btn-sm"/>
                </c:otherwise>
            </c:choose>

            <a href="/sprint/list/${releaseId}" class="btn btn-primary btn-sm">Back to Sprint</a>

        </div>
    </form:form>
</div>

<script type="application/javascript">
    var text_max = 200;
    $('#count_message').html(text_max + ' remaining');

    $('#description').keyup(function() {
        var text_length = $('#description').val().length;
        var text_remaining = text_max - text_length;

        $('#count_message').html(text_remaining + ' remaining');
    });
</script>














<%--<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>--%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>

<%--<c:choose>--%>
    <%--<c:when test="${action eq 'edit'}">--%>
        <%--<h2>Edit Sprint</h2>--%>
    <%--</c:when>--%>
    <%--<c:otherwise>--%>
        <%--<h2>Add New Sprint</h2>--%>
    <%--</c:otherwise>--%>
<%--</c:choose>--%>
<%--<hr>--%>
<%--<div class="half-width">--%>



    <%--<form:form modelAttribute="newSprint" class="form-horizontal formWithDateValidation">--%>
        <%--<c:choose>--%>
            <%--<c:when test="${action eq 'edit'}">--%>
                <%--<form:hidden path="id" id="id"/>--%>
            <%--</c:when>--%>
        <%--</c:choose>--%>

        <%--<div class="form-group">--%>
            <%--<label class="control-label col-sm-2" for="title">Title:</label>--%>
                <%--<div class="col-sm-10">--%>
                    <%--<form:input type="text" path="title" id="title" class="form-control input-sm" placeholder="Title"/>--%>
                    <%--<div class="has-error">--%>
                        <%--<form:errors path="title" class="help-inline"/>--%>
                    <%--</div>--%>
                <%--</div>--%>
        <%--</div>--%>


        <%--<div class="form-group">--%>
            <%--<label class="control-label col-sm-2" for="description">Description:</label>--%>
                <%--<div class="col-sm-10">--%>
                    <%--<form:textarea type="text" path="description" id="description" class="form-control input-sm" />--%>
                    <%--<div class="has-error">--%>
                        <%--<form:errors path="description" class="help-inline"/>--%>
                    <%--</div>--%>
                <%--</div>--%>
        <%--</div>--%>


        <%--<div class="form-group" >--%>
            <%--<label class="control-label col-sm-2" for="startDate">Start Date:</label>--%>
            <%--<div class="col-sm-10">--%>

                <%--<div class="datepicker">--%>
                <%--<div class='input-group date' >--%>
                    <%--<form:input id="startDate" path="startDate" class="form-control" />--%>
                    <%--<span class="input-group-addon">--%>
                            <%--<span class="glyphicon glyphicon-calendar"></span>--%>
                        <%--</span>--%>
                <%--</div>--%>
                <%--</div>--%>
                <%--<div class="has-error">--%>
                    <%--<form:errors path="startDate" />--%>
                <%--</div>--%>

            <%--</div>--%>
        <%--</div>--%>


        <%--<div class="form-group" >--%>
            <%--<label class="control-label col-sm-2" for="endDate">End Date:</label>--%>
            <%--<div class="col-sm-10">--%>

                <%--<div class="datepicker">--%>
                <%--<div class='input-group date'>--%>
                    <%--<form:input id="endDate" path="endDate" class="form-control" />--%>
                    <%--<span class="input-group-addon">--%>
                            <%--<span class="glyphicon glyphicon-calendar"></span>--%>
                        <%--</span>--%>
                <%--</div>--%>
                <%--</div>--%>
                <%--<div class="has-error">--%>
                    <%--<form:errors path="endDate" />--%>
                <%--</div>--%>

            <%--</div>--%>
        <%--</div>--%>

        <%--<div class="form-group" >--%>
            <%--<label class="control-label col-sm-2" for="status">Status:</label>--%>
            <%--<div class="col-sm-10">--%>
                <%--<form:select class="form-control" path="status" items="${statusTypes}"--%>
                             <%--itemLabel="StatusType"--%>
                             <%--itemValue="StatusType"/>--%>
                <%--<div class="has-error">--%>
                    <%--<form:errors path="status" />--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>

        <%--<form:hidden path="release.id" value="${releaseId}" id="release.id"/>--%>


        <%--<div class="form-group">--%>
            <%--<div class="col-sm-offset-2 col-sm-10">--%>
                <%--<c:choose>--%>
                    <%--<c:when test="${action eq 'edit'}">--%>
                        <%--<button type="submit" class="btn btn-default">Edit</button>--%>
                    <%--</c:when>--%>
                    <%--<c:otherwise>--%>
                        <%--<button type="submit" class="btn btn-default">Add</button>--%>
                    <%--</c:otherwise>--%>
                <%--</c:choose>--%>
                <%--<a href="<c:url value="/release/list${projectId}"/>" class="col-sm-2 btn btn-warning pull-right">Back to Release</a>--%>
            <%--</div>--%>

        <%--</div>--%>
    <%--</form:form>--%>



<%--</div>--%>






