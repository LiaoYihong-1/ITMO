<%--
  Created by IntelliJ IDEA.
  User: x1761
  Date: 2021/10/26
  Time: 17:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" type="text/css" href="table.css">
<jsp:useBean id="list" scope="session" class="com.example.LAB2.Results.ListOfResult"/>
        <table border="1">
            <thead>
            <tr>
                <th>x</th>
                <th>y</th>
                <th>r</th>
                <th>result</th>
            </tr>
            </thead>
            <tbody>
                <c:forEach var="result" items="${list.results}">
                    <tr>
                        <th>
                            ${result.corX}
                        </th>
                        <th>
                            ${result.corY}
                        </th>
                        <th>
                            ${result.r}
                        </th>
                        <th>
                            ${result.text}
                        </th>

                    </tr>
                </c:forEach>
            </tbody>
        </table>
