<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- 引入JSTL 核心库 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<title>课程信息显示</title>
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
					    	<h1 class="col-md-5">课程名单管理</h1>
							<form class="bs-example bs-example-form col-md-5" role="form" style="margin: 20px 0 10px 0;" action="CourseManageServlet.do" id="form1" method="post">
								<input type="hidden" name="action" value="search">
								
								<div class="input-group">
									<input type="text" class="form-control" placeholder="请输入课程名" value="${search}" name="search">
									<span class="input-group-addon btn" id="sub"><button>搜索</button></span>
								</div>
								
							</form>
							<a class="btn btn-default btn-success col-md-2" 
								style="margin-top: 20px" href="JSP/admin/addCourse.jsp">
								添加课程信息
								<sapn class="glyphicon glyphicon-plus"/>
							</a>

						</div>
				    </div>
				    <table class="table table-bordered">
					        <thead>
					            <tr>
									<th>课程号</th>
									<th>课程名称</th>
									<th>授课老师编号</th>
									<th>上课时间</th>
									<th>上课地点</th>
									<th>周数</th>
									<th>课程类型</th>
									<th>学分</th>
									<th>操作</th>
					            </tr>
					        </thead>
					        <tbody>
					        	<c:forEach items="${coulist}" var="cou">
								<tr>
									<td>${cou.courseID}</td>
									<td>${cou.courseName}</td>
									<td>${cou.teacherID}</td>
									<td>${cou.courseTime}</td>
									<td>${cou.classRoom}</td>
									<td>${cou.courseWeek}</td>
									<td>${cou.courseType}</td>
									<td>${cou.score}</td>
									<td>
										<button class="btn btn-default btn-xs btn-info" 
										onClick="location.href='CourseManageServlet.do?action=goEdit&id=${cou.courseID}'">修改</button>
										
										<button class="btn btn-default btn-xs btn-danger btn-primary" 
										onClick="confirmDelete(${cou.courseID})">删除</button>
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
									   <li><a href="CourseManageServlet.do?action=list&pageNumber=${page.pageNumber-1}">&laquo;上一页</a></li>
									</c:if>
									<!-- 循环显示 所有的页码   从1开始到总的页数 -->
									<c:forEach begin="1" end="${page.totalPage }" var="p">
									   <c:if test="${p == page.pageNumber}"><!-- 当前页被单击 -->
									      <li class="active"> <!-- 变蓝色 -->
									        <a href="CourseManageServlet.do?action=list&pageNumber=${p}">${p}</a>
									      </li>
									   </c:if>
									   
									   <c:if test="${p != page.pageNumber}">
									      <li>
									        <a href="CourseManageServlet.do?action=list&pageNumber=${p}">${p}</a>
									      </li>
									   </c:if>
									</c:forEach>
									<c:if test="${page.pageNumber == page.totalPage }">
									    <li><a href="#">下一页&raquo;</a></li>
									</c:if>
									<c:if test="${page.pageNumber != page.totalPage }">
									    <li><a href="CourseManageServlet.do?action=list&pageNumber=${page.pageNumber+1}">下一页&raquo;</a></li>
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
		<%--设置菜单中--%>
		<%-- $("#nav li:nth-child(1)").addClass("active")
        <c:if test="${pagingVO != null}">
        if (${pagingVO.curentPageNo} == ${pagingVO.totalCount}) {
            $(".pagination li:last-child").addClass("disabled")
        };

        if (${pagingVO.curentPageNo} == ${1}) {
            $(".pagination li:nth-child(1)").addClass("disabled")
        };
        </c:if>--%>

        $("#nav li:nth-child(1)").addClass("active")
	     function confirmDelete(id){
	    	 if(confirm("删除该课程，会同时删除所有已选课程的学生！您确定要删除吗？")==true){
   			 // 实现跳转
   			 location.href='CourseManageServlet.do?action=delete&id='+id;
   		 }else{
   			 return false;
   		 }
	     }

        $("#sub").click(function () {
            $("#form1").submit();
        });
	</script>
</html>