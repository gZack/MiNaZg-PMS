<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<h2>Add New Project</h2>
<hr/>
<div class="table-responsive">
    <form:form modelAttribute="newProject" cssClass="form-horizontal">
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
                <form:select path="status" items="${statusTypes}"
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
