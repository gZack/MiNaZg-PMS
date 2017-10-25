<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="half-width container">

	<div class="panel panel-default">

		<div class="panel-heading">
			Log Report
			<div class="pull-right">
				<c:url value='/dev/detail' var="backToDetailUrl">
					<c:param name="id" value="${task.id}"/>
				</c:url>
				<a href="${backToDetailUrl}" class="btn btn-primary btn-xs">Back</a>
			</div>
		</div>

	<form:form method="POST" modelAttribute="report" class="form-horizontal">

		<div class="form-padding">
			<form:input type="hidden" path="workOrder"/>

			<div class="form-group">
				<label class="col-sm-3 control-label" >Task Title:</label>
				<div class="col-sm-9">
					<p class="badge">
						<strong>${task.title}</strong>
					</p>
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-3 control-label">Task Status:</label>
				<div class="col-sm-9">
					<p class="badge">
						<strong>${task.status}</strong>
					</p>
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-3 control-label" for="hour">Hour:</label>
				<div class="col-sm-9">
					<form:input type="text" path="hoursSpent" id="hour" class="form-control input-sm"/>
					<div class="has-error">
						<form:errors path="hoursSpent" class="help-inline"/>
					</div>
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-3 control-label" for="progress">Progress:</label>
				<div class="col-sm-9">
					<form:input type="text" path="progressPercentage" id="progress" class="form-control input-sm"/>
					<div class="has-error">
						<form:errors path="progressPercentage" class="help-inline"/>
					</div>
				</div>
			</div>
		</div>

		<div class="panel-body">
			<div class="pull-right">
				<input type="submit" value="Create Report" class="btn btn-primary btn-sm"/>
			</div>
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