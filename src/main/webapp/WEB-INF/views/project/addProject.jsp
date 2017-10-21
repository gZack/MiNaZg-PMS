<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h2>Add New Project</h2>
<hr/>
<div class="half-width">
    <form:form modelAttribute="newProject" cssClass="form-horizontal">
        <c:choose>
            <c:when test="${action eq 'edit'}">
                <form:hidden path="id" id="id"/>
            </c:when>
        </c:choose>
        <div class="form-group">
            <label class="control-label col-sm-2" for="name">Name:</label>
            <div class="col-sm-10">
                <form:input  path="name" class="form-control" id="name"  />
                <div class="has-error">
                    <form:errors path="name" />
                </div>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="description">Description:</label>
            <div class="col-sm-10">
                <form:textarea path="description" class="form-control" id="description" />
                <div class="has-error">
                    <form:errors path="description" />
                </div>
            </div>
        </div>

        <div class="form-group">
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

        <div class="form-group">
            <label class="control-label col-sm-2" for="dateStart">Start Date:</label>
            <div class="col-sm-10">
                <%--<form:input  path="dateStart" class="form-control" id="dateStart"  />--%>
                    <div class='input-group date' id='datetimepicker1'>
                        <form:input path="dateStart" class="form-control" />
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
            <label class="control-label col-sm-2" for="dateEnd">End Date:</label>
            <div class="col-sm-10">
                <div class='input-group date' id='datetimepicker2'>
                    <form:input path="dateEnd" class="form-control" />
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
            <label class="control-label col-sm-2" for="projectManager">project Manager:</label>
            <div class="col-sm-10">
                <%--<form:input path="projectManager.id" class="form-control" id="description" value="1" />--%>
                <form:select class="form-control" id = "projectManager" path="projectManager.id"  >
                    <form:option value="1" label="Select User" />
                    <form:options items="${projectManagerList}" itemValue="id" itemLabel="firstName" />
                </form:select>

                <div class="has-error">
                    <form:errors path="projectManager" />
                </div>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="client">Client:</label>
            <div class="col-sm-10">
                <%--<form:input path="client.id" class="form-control" id="client" value="1" />--%>
                    <form:select class="form-control" id = "client" path="client.id"  >
                        <form:option value="1" label="Select User" />
                        <form:options items="${clientList}" itemValue="id" itemLabel="firstName" />
                    </form:select>
                <div class="has-error">
                    <form:errors path="client" />
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${action eq 'edit'}">
                        <button type="submit" class="btn btn-default">Edit</button>
                    </c:when>
                    <c:otherwise>
                        <button type="submit" class="btn btn-default">Add</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
</div>

<script type="text/javascript">
    // # ref http://eonasdan.github.io/bootstrap-datetimepicker/Options/
    $(".date").datetimepicker({
        format: 'MM-DD-YYYY',
        widgetPositioning: {
            horizontal: 'auto',
            vertical: 'bottom'
        }
    });

</script>