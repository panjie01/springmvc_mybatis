<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <title>index-jsp</title>
</head>
<body>
    ${pageContext.request.contextPath}/123
    <form action="hello/second" method="post">
        <input type="text" name="username" value="张三"/>
        <input type="password" name="password" value="23456"/>
        <input type="submit" value="提交" id='submit'>
    </form>
    <button name="button" id="bu" type="button">按钮</button>
    <%
        System.out.println("this is index.jsp");
        %>
<script type="text/javascript">

    $("#bu").click(function() {

        $.ajax({
            url:"hello/first",
            data:
                {dictTypeCode:"001"},
            type:"post",
            dataType:"json",
            success:function(msg) {
                alert(JSON.stringify(msg));
                // $(msg).each(function() {
                //     /* $("#industry").append("<option value='"+this.dictId+"'>"+this.dictItemName +"</option> ") */
                //     $("#industry").append("<option value="+this.dictId+">"+this.dictItemName+"</option>")
                // });
            }
        });

    });

</script>

</body>
</html>