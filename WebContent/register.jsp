<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册界面</title>
<script type="text/javascript"  src="js/jquery-3.2.1.min.js"></script>
</head>
<body>
   <form id="f1">
   		<input id="username" type="text" name="username"><span id="userMessage"></span><br>
   		<input id="password" type="password" name="password"><br>
   		<input type="button" value="注册">
   </form>
   
   <script type="text/javascript">
    	$(document).ready(function(){
    		$("#username").blur(function(){
    			
    			$.ajax({
    				
    				url:'CheckUsernameServlet.do',
    				type:'POST',//请求提交方式
					dataType:'text', //响应的数据的类型.xml,json,text
					data:$("#f1").serialize(),  // 需要提交的数据
					success:function(data){
						// 如果整个ajax过程中没有错误，自动调用success的function
						// data 参数名字可以自己定义，自动接收后台传回来的数据
						// 将data中的数据，显示到界面上
						
						if(data=='E101'){
							$("#userMessage").html("用户已存在").css("color:red");
						};
						if(data=='E100'){
							$("#userMessage").html("可以使用").css("color:green");
						};
					},   
					error:function(){
						alert("啊哦，出错了！");
					}

    			});
    			
    		});
    		
    	})
   
   </script>
</body>
</html>