<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="list-group">
    <a class="list-group-item active">
        New Release
        <c:if test="${project.name != null}">
            for <b>${project.name}</b>
        </c:if>
    </a>
    <br/><br/>
    <div class="half-width">
        <form:form modelAttribute="newRelease" cssClass="form-horizontal">
            <c:choose>
                <c:when test="${action eq 'edit'}">
                    <form:hidden path="id" id="id"/>
                </c:when>
            </c:choose>

            <c:choose>
                <c:when test="${projectId != null}">
                    <form:input type="hidden" path="project.id"/>
                </c:when>
                <c:otherwise>
                    <div class="form-group">
                        <label class="col-sm-2 control-label" for="project.id">projects:</label>
                        <div class="col-sm-10">
                            <form:select id="project" path="project.id" class="form-control">
                                <form:option value="">--select project--</form:option>
                                <form:options items="${projects}" itemValue="id" itemLabel="name" />
                            </form:select>
                            <div class="has-error">
                                <form:errors path="project.id" class="help-inline"/>
                            </div>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>

            <%--<form:hidden path="project.id" value="${projectId}" id="project.id"/>--%>

            <div class="form-group">
                <label class="control-label col-sm-2" for="versionNumber">Version Number:</label>
                <div class="col-sm-10">
                    <form:input path="versionNumber" class="form-control" id="versionNumber"/>
                    <div class="has-error">
                        <form:errors path="versionNumber"/>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2" for="remark">Remark:</label>
                <div class="col-sm-10">
                    <form:textarea path="remark" class="form-control" id="remark"/>
                    <div class="has-error">
                        <form:errors path="remark"/>
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
                        <form:errors path="status"/>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2" for="releaseDate">Release Date:</label>
                <div class="col-sm-10">
                    <div class='input-group date' id='datetimepicker1'>
                        <form:input path="releaseDate" class="form-control"/>
                        <span class="input-group-addon">
                            <span class="glyphicon glyphicon-calendar"></span>
                        </span>
                    </div>
                    <div class="has-error">
                        <form:errors path="releaseDate"/>
                    </div>
                    <div class='col-sm-6'>

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
</div>