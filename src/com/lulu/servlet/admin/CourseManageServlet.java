package com.lulu.servlet.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lulu.dao.CourseDAO;
import com.lulu.dao.StudentDAO;
import com.lulu.util.Page;
import com.lulu.vo.Course;
import com.lulu.vo.Student;
import com.sun.corba.se.spi.activation.Repository;

/**
 * 课程管理
 */
@WebServlet("/CourseManageServlet.do")
public class CourseManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
        
	CourseDAO dao=new CourseDAO();//实例化dao 方便调用dao里的变量和方法
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html,charset=UTF-8");
		//***显示课程信息***********************
		if("list".equals(action)){
			Page page=new Page();
			if(request.getParameter("pageNumber")!=null){
				int pageNumber=Integer.parseInt(request.getParameter("pageNumber"));
				page.setPageNumber(pageNumber);
				
			}
			//1.查询所有课程
			List<Course> coulist=dao.getCourses(page.getPageNumber(), page.getPageSize());
			//查询总行数
			page.setTotalCount(dao.getTotalCount());
			
			//2.跳转			
			request.setAttribute("coulist", coulist);//课程信息内容传到界面
			request.setAttribute("page", page);  // 分页信息 传到界面
			request.getRequestDispatcher("/JSP/admin/showCourse.jsp").forward(request, response);;
		}//****删除课程信息********************************
		else if("delete".equals(action)){
			int id=Integer.parseInt(request.getParameter("id"));
	    	//执行删除
	    	int count = dao.deletById(id);
	    	
	    	if(count>0){
	    		//c重新执行查询
	    		response.sendRedirect("CourseManageServlet.do?action=list");
	    	}
			
		}//****添加课程******************************
		else if("add".equals(action)){
			//获取界面
			String coursename=request.getParameter("coursename");
			int teacherid=Integer.parseInt(request.getParameter("teacherid"));
			String coursetime=request.getParameter("coursetime");
			String classroom=request.getParameter("classroom");
			int courseweek=Integer.parseInt(request.getParameter("courseweek"));
			String coursetype=request.getParameter("coursetype");
			int score=Integer.parseInt(request.getParameter("score"));
			//调用dao 执行insert语句
			Course c=new Course();
			c.setCourseName(coursename);
			c.setTeacherID(teacherid);
			c.setCourseTime(coursetime);
			c.setClassRoom(classroom);
			c.setCourseWeek(courseweek);
			c.setCourseType(coursetype);
			c.setScore(score);
			
			int count =dao.insert(c);
	    	 if(count>0){	    		 
	    		 response.sendRedirect("CourseManageServlet.do?action=list");
	    	 }
		}//*******跳转到修改课程的页面*****************************
		else if("goEdit".equals(action)){
			int id=Integer.parseInt(request.getParameter("id"));
			//调用DAO 执行select 语句
			Course c=dao.getById(id);
			request.setAttribute("cou", c);
			
			request.getRequestDispatcher("/JSP/admin/editCourse.jsp").forward(request, response);;
			
		}//********在修改课程页面实现更新课程信息****************************
		else if("update".equals(action)){
			//获取页面
			int courseID=Integer.parseInt(request.getParameter("courseid"));
			String coursename=request.getParameter("coursename");
			int teacherid=Integer.parseInt(request.getParameter("teacherid"));
			String coursetime=request.getParameter("coursetime");
			String classroom=request.getParameter("classroom");
			int courseweek=Integer.parseInt(request.getParameter("courseweek"));
			String coursetype=request.getParameter("coursetype");
			int score=Integer.parseInt(request.getParameter("score"));
			
			Course c= new Course();
			c.setCourseID(courseID);
			c.setCourseName(coursename);
			c.setTeacherID(teacherid);
			c.setCourseTime(coursetime);
			c.setClassRoom(classroom);
			c.setCourseWeek(courseweek);
			c.setCourseType(coursetype);
			c.setScore(score);
			
			int count =dao.updateById(c);
			if(count>0){
				response.sendRedirect("CourseManageServlet.do?action=list");
			}
			
		}//*************实现课程的搜索功能********************
		else if("search".equals(action)){
			String search = request.getParameter("search");
			Page page = new Page();
			// 查询总的行数 ，并设置到page中
			page.setTotalCount(dao.getTotalCount(search));
			List<Course> coulist = dao.getCourses(page.getPageNumber(), page.getPageSize(), search);

			request.setAttribute("coulist", coulist);
			request.setAttribute("page", page);
			request.setAttribute("search", search); // ① 将查询条件回传界面上,显示到查询的文本框里面

			request.getRequestDispatcher("/JSP/admin/showCourse.jsp").forward(request, response);
		}
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
