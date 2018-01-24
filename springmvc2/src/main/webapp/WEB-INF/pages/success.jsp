<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>success</title>
  	 <script type="text/javascript" src="<%=path %>/js/jquery-1.7.1.min.js"></script>
  </head>
  
  <body>
    ${name},success. <br>
    用户名：${name},密码：${password}</br>
    上:${joan.password }<br/>
    s:${animal }
    <div id="d1">d11111</div>
    <div id="d2">d22222</div>
<!-- 	<img alt="天空" src="img/timg.jpg"> -->
	<script type="text/javascript">
    jQuery(function($){
    	$("#d2").bind("click",function(){
    		//var obj ={"user":{"name":"pig","id":32,"height":"23","password":"pwd"},"Animal":{"mm":"we"}};
    		var obj ={user:{name:'pig',id:32,height:'23',password:'pwd'}} ;
    		obj.animal={mm:'we'};
    		//alert(obj);
    		//alert("--"+JSON.stringify(obj)+"--");
    		$.ajax({
    			   url: "myajax4",
    			   type: "POST",
    			   contentType : 'application/json;charset=utf-8',
    			   data:JSON.stringify(obj),
    			   success: function(result){
    			     alert( "Data Saved: " + result[0].name);
    			   }
    		}); 
    	});
    });
	</script>
  </body>
</html>