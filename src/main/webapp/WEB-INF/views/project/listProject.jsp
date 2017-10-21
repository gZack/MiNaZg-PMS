<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h2>Projects</h2>
<hr/>
<div class="">
    <c:if test="${not empty projects}">
        <table class="table">
            <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Release</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
                <c:forEach var="val" items="${projects}">
                    <tr>
                        <td>${val.id}</td>
                        <td>${val.name}</td>
                        <td>
                            <c:url value="/release/list/${val.id}" var="releaseViewUrl"/>
                            <a class="fa fa-list" href="<c:out value='${releaseViewUrl}'/>"></a>
                            &nbsp;&nbsp;
                            <c:url value="/release/add/${val.id}" var="releaseAddUrl"/>
                            <a class="fa fa-plus" href="<c:out value='${releaseAddUrl}'/>"></a>
                        </td>
                        <td>
                            <c:url value="/project/edit/${val.id}" var="editUrl"/>
                            <a href="<c:out value='${editUrl}'/>" class="fa fa-edit"></a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
</div>