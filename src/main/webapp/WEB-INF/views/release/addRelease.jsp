<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="half-width container">
    <div class="well lead"> New Release for <b>${projectName}</b>
    </div>

    <div class="">
        <form:form modelAttribute="newRelease" cssClass="form-horizontal formWithDateValidation">
            <c:choose>
                <c:when test="${action eq 'edit'}">
                    <form:hidden path="id" id="id"/>
                </c:when>
            </c:choose>

            <form:hidden path="project.id" value="${projectId}" id="project.id"/>

            <div class="form-group">
                <label class="col-sm-3 control-label" for="versionNumber">Version Number:</label>
                <div class="col-sm-9">
                    <form:input type="text" path="versionNumber" id="versionNumber" class="form-control input-sm"/>
                    <div class="has-error">
                        <form:errors path="versionNumber" class="help-inline"/>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-3 control-label" for="remark">Remark:</label>
                <div class="col-sm-9">
                    <form:input type="text" path="remark" id="remark" class="form-control input-sm"/>
                    <div class="has-error">
                        <form:errors path="remark" class="help-inline"/>
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

            <%--<div class="form-group">--%>
                <%--<label class="col-sm-3 control-label" for="releaseDate">Release Date:</label>--%>
                <%--<div class="col-sm-10">--%>
                    <%--<div class='input-group date' id='datetimepicker1'>--%>
                        <%--<form:input path="releaseDate" class="form-control"/>--%>
                        <%--<span class="input-group-addon">--%>
                            <%--<span class="glyphicon glyphicon-calendar"></span>--%>
                        <%--</span>--%>
                    <%--</div>--%>
                    <%--<div class="has-error">--%>
                        <%--<form:errors path="releaseDate"/>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>

            <div class="form-group">
                <label class="control-label col-sm-3" for="releaseDate">Release Date:</label>
                <div class="col-sm-9">
                    <div class='input-group date' id='datetimepicker1'>
                        <form:input id="releaseDate" path="releaseDate" class="form-control input-sm" />
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

            <div class="form-group floatRight">
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
</div>