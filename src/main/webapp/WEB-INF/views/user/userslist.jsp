<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="panel panel-default">

	<div class="panel-heading">
		Users
	</div>
	<div class="panel-body">
		<a href="<c:url value='/newuser' />" class="col-md-1 btn btn-warning pull-right">Add User</a>
		<form class="col-md-10" method="GET" action="<c:url value="/user-search" />" >
			<div class="col-md-4">
				<div class="input-group">
					<input class="form-control" name="q" tupe="text" value="${param['q']}"
						   placeholder="ssoid or fname or lname" />
					<span class="input-group-btn">
                        <button type="submit" class="btn btn-default" type="button">Go!</button>
                      </span>
				</div>
			</div>
			<div class="clearfix"></div>
		</form>
		<div class="clearfix"></div>
	</div>


	<table class="table">
		<thead>
		<tr>
			<th scope="col">Firstname</th>
			<th scope="col">Lastname</th>
			<th scope="col">Username</th>
			<th scope="col" width="100"></th>
		</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${fn:length(users) > 0}">
					<c:forEach items="${users}" var="user">
						<tr>
							<td>${user.firstName}</td>
							<td>${user.lastName}</td>
							<td>${user.ssoId}</td>
							<td>
								<a href="<c:url value='/edit-user-${user.ssoId}'  />" class="fa fa-edit"/>
							</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>

				</c:otherwise>
			</c:choose>
		</tbody>
	</table>

	<c:if test="${pages > 0}">
		<div class="panel-body">
			<div class="pull-right">
				<nav aria-label="pager">
					<ul class="pager">
						<c:if test="${prevPage > 0}">
							<c:url value="/list" var="prevPage">
								<c:param name="page" value="${prevPage - 1}"/>
								<c:param name="size" value="${pageSize}"/>
							</c:url>
							<li><a href="${prevPage}">Previous</a></li>
						</c:if>

						<c:if test="${nextPage <= pages - 1}">
							<c:url value="/list" var="nextPage">
								<c:param name="page" value="${nextPage}"/>
								<c:param name="size" value="${pageSize}"/>
							</c:url>
							<li><a href="${nextPage}">Next</a></li>
						</c:if>
					</ul>
				</nav>
			</div>
		</div>
	</c:if>
</div>

