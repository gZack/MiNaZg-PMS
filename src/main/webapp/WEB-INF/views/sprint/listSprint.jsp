<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h2>List of Sprint</h2>
<hr/>
<div class="">
    <c:if test="${not empty sprints}">
        <table class="table">
            <thead>
            <tr>
                <th>Sprint Id</th>
                <th>Title</th>
                <th>Description</th>
                <th>View/Edit WorkOrder</th>
                <th>Status</th>
                <th>Edit</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="sprint" items="${sprints}">
                <tr>
                    <td>${sprint.id}</td>
                    <td>
                        <c:url value="/sprint/detail/${sprint.id}" var="sprintViewUrl"/>
                        <a href="<c:out value='${sprintViewUrl}'/>">${sprint.title}</a></td>
                    <td>${sprint.description}</td>

                    <td>
                        <c:url value="/workorder/list/${sprint.id}" var="workOrderViewUrl"/>
                        <a class="fa fa-list" href="<c:out value='${workOrderViewUrl}'/>"></a>
                        &nbsp;&nbsp;
                        <c:url value="/orkorder/add/${sprint.id}" var="workOrderAddUrl"/>
                        <a class="fa fa-plus" href="<c:out value='${workOrderAddUrl}'/>"></a>
                    </td>
                    <td>${sprint.status}</td>
                    <td>
                        <c:url value="/sprint/edit/${sprint.id}" var="editUrl"/>
                        <a href="<c:out value='${editUrl}'/>" class="fa fa-edit"></a>
                    </td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
    </c:if>
</div>