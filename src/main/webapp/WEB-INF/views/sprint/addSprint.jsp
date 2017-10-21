<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<H2> Add a New Sprint</H2>
<hr>
<div class="half-width">

    <form:form method="POST" modelAttribute="newSprint" class="form-horizontal">

        <div class="form-group">
            <label class="control-label col-sm-2" for="title">Title:</label>
                <div class="col-sm-10">
                    <form:input type="text" path="title" id="title" class="form-control input-sm" placeholder="Title"/>
                    <div class="has-error">
                        <form:errors path="title" class="help-inline"/>
                    </div>
                </div>
        </div>


        <div class="form-group">
            <label class="control-label col-sm-2" for="description">Description:</label>
                <div class="col-sm-10">
                    <form:textarea type="text" path="description" id="description" class="form-control input-sm" />
                    <div class="has-error">
                        <form:errors path="description" class="help-inline"/>
                    </div>
                </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-2" for="startDate">Start Date:</label>
            <div class="col-sm-10">

                <div class="datepicker">
                <div class='input-group date' >
                    <form:input path="startDate" class="form-control" />
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

        <div class="form-group">
            <label class="control-label col-sm-2" for="endDate">End Date:</label>
            <div class="col-sm-10">

                <div class="datepicker">
                <div class='input-group date'>
                    <form:input path="endDate" class="form-control" />
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
            <label class="control-label col-sm-2" for="release.id">Release ID:</label>
            <div class="col-sm-10">
                <form:input type="text" path="release.id" id="release.id" value="1" class="form-control input-sm" />
                <div class="has-error">
                    <form:errors path="release.id" class="help-inline"/>
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

<%--<script type="text/javascript">--%>
    <%--// # ref http://eonasdan.github.io/bootstrap-datetimepicker/Options/--%>
    <%--$(".datepicker").datetimepicker({--%>
        <%--format: 'MM-DD-YYYY',--%>
        <%--widgetPositioning: {--%>
            <%--horizontal: 'auto',--%>
            <%--vertical: 'bottom'--%>
        <%--}--%>
    <%--});--%>

<%--</script>--%>