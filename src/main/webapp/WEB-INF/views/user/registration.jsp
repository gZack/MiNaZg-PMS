<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="half-width container">
	<div class="well lead">User Registration Form</div>

	<c:url value="/newuser" var="registrationUrl">
		<c:param name="${_csrf.parameterName}" value="${_csrf.token}"/>
	</c:url>

	<form:form method="POST" modelAttribute="user"
			   enctype="multipart/form-data"
			   action="${registrationUrl}"
			   class="form-horizontal">
		<form:input type="hidden" path="id" id="id"/>

		<div class="form-group">
			<label class="col-sm-3 control-label" for="firstName">First Name</label>
			<div class="col-sm-9">
				<form:input type="text" path="firstName" id="firstName" class="form-control input-sm"/>
				<div class="has-error">
					<form:errors path="firstName" class="help-inline"/>
				</div>
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-3 control-label" for="lastName">Last Name</label>
			<div class="col-sm-9">
				<form:input type="text" path="lastName" id="lastName" class="form-control input-sm" />
				<div class="has-error">
					<form:errors path="lastName" class="help-inline"/>
				</div>
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-3 control-label" for="ssoId">SSO ID</label>
			<div class="col-sm-9">
				<c:choose>
					<c:when test="${edit}">
						<form:input type="text" path="ssoId" id="ssoId" class="form-control input-sm" disabled="true"/>
					</c:when>
					<c:otherwise>
						<form:input type="text" path="ssoId" id="ssoId" class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="ssoId" class="help-inline"/>
						</div>
					</c:otherwise>
				</c:choose>
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-3 control-label" for="password">Password</label>
			<div class="col-sm-7">
				<form:input type="password" path="password" id="password" class="form-control input-sm" />
				<div class="has-error">
					<form:errors path="password" class="help-inline"/>
				</div>
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-3 control-label" for="email">Email</label>
			<div class="col-sm-9">
				<form:input type="text" path="address.email" id="email" class="form-control input-sm" />
				<div class="has-error">
					<form:errors path="address.email" class="help-inline"/>
				</div>
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-3 control-label" for="street">Street</label>
			<div class="col-sm-9">
				<form:input type="text" path="address.street" id="street" class="form-control input-sm" />
				<div class="has-error">
					<form:errors path="address.street" class="help-inline"/>
				</div>
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-3 control-label" for="city">City</label>
			<div class="col-sm-9">
				<form:input type="text" path="address.city" id="city" class="form-control input-sm" />
				<div class="has-error">
					<form:errors path="address.city" class="help-inline"/>
				</div>
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-3 control-label" for="country">Country</label>
			<div class="col-sm-9">
				<form:input type="text" path="address.country" id="country" class="form-control input-sm" />
				<div class="has-error">
					<form:errors path="address.country" class="help-inline"/>
				</div>
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-3 control-label" for="zipCode">Zip Code</label>
			<div class="col-sm-9">
				<form:input type="text" path="address.zipCode" id="zipCode"
							class="form-control input-sm" />
				<div class="has-error">
					<form:errors path="address.zipCode" class="help-inline"/>
				</div>
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-3 control-label" for="profPic">Profile Pic</label>
			<div class="col-sm-9">
				<form:input type="file" path="userProfPic" id="profPic"
							class="form-control input-sm" />
				<div class="has-error">
					<form:errors path="userProfPic" class="help-inline"/>
				</div>
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-3 control-label" for="userRoles">Roles</label>
			<div class="col-sm-9">
				<form:select path="userRoles" items="${roles}" multiple="true" itemValue="name" itemLabel="name" class="form-control input-sm" />
				<div class="has-error">
					<form:errors path="userRoles" class="help-inline"/>
				</div>
			</div>
		</div>

		<div class="form-actions floatRight">
			<input type="submit" value="Register" class="btn btn-primary btn-sm"/> or <a href="<c:url value='/list' />">Cancel</a>
		</div>
	</form:form>
</div>
