<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
    <c:when test="${action eq 'edit'}">
        <h2>Edit Sprint</h2>
    </c:when>
    <c:otherwise>
        <h2>Add New Sprint</h2>
    </c:otherwise>
</c:choose>
<hr>
<div class="half-width">



    <form:form modelAttribute="newSprint" class="form-horizontal ">
        <c:choose>
            <c:when test="${action eq 'edit'}">
                <form:hidden path="id" id="id"/>
            </c:when>
        </c:choose>

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


        <div class="form-group" >
            <label class="control-label col-sm-2" for="startDate">Start Date:</label>
            <div class="col-sm-10">

                <div class="datepicker">
                <div class='input-group datex' >
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
                <div class='input-group datex'>
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
        <%--<div class="form-group">--%>
            <%--<label class="control-label col-sm-2" for="release.id">Release ID:</label>--%>
            <%--<div class="col-sm-10">--%>
                <%--<form:input type="text" path="release.id" id="release.id" value="1" class="form-control input-sm" />--%>
                <%--<div class="has-error">--%>
                    <%--<form:errors path="release.id" class="help-inline"/>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>

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

