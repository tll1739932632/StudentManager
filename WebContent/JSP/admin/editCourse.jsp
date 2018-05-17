<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<title>修改课程信息</title>
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
					    	<h1 style="text-align: center;">修改课程信息</h1>
						</div>
				    </div>
				    <div class="panel-body">
						<form class="form-horizontal" role="form" action="CourseManageServlet.do" id="editfrom" method="post">
							  <input type="hidden" name="action" value="update"/>
							   <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label">课程号</label>
							    <div class="col-sm-10">
							      <input readonly="readonly" value="${cou.courseID}"  type="number" class="form-control" id="inputEmail3"  name="courseid" placeholder="请输入课程号">
							    </div>
							  </div>
							  <div class="form-group">
							    <label for="inputPassword3" class="col-sm-2 control-label">课程名称</label>
									<div class="col-sm-10">
							      <input type="text" class="form-control" value="${cou.courseName}" id="inputPassword3" name="coursename"  placeholder="请输入课程名称">
							    </div>
							  </div>
							  <div class="form-group">
								  <label for="inputPassword3" class="col-sm-2 control-label" name="grade">授课老师编号</label>
								  <div class="col-sm-10">
									  <select class="form-control" name="teacherid" id="teacherid">
										<option value="1001" <c:if test="${cou.teacherID==1001}">selected</c:if> >1001</option>
										<option value="1002" <c:if test="${cou.teacherID==1002}">selected</c:if> >1002</option>
										<option value="1003" <c:if test="${cou.teacherID==1003}">selected</c:if> >1003</option>
									  </select>
								  </div>
							  </div>
							<div class="form-group">
								<label for="inputPassword3" class="col-sm-2 control-label">上课时间</label>
								<div class="col-sm-10">
									<input type="text" value="${cou.courseTime}" class="form-control" name="coursetime"  placeholder="请输入上课时间">
								</div>
							</div>
							<div class="form-group">
								<label for="inputPassword3" class="col-sm-2 control-label">上课地点</label>
								<div class="col-sm-10">
									<input type="text" value="${cou.classRoom}" class="form-control" name="classroom"  placeholder="上课地点">
								</div>
							</div>
							<div class="form-group">
								<label for="inputEmail3"  class="col-sm-2 control-label">周数</label>
								<div class="col-sm-10">
									<input type="number" value="${cou.courseWeek}" class="form-control" name="courseweek"  placeholder="请输入周数">
								</div>
							</div>
							<div class="form-group">
								<label for="inputPassword3" class="col-sm-2 control-label" name="coursetype">课程的类型：</label>
								<div class="col-sm-10">
									<select class="form-control" name="coursetype" id="coursetype">
										<option value="必修课" <c:if test="${cou.courseType=='必修课'}">selected</c:if> >必修课</option>
										<option value="选修课" <c:if test="${cou.courseType=='选修课'}">selected</c:if> >选修课</option>
										<option value="公共课" <c:if test="${cou.courseType=='公共课'}">selected</c:if> >公共课</option>
									</select>
								</div>
							</div>
							<!--  <div class="form-group">
								<label for="inputPassword3" class="col-sm-2 control-label" name="grade">所属院系</label>
								<div class="col-sm-10">
									<select class="form-control" name="collegeid">
										<option >计算机学院</option>
                                        <option >外语学院</option>
                                        <option >数学院</option>
									</select>
								</div>
							</div>-->
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-2 control-label">学分：</label>
								<div class="col-sm-10">
									<input type="number" value="${cou.score}" class="form-control" name="score" placeholder="请输入学分">
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
		$("#nav li:nth-child(1)").addClass("active")

        var collegeSelect = $("#college option");
        for (var i=0; i<collegeSelect.length; i++) {
            if (collegeSelect[i].value == '${course.collegeid}') {
                collegeSelect[i].selected = true;
            }
        }

        var degreeSelect = $("#coursetype option");
        for (var i=0; i<coursetypeSelect.length; i++) {
            if (coursetypeSelect[i].value == '${course.coursetype}') {
                coursetypeSelect[i].selected = true;
            }
        }

        var teacheridSelect = $("#teacherid option");
        for (var i=0; i<teacheridSelect.length; i++) {
            if (teacheridSelect[i].value == '${course.teacherid}') {
                teacheridSelect[i].selected = true;
            }
        }

	</script>
</html>