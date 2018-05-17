<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title></title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<!-- 引入bootstrap -->
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
	<!-- 引入JQuery  bootstrap.js-->
	<script src="js/jquery-3.2.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<style type="text/css">
	body{
	   background: url(images/aa.jpg)repeat;
	}
	#login-box {
		/*border:1px solid #F00;*/
		padding: 35px;
		border-radius:15px;
		background: #56666B;
		color: #fff;
	}

	</style>
</head>
<body>
	<div class="container" id="top">
				<div  style="font-size: 70px;font: bold;font-family: '微软雅黑'; text-align: center; margin-top: 50px;color: gray;    ">高校智能教务管理系统</div>
		<div class="row" style="margin-top: 100px; ">				
			<div class="col-md-4"></div>
			<div class="col-md-4" id="login-box">
			 <span style="color:red">${message}</span>
				<form class="form-horizontal" role="form" action="LoginServlet.do" id="loginForm" method="post">
				  <div class="form-group">
				    <label for="firstname" class="col-sm-3 control-label">账号：</label>
				    <div class="col-sm-9">
				      <input type="text" class="form-control" id="userID" placeholder="请输入名字" name="username">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="lastname" class="col-sm-3 control-label">密码：</label>
				    <div class="col-sm-9">
				      <input type="password" class="form-control" id="password" placeholder="请输入密码" name="password">
				    </div>
				  </div>
				  <div class="form-group">
				    <div class="col-sm-offset-3 col-sm-9">
				      <button type="submit" class="btn btn-default btn-info" style="width:100%;">登录</button>
				    </div>
				  </div>
				</form>
			</div>
			<div class="col-md-4"></div>
		</div>		
	</div>
</body>
</html>