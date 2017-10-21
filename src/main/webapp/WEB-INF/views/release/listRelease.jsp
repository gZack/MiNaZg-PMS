<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h2>List of Releases</h2>
<hr/>
<div class="">
    <c:if test="${not empty releases}">
        <table class="table">
            <thead>
            <tr>
                <th>Version Number</th>
                <th>Remark</th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="val" items="${releases}">
                <tr>
                    <td>${val.versionNumber}</td>
                    <td>${val.remark}</td>
                    <td>Edit</td>
                    <td>Delete</td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
    </c:if>
</div>