<%@ page import="com.example.LAB2.Results.Result" %>
<%@ page import="com.example.LAB2.Results.ListOfResult" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Студент: Ляо Ихун P3211</title>
    <link rel="stylesheet" type="text/css" href="Style.css">
    <script type="text/javascript" src="tools/graph.js"></script>

</head>
<body>
<script type="text/javascript">
    function gerR(){
        let list = document.getElementsByName("R");
        for(let k = 0; k<list.length; k++){
            if(list.item(k).checked === true){
                return list.item(k).value;
            }
        }
        return null;
    }
</script>

<h2 class = "hate">
    <p>Студент: Ляо Ихун  P3211 <br> Вариант: 12003</p>
</h2>

<form id = "form1" action="Controller">
    <div class = "wrapper">
        <!--first choose(can only one)-->
        <div class = "choice1">
            <p>
                Please choose value of R<span class = "required">*</span>:<br>
                <input type = radio value = "1" name = "R">1<br>
                <input type = radio value = "1.5" name = "R">1.5<br>
                <input type = radio value = "2" name = "R">2<br>
                <input type = radio value = "2.5" name = "R">2.5<br>
                <input type = radio value = "3" name = "R">3<br>
            </p>
        </div>

        <!--input by yourself-->
        <div class = "inputText">
            <p>Please input value of y<span class = "required">*</span>(-3,3)<br>
                <input id = "Y" type = text name = "Y"><br></p>
        </div>

        <!--submit-->
        <div class = "submit">
            Please choose value of x<span class = "required">*</span>(-5,3):<br>
            <button type="submit" value = "-4" name = "X" onclick="send('-4')">-4</button>
            <button type="submit" value = "-3" name = "X" onclick="send('-3')">-3</button>
            <button type="submit" value = "-2" name = "X" onclick="send('-2')">-2</button>
            <button type="submit" value = "-1" name = "X" onclick="send('-1')">-1</button>
            <button type="submit" value = "0" name = "X" onclick="send('0')">0</button>
            <button type="submit" value = "1" name = "X" onclick="send('1')">1</button>
            <button type="submit" value = "2" name = "X" onclick="send('2')">2</button>
            <button type="submit" value = "3" name = "X" onclick="send('3')">3</button>
            <button type="submit" value = "4" name = "X" onclick="send('4')">4</button>
        </div>
    </div>

    <div class = "Graph" >
        <%--
            ListOfResult result = new ListOfResult();
            if(session.getAttribute("result")!=null) {
                result = (ListOfResult) session.getAttribute("result");
            }
        --%>
        <canvas id = "graphClick" width = "350" height="300" style="border:2px solid" onclick = "doClick('graphClick',gerR())">
        </canvas>
        <script>draw("graphClick")</script>
        <jsp:include page="points.jsp"/>
        <%--<script>drawPoint("graphClick",
            <%=(result == null? null:result.getX())%>,
            <%=(result == null? null:result.getY())%>,
            <%=(result == null? null:result.getInRange())%>
        )</script>--%>
    </div>
</form>

    <div id = "result">
        <jsp:include page="table.jsp"/>
        <%--
            String mistake =(String) session.getAttribute("Mistake");
            if(mistake != null){
                response.getWriter().println(mistake);
            }
        --%>
    </div>
<script type="text/javascript">
    let i = "<%=request.getAttribute("Mistake")%>";
    if(i!=="null" && i!==""){
        alert(i);
    }
</script>
</body>
</html>