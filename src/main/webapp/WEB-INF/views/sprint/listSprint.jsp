<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h2>List of Sprint</h2>
<hr/>
<div class="">
    <c:if test="${not empty sprints}">
        <table class="table">
            <thead>
            <tr>
                <th>Id</th>
                <th>Title</th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="val" items="${sprints}">
                <tr>
                    <td>${val.id}</td>
                    <td>${val.title}</td>
                    <td>Edit</td>
                    <td>Delete</td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
    </c:if>
</div>