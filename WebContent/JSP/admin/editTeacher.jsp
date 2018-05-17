<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<title>修改教师信息</title>
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
					    	<h1 style="text-align: center;">修改教师信息</h1>
						</div>
				    </div>
				    <div class="panel-body"><!-- 转到教师管理服务 avtion=update -->
						<form class="form-horizontal" role="form" action="TeacherManageServlet.do" id="editfrom" method="post">
							   <input type="hidden" name="action" value="update"/>
							  <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label">工号</label>
							    <div class="col-sm-10">
							      <input readonly="readonly" value="${tea.userID}" type="number" class="form-control" id="inputEmail3" name="userid" placeholder="请输入工号">
							    </div>
							  </div>
							  <div class="form-group">
							    <label for="inputPassword3" class="col-sm-2 control-label">姓名</label>
							    <div class="col-sm-10">
							      <input type="text" value="${tea.userName}" class="form-control" id="inputPassword3" name="username" placeholder="请输入姓名">
							    </div>
							  </div>
							  <div class="form-group">
							    <label for="inputPassword3" class="col-sm-2 control-label">性别</label>
							    <div class="col-sm-10">
								    <label class="checkbox-inline">
									   	<c:if test="${tea.sex eq '男' }">
									      	<input type="radio" name="sex" value="男" checked>男
									   	</c:if>
									   	<c:if test="${tea.sex ne '男' }">
									      	<input type="radio" name="sex" value="男">男
									   	</c:if>
									</label>
									<label class="checkbox-inline">
										<c:if test="${tea.sex eq '女' }">
									      	<input type="radio" name="sex" value="女" checked>女
									   	</c:if>
									   	<c:if test="${tea.sex ne '女' }">
									      	<input type="radio" name="sex" value="女">女
									   	</c:if>
									</label>
							    </div>
							  </div>
							  <div class="form-group">
							    <label for="inputPassword3" class="col-sm-2 control-label">出生年份</label>
							    <div class="col-sm-10">
								    <input type="date" value="${tea.birthYear}"  name="birthyear"/>
							    </div>
							  </div>
							  <div class="form-group">
								<label for="inputPassword3" class="col-sm-2 control-label" name="degree">学历：</label>
								<div class="col-sm-10">
									<select class="form-control" name="degree" id="degree">
										<option value="本科" <c:if test="${tea.degree=='本科'}">selected</c:if>>本科</option>
										<option value="硕士" <c:if test="${tea.degree=='硕士'}">selected</c:if>>硕士</option>
										<option value="博士" <c:if test="${tea.degree=='博士'}">selected</c:if>>博士</option>
									</select>
								</div>
							  </div>
							<div class="form-group">
								<label for="inputPassword3" class="col-sm-2 control-label" name="title" >职称：</label>
								<div class="col-sm-10">
									<select class="form-control" name="title" id="title">
										<option value="普通教师" <c:if test="${tea.title=='普通教师'}">selected</c:if>>普通教师</option>
										<option value="助教"   <c:if test="${tea.title=='助教'}">selected</c:if>>助教</option>
										<option value="讲师"   <c:if test="${tea.title=='讲师'}">selected</c:if>>讲师</option>
										<option value="副教授" <c:if test="${tea.title=='副教授'}">selected</c:if>>副教授</option>
										<option value="教授"  <c:if test="${tea.title=='教授'}">selected</c:if>>教授</option>
									</select>
								</div>
							</div>
							  <div class="form-group">
							    <label for="inputPassword3" class="col-sm-2 control-label" >入职时间</label>
							    <div class="col-sm-10">
								    <input type="date" value="${tea.grade}" dateStyle="medium" pattern="yyyy-MM-dd"  name="grade"/>
							    </div>
							  </div>
							  <div class="form-group">
							    <label for="inputPassword3" class="col-sm-2 control-label" name="grade">所属院系</label>
							    <div class="col-sm-10">
								    <select class="form-control" name="collegeid" id="college">
										<option value="1" <c:if test="${tea.collegeID==1}">selected</c:if>>计算机学院</option>
                                        <option value="2" <c:if test="${tea.collegeID==2}">selected</c:if>>外语学院</option>
                                        <option value="3" <c:if test="${tea.collegeID==3}">selected</c:if>>数学院</option>
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
		$("#nav li:nth-child(3)").addClass("active");

        var collegeSelect = $("#college option");
        for (var i=0; i<collegeSelect.length; i++) {
            if (collegeSelect[i].value == '${teacher.collegeid}') {
                collegeSelect[i].selected = true;
            }
        }

        var degreeSelect = $("#degree option");
        var titleSelect = $("#title option");

        for (var i=0; i<degreeSelect.length; i++) {
            if (degreeSelect[i].value == '${teacher.degree}') {
                degreeSelect[i].selected = true;
            }
        }
        for (var i=0; i<titleSelect.length; i++) {
            if (titleSelect[i].value == '${teacher.title}') {
                titleSelect[i].selected = true;
            }
        }

	</script>
</html>