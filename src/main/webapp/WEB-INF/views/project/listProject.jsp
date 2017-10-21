<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h2>List Project</h2>
<hr/>
<div class="">
    <c:if test="${not empty projects}">
        <table class="table">
            <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <tbody>
        <c:forEach var="val" items="${projects}">
            <tr>
                <td>${val.id}</td>
                <td>${val.name}</td>
                <td>Edit</td>
                <td>Delete</td>
            </tr>
        </c:forEach>

            </tbody>
        </table>
    </c:if>
</div>