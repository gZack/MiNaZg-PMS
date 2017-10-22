<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1 style="align-content: center">${sprint.title}</h1>
<hr/>
<p>Description: ${sprint.description}</p>
<p>Status: ${sprint.status}</p>

<p>Start Date: <fmt:formatDate value="${sprint.startDate}" var="formattedStartDate"
                    type="date" pattern="MM-dd-yyyy" />
    ${formattedStartDate}
</p>

<p>Start Date: <fmt:formatDate value="${sprint.startDate}" var="formattedEndDate"
                    type="date" pattern="MM-dd-yyyy" />
    ${formattedEndDate}
</p>





