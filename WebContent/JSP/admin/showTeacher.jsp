<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<title>教师信息显示</title>
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
	
	<jsp:include page="top.jsp"/>

	<!-- 中间主体 -->
	<div class="container" id="content">
		<div class="row">
			<jsp:include page="menu.jsp"/>
			<div class="col-md-10">
				<div class="panel panel-default">
				    <div class="panel-heading">
						<div class="row">
					    	<h1 class="col-md-5">教师名单管理</h1>					    	
							<form class="bs-example bs-example-form col-md-5" role="form" 
								style="margin: 20px 0 10px 0;" action="TeacherManageServlet.do" id="form1" method="post">
								<input type="hidden" name="action" value="search">
								<div class="input-group">
									<input type="text" class="form-control" value="${search}" placeholder="请输入姓名" name="search">
									<span class="input-group-addon btn" " id="sub"><button>搜索</button></span>
								</div>
							</form>
							<a class="btn btn-default btn-success col-md-2" 
							style="margin-top: 20px" href="JSP/admin/addTeacher.jsp">
								添加教师信息
								<sapn class="glyphicon glyphicon-plus"/>
							</a>

						</div>
				    </div>
				    <table class="table table-bordered">
					        <thead>
					            <tr>
									<th>教师编号</th>
									<th>姓名</th>
									<th>性别</th>
									<th>出生年份</th>
									<th>学历</th>
									<th>职称</th>
									<th>入职年份</th>
									<th>学院</th>
									<th>操作</th>
					            </tr>
					        </thead>
					        <tbody>
								<c:forEach items="${tealist}" var="tea">
								<tr>
								    
									<td>${tea.userID}</td>
									<td>${tea.userName}</td>
									<td>${tea.sex}</td>
									<td>${tea.birthYear}</td>
									<td>${tea.degree}</td>
									<td>${tea.title}</td>
									<td>${tea.grade}</td>
									<td>${tea.collegeID}</td>
									
									<td>
										<button class="btn btn-default btn-xs btn-info" 
										onClick="location.href='TeacherManageServlet.do?action=goEdit&id=${tea.userID}'">修改</button>
										
										
										<button class="btn btn-default btn-xs btn-danger btn-primary" 
										onClick="confirmDelete(${tea.userID})">删除</button>
										<!--弹出框-->
									</td>
								</tr>
								</c:forEach>
					        </tbody>
				    </table>
				    <div class="panel-footer">
							<nav style="text-align: center">
								<ul class="pagination">
								    <c:if test="${page.pageNumber == 1}">
									   <li><a href="#">&laquo;上一页</a></li>
									</c:if>
									<c:if test="${page.pageNumber != 1}">
									   <li><a href="TeacherManageServlet.do?action=list&pageNumber=${page.pageNumber-1}">&laquo;上一页</a></li>
									</c:if>
									<!-- 循环显示 所有的页码   从1开始到总的页数 -->
									<c:forEach begin="1" end="${page.totalPage }" var="p">
									   <c:if test="${p == page.pageNumber}"><!-- 当前页被单击 -->
									      <li class="active"> <!-- 变蓝色 -->
									        <a href="TeacherManageServlet.do?action=list&pageNumber=${p}">${p}</a>
									      </li>
									   </c:if>
									   
									   <c:if test="${p != page.pageNumber}">
									      <li>
									        <a href="TeacherManageServlet.do?action=list&pageNumber=${p}">${p}</a>
									      </li>
									   </c:if>
									</c:forEach>
									<c:if test="${page.pageNumber == page.totalPage }">
									    <li><a href="#">下一页&raquo;</a></li>
									</c:if>
									<c:if test="${page.pageNumber != page.totalPage }">
									    <li><a href="TeacherManageServlet.do?action=list&pageNumber=${page.pageNumber+1}">下一页&raquo;</a></li>
									</c:if>
								</ul>
							</nav>
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
 	$("#nav li:nth-child(3)").addClass("active")
        function confirmDelete(id){
	    	 if(confirm("删除该老师，会同时删除所有已选课程！您确定要删除吗？")==true){
    			 // 实现跳转
    			 location.href='TeacherManageServlet.do?action=delete&id='+id;
    		 }else{
    			 return false;
    		 }
	     }

        $("#sub").click(function () {
            $("#form1").submit();
        });
	</script> 

</html>