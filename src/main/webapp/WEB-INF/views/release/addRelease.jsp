<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<h2>Add New Project</h2>
<hr/>
<div class="half-width">
    <form:form modelAttribute="newRelease" cssClass="form-horizontal">
        <div class="form-group">
            <label class="control-label col-sm-2" for="project.id">Project Name:</label>
            <div class="col-sm-10">
                <form:input  path="project.id" class="form-control" id="project.id"  />
                <div class="has-error">
                    <form:errors path="project.id" />
                </div>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-2" for="versionNumber">Version Number:</label>
            <div class="col-sm-10">
                <form:input path="versionNumber" class="form-control" id="versionNumber" />
                <div class="has-error">
                    <form:errors path="versionNumber" />
                </div>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-2" for="releaseDate">Release Date:</label>
            <div class="col-sm-10">
                <div class='input-group date' id='datetimepicker1'>
                    <form:input path="releaseDate" class="form-control" />
                    <span class="input-group-addon">
                            <span class="glyphicon glyphicon-calendar"></span>
                    </span>
                </div>
                <div class="has-error">
                    <form:errors path="releaseDate" />
                </div>
                <div class='col-sm-6'>

                </div>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-2" for="remark">Remark:</label>
            <div class="col-sm-10">
                <form:textarea path="remark" class="form-control" id="remark" />
                <div class="has-error">
                    <form:errors path="remark" />
                </div>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-2" for="releaseDate">Status:</label>
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
            <div class="col-sm-offset-2 col-sm-10">
                <form:hidden path="id" id="id"/>
                <button type="submit" class="btn btn-default">Submit</button>
            </div>
        </div>
    </form:form>
</div>