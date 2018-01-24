<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>index</title>
  </head>
  
  <body>
    <form action="login4" method="post">
          用户：<input type="text" name="name"><br><br>
          密码：<input type="text" name="password"><br><br>
          身高：<input type="text" name="shenggao"><br><br>
<!--           身高：<input type="text" name="height"><br><br> -->
        <input type="submit" value="确定">
    </form>
    
    <div>m1</div>
    <div id="m2">mmmm2</div>
    <img alt="aaa" src="timg.jpg">
  </body>
</html>