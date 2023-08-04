<%--
  Created by IntelliJ IDEA.
  User: x1761
  Date: 2021/10/25
  Time: 22:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="result.css">
    <title>Result</title>
</head>
<body>
<div class = show>
<svg class="graph" xmlns="http://www.w3.org/2000/svg" height=300 width=350 fill="white">
    <%--for the first qudrant--%>
    <polygon fill="orange" points="175,150 275,150 175,50"/>
    <%--this is for the second quadrant--%>
    <rect fill="orange" width="100" height="100" x="75" y="50"/>
    <%--this is for the third quadrant--%>
    <path d="M75 150 A100 100 0 0 0 175 250 L175 250 175 150 L 175 150 75 150 Z"  fill="orange"/>
    <%--add a frame--%>
    <line x1 = "0" y1="0" x2="0" y2="300" style ="stroke: black ;stroke-width: 3px"/>
    <line x1 = "0" y1="0" x2="350" y2="0" style = "stroke: black ;stroke-width: 3px"/>
    <line x1 = "350" y1="0" x2="350" y2="300" style = "stroke: black ;stroke-width: 3px"/>
    <line x1 = "350" y1="300" x2="0" y2="300" style = "stroke: black ;stroke-width: 3px"/>
    <line x1 = "0" y1="150" x2="350" y2="150" style = "stroke: black ;stroke-width: 2px"/>
    <line x1 = "175" y1="0" x2="175" y2="300" style = "stroke: black ;stroke-width: 2px"/>
    <%--add arrow--%>
    <line x1 = "175" y1="0" x2="168" y2="7" style = "stroke: black ;stroke-width: 2px"/>
    <line x1 = "175" y1="0" x2="182" y2="7" style = "stroke: black ;stroke-width: 2px"/>
    <line x1 = "350" y1="150" x2="343" y2="143" style = "stroke: black ;stroke-width: 2px"/>
    <line x1 = "350" y1="150" x2="343" y2="157" style = "stroke: black ;stroke-width: 2px"/>
    <%--coordinates--%>
    <line x1 = "125" y1="147.5" x2="125" y2="152.5" style = "stroke: black ;stroke-width: 2px"/>
    <text x="110" y="165" font-size="15px" fill="black">-R/2</text>
    <line x1 = "75" y1="147.5" x2="75" y2="152.5" style = "stroke: black ;stroke-width: 2px"/>
    <text x="65" y="165" font-size="15px" fill="black">-R</text>
    <line x1 = "225" y1="147.5" x2="225" y2="152.5" style = "stroke: black ;stroke-width: 2px"/>
    <text x="215" y="165" font-size="15px" fill="black">R/2</text>
    <line x1 = "275" y1="147.5" x2="275" y2="152.5" style = "stroke: black ;stroke-width: 2px"/>
    <text x="270" y="165" font-size="15px" fill="black">R</text>
    <line x1 = "172.5" y1="100" x2 = "177.5" y2="100" style = "stroke: black; stroke-width: 2px"/>
    <text x = "180" y = "105" font-size="15px" fill="black">R/2</text>
    <line x1 = "172.5" y1="50" x2 = "177.5" y2="50" style = "stroke: black; stroke-width: 2px"/>
    <text x = "180" y = "55" font-size="15px" fill="black">R</text>
    <line x1 = "172.5" y1="200" x2 = "177.5" y2="200" style = "stroke: black; stroke-width: 2px"/>
    <text x = "180" y = "205" font-size="15px" fill="black">-R/2</text>
    <line x1 = "172.5" y1="250" x2 = "177.5" y2="250" style = "stroke: black; stroke-width: 2px"/>
    <text x = "180" y = "255" font-size="15px" fill="black">-R</text>
    <%--result point--%>
    <%boolean in = session.getAttribute("inRange").equals("yes");
      String color = "";
        if(in){
            color = "green";
        }else {
            color = "blue";
        }
    %>
    <circle r="3.0" cx ="<%=session.getAttribute("xGraph")%>" cy="<%=session.getAttribute("yGraph")%>" fill="<%=color%>"  ></circle>
</svg>
<div class = "next">
    <a href = index.jsp>Link for a new require</a>
</div>
<div>
<div class = "result">
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
        <td><%=session.getAttribute("x")%></td>
        <td><%=session.getAttribute("y")%></td>
        <td><%=session.getAttribute("r")%></td>
        <td><%=session.getAttribute("inRange")%></td>
    </tbody>
</table>
</div>
</div>
</div>
</body>
</html>
