<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="project-search-form">
    <form  method="GET" action="/report/search" >

        <div class="col-md-3">
            <div class="input-group">
                <select class="form-control" id="searchType" name="searchType">
                    <option value="NONE" selected>---- Select Search Type ----</option>
                    <c:forEach items="${searchType}" var="type">
                        <option value="${type.key}">${type.value}</option>
                    </c:forEach>
                </select>
            </div>
            <br/>
            <div class="input-group">
                <input class="form-control" placeholder="Name" required name="name" tupe="text" value="${name}"/>
                <span class="input-group-btn">
                    <button type="submit" class="btn btn-default" type="button">Search</button>
                </span>
            </div>
        </div>
        <div class="clearfix"></div>
    </form>
</div>
<br/>
<div class="list-group">
    <a class="list-group-item active">
        <b>Task Report</b>
    </a>
    <div>
        <c:if test="${not empty reports}">
            <table class="table">
                <thead>
                <tr>
                    <th>Task</th>
                    <th>Developer</th>
                    <th>Status</th>
                    <th>Total Duration</th>
                    <th>Hours Spent</th>
                    <th>Progress (%)</th>
                    <th>Log Time</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="report" items="${reports}">
                    <tr>
                        <td>${report.workOrder.title}</td>
                        <td>${report.workOrder.developer.lastName}</td>
                        <td>${report.workOrder.status}</td>
                        <td>${report.workOrder.totalDuration}</td>
                        <td>${report.hoursSpent}</td>
                        <td>${report.progressPercentage}</td>
                        <td>${report.timeLog}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:if>
    </div>

</div>





