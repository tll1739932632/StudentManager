<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="col-md-2">
    <ul class="nav nav-pills nav-stacked" id="nav">
        <li>
          <a href="${pageContext.request.contextPath}/CourseManageServlet.do?action=list">课程管理<span class="glyphicon glyphicon-folder-open pull-right"></span></a>
        </li>
        <li>
          <!-- http://localhost:8080/tcms -->
          <a href="${pageContext.request.contextPath}/StudentManageServlet.do?action=list">学生管理<span class="glyphicon glyphicon-equalizer pull-right"></span></a>
        </li>
        <li>
          <a href="${pageContext.request.contextPath}/TeacherManageServlet.do?action=list">教师管理<span class="glyphicon glyphicon-user pull-right"></span></a>
        </li>
        <li>
          <a href="${pageContext.request.contextPath}/PasswordRestServlet.do?action=goOther">账号密码重置<span class="glyphicon glyphicon-repeat pull-right"></span></a>
        </li>
        <li>
           <a href="${pageContext.request.contextPath}/PasswordRestServlet.do?action=goChange">修改密码<span class="glyphicon glyphicon-pencil pull-right"></span></a>
        </li>
        <li>
           <a href="${pageContext.request.contextPath}/LogoutServlet.do">退出系统<span class="glyphicon glyphicon-log-out pull-right"></span></a>
         </li>
    
    </ul>
</div>