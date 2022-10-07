<%--
  Created by IntelliJ IDEA.
  User: x1761
  Date: 2021/10/27
  Time: 23:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="list" scope="session" class="com.example.LAB2.Results.ListOfResult"/>
<c:forEach var="result" items="${list.results}">
    <script>
        drawPoint('graphClick', ${result.x}, ${result.y},${result.inRange});
    </script>
</c:forEach>