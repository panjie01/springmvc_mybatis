<%--
  Created by IntelliJ IDEA.
  User: panjie
  Date: 2019/4/8
  Time: 15:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <button id="bu" type="button" >click</button>
    <script type="text/javascript">

        function sendMsg(){
            alert("hello i am go home");
        }

        function hello(callback) {
            callback();
        };

        document.getElementById("bu").onclick=function () {
            hello(sendMsg);
        };
    </script>
</body>
</html>
