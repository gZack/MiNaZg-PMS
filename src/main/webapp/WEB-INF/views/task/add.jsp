<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="half-width container">

	<div class="panel panel-default">

		<div class="panel-heading">
			Create Task
			<div class="pull-right">
				<a href="<c:url value='/task/list' />" class="btn btn-primary btn-xs">Back</a>
			</div>
		</div>

	<form:form method="POST" modelAttribute="task" class="form-horizontal">
		<div class="form-padding">
			<form:input type="hidden" path="id" id="id"/>
			<c:choose>
				<c:when test="${sprintId != null}">
					<form:input type="hidden" path="sprint"/>
				</c:when>
				<c:otherwise>
					<div class="form-group">
						<label class="col-sm-3 control-label" for="sprint">Sprint:</label>
						<div class="col-sm-9">
							<form:select id="sprint" path="sprint" class="form-control input-sm">
								<form:option value="">--select sprint--</form:option>
								<form:options items="${sprints}" itemValue="id" itemLabel="title" />
							</form:select>
							<div class="has-error">
								<form:errors path="sprint.id" class="help-inline"/>
							</div>
						</div>
					</div>
				</c:otherwise>
			</c:choose>
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

			<div class="form-group">
				<label class="control-label col-sm-3" for="startDate">Start Date:</label>
				<div class="col-sm-9">
					<div class='input-group date' id='startDate'>
						<form:input path="startDate" class="form-control" />
						<span class="input-group-addon">
                            <span class="glyphicon glyphicon-calendar"></span>
					</span>
					</div>
					<div class="has-error">
						<form:errors path="startDate" />
					</div>
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-3" for="deadLine">End Date:</label>
				<div class="col-sm-9">
					<div class='input-group date' id='deadLine'>
						<form:input path="deadLine" class="form-control" />
						<span class="input-group-addon">
                            <span class="glyphicon glyphicon-calendar"></span>
                        </span>
					</div>
					<div class="has-error">
						<form:errors path="deadLine" />
					</div>
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-3 control-label" for="developer">Developer:</label>
				<div class="col-sm-9">
					<form:select id="developer" path="developer" class="form-control input-sm">
						<form:option value="">--select developer--</form:option>
						<form:options items="${developers}" itemValue="id" itemLabel="ssoId" />
					</form:select>
					<div class="has-error">
						<form:errors path="developer.id" class="help-inline"/>
					</div>
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-3 control-label" for="type">Type:</label>
				<div class="col-sm-9">
					<form:select id="type" path="type" class="form-control input-sm">
						<form:option value="">--select type--</form:option>
						<form:options items="${workOrderTypes}" itemValue="workOrderType" itemLabel="workOrderType" />
					</form:select>
					<div class="has-error">
						<form:errors path="type" class="help-inline"/>
					</div>
				</div>
			</div>
		</div>

		<div class="panel-body">
			<div class="pull-right">
				<input type="submit" value="Create Task" class="btn btn-primary btn-sm"/>
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