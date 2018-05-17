<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<title>修改学生信息</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<!-- 引入bootstrap -->
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
	<!-- 引入JQuery  bootstrap.js-->
	<script src="js/jquery-3.2.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</head>
<body>
	<!-- 顶栏 -->
<!-- 顶栏 -->
<jsp:include page="top.jsp"/>
	<!-- 中间主体 -->
		<div class="container" id="content">
		<div class="row">
			<jsp:include page="menu.jsp"/>
			<div class="col-md-10">
				<div class="panel panel-default">
				    <div class="panel-heading">
						<div class="row">
					    	<h1 style="text-align: center;">修改学生信息</h1>
						</div>
				    </div>
				    <div class="panel-body">
						<form class="form-horizontal" role="form" action="StudentManageServlet.do" id="editfrom" method="post">
							  <input type="hidden" name="action" value="update"/>
							  <div class="form-group ">
							    <label for="inputEmail3" class="col-sm-2 control-label" >学号</label>
							    <div class="col-sm-10">
							      <input readonly="readonly" value="${stu.userID}" type="number" class="form-control" id="inputEmail3" name="userid" placeholder="请输入学号">
							    </div>
							  </div>
							  <div class="form-group">
							    <label for="inputPassword3" class="col-sm-2 control-label">姓名</label>
							    <div class="col-sm-10">
							      <input type="text" value="${stu.userName}" class="form-control" id="inputPassword3" name="username" placeholder="请输入姓名">
							    </div>
							  </div>
							  <div class="form-group">
							    <label for="inputPassword3" class="col-sm-2 control-label">性别</label>
							    <div class="col-sm-10">
								    <label class="checkbox-inline">
								        <c:if test="${stu.sex eq '男' }">
									      	<input type="radio" name="sex" value="男" checked>男
									   	</c:if>
									   	<c:if test="${stu.sex ne '男' }">
									      	<input type="radio" name="sex" value="男">男
									   	</c:if>
									</label>
									<label class="checkbox-inline">
										<c:if test="${stu.sex eq '女' }">
									      	<input type="radio" name="sex" value="女" checked>女
									   	</c:if>
									   	<c:if test="${stu.sex ne '女' }">
									      	<input type="radio" name="sex" value="女">女
									   	</c:if>
									</label>
							    </div>
							  </div>
							  <div class="form-group">
							    <label for="inputPassword3" class="col-sm-2 control-label">出生年份</label>
							    <div class="col-sm-10">
								    <input type="date" value="${stu.birthYear}"name="birthyear"/>
							    </div>
							  </div>
							  <div class="form-group">
							    <label for="inputPassword3" class="col-sm-2 control-label" name="grade">入学时间</label>
							    <div class="col-sm-10">
								    <input type="date" value="${stu.grade}"  dateStyle="medium" pattern="yyyy-MM-dd"  name="grade"/>
							    </div>
							  </div>
							  <div class="form-group">
							    <label for="inputPassword3" class="col-sm-2 control-label" name="grade">所属院系</label>
							    <div class="col-sm-10">
								    <select class="form-control" name="collegeid" id="college">
										<option value="1" <c:if test="${stu.collegeID==1}">selected</c:if>>计算机学院</option>
                                        <option value="2" <c:if test="${stu.collegeID==2}">selected</c:if>>外语学院</option>
                                        <option value="3" <c:if test="${stu.collegeID==3}">selected</c:if>>数学院</option>
								    </select>
							    </div>
							  </div>
							  <div class="form-group" style="text-align: center">
								<button class="btn btn-default" type="submit">提交</button>
								<button class="btn btn-default" type="reset">重置</button>
							  </div>
						</form>
				    </div>

				</div>

			</div>
		</div>
	</div>
	<div class="container" id="footer">
	<div class="row">
		<div class="col-md-12"></div>
	</div>
	</div>
</body>
	<script type="text/javascript">
		$("#nav li:nth-child(2)").addClass("active")

        var collegeSelect = $("#college option");
        for (var i=0; i<collegeSelect.length; i++) {
            if (collegeSelect[i].value == '${student.collegeid}') {
                collegeSelect[i].selected = true;
            }
        }
	</script>
</html>						 
	