<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="half-width container">

    <div class="well lead">
        <c:choose>
            <c:when test="${action eq 'edit'}">
                Edit Project
            </c:when>
            <c:otherwise>
                Create Project
            </c:otherwise>
        </c:choose>
    </div>


    <form:form modelAttribute="newProject" cssClass="form-horizontal formWithDateValidation">
        <c:choose>
            <c:when test="${action eq 'edit'}">
                <form:hidden path="id" id="id"/>
            </c:when>
        </c:choose>
        <div class="form-group">
            <label class="control-label col-sm-3" for="name">Name:</label>
            <div class="col-sm-9">
                <form:input  path="name" class="form-control input-sm" id="name"  />
                <div class="has-error">
                    <form:errors path="name" />
                </div>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-3" for="description">Description:</label>
            <div class="col-sm-9">
                <form:textarea path="description" class="form-control input-sm" id="description" />
                <div class="has-error">
                    <form:errors path="description" />
                </div>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-3" for="status">Status:</label>
            <div class="col-sm-9">
                <form:select class="form-control input-sm" path="status" items="${statusTypes}"
                             itemLabel="StatusType"
                             itemValue="StatusType"/>
                <div class="has-error">
                    <form:errors path="status" />
                </div>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-3" for="startDate">Start Date:</label>
            <div class="col-sm-9">
                    <div class='input-group date' id='datetimepicker1'>
                        <form:input id="startDate" path="dateStart" class="form-control input-sm" />
                        <span class="input-group-addon">
                            <span class="glyphicon glyphicon-calendar"></span>
                        </span>
                    </div>
                <div class="has-error">
                    <form:errors path="dateStart" />
                </div>
                <div class='col-sm-6'>

                </div>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-3" for="endDate">End Date:</label>
            <div class="col-sm-9">
                <div class='input-group date' id='datetimepicker2'>
                    <form:input id="endDate" path="dateEnd" class="form-control input-sm" />
                    <span class="input-group-addon">
                            <span class="glyphicon glyphicon-calendar"></span>
                    </span>
                </div>
                <div class="has-error">
                    <form:errors path="dateEnd" />
                </div>
                <div class='col-sm-6'>

                </div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label " for="projectManager">Project Manager:</label>
            <div class="col-sm-9">
                <form:select class="form-control input-sm" id = "projectManager" path="projectManager.id"  >
                    <form:option value="1" label="Select User" />
                    <form:options items="${projectManagerList}" itemValue="id" itemLabel="firstName" />
                </form:select>

                <div class="has-error">
                    <form:errors path="projectManager.id" />
                </div>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-3" for="client">Client:</label>
            <div class="col-sm-9">
                <%--<form:input path="client.id" class="form-control" id="client" value="1" />--%>
                    <form:select class="form-control input-sm" id = "client" path="client.id"  >
                        <form:option value="1" label="Select User" />
                        <form:options items="${clientList}" itemValue="id" itemLabel="firstName" />
                    </form:select>
                <div class="has-error">
                    <form:errors path="client" />
                </div>
            </div>
        </div>
        <div class="form-actions floatRight">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${action eq 'edit'}">
                        <button type="submit" class="btn btn-primary btn-sm">Edit</button>
                    </c:when>
                    <c:otherwise>
                        <button type="submit" class="btn btn-primary btn-sm">Add</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
</div>
