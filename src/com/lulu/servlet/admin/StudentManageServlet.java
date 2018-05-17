package com.lulu.servlet.admin;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lulu.dao.StudentDAO;
import com.lulu.util.Page;
import com.lulu.vo.Student;

/**
 * 实现学生信息管理（管理员）
 */
@WebServlet("/StudentManageServlet.do")
public class StudentManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      //引用dao
	StudentDAO dao= new StudentDAO();
	
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html,charset=UTF-8");
		
		
		if ("list".equals(action)) { // 列表学生信息
			Page page = new Page();
			if(request.getParameter("pageNumber")!=null){ //不是登录界面过来的话，就是翻页
				int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
				page.setPageNumber(pageNumber);
			}
			// 1. 查询所有学生信息
			List<Student> stuList = dao.getStudents(page.getPageNumber(),page.getPageSize());
			
			// 查询总的行数 并且保存到page里面
			page.setTotalCount(dao.getTotalCount());
			
			// 2. 跳转到 showStudent.jsp
			request.setAttribute("stuList", stuList); // 将查询的结果 存入request,带到界面去
			request.setAttribute("page", page);  // 分页信息 传到界面
			
			request.getRequestDispatcher("/JSP/admin/showStudent.jsp").forward(request, response);
		}
		else if("delete".equals(action)){//删除
	    	int id=Integer.parseInt(request.getParameter("id"));
	    	//执行删除
	    	int count = dao.deletById(id);
	    	
	    	if(count>0){
	    		//c重新执行查询
	    		response.sendRedirect("StudentManageServlet.do?action=list");
	    	}
	    	
	    }else if("add".equals(action)){//添加学生
	    	//获取界面
	    	String userName=request.getParameter("username");
	    	String sex=request.getParameter("sex");
	    	String birthYear=request.getParameter("birthyear");
	    	String grade=request.getParameter("grade");
	    	int collegeId=Integer.parseInt(request.getParameter("collegeid"));
	    	
	    	//调用DAO 执行insert语句
	    	Student s=new Student();
	    	s.setUserName(userName);
	    	s.setSex(sex);
	    	//java 字符串转化为日期模型
	    	SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd");
	    	try {
				s.setBirthYear(format.parse(birthYear));
				s.setGrade(format.parse(grade));
			} catch (ParseException e) {
				
				e.printStackTrace();
			}
	    	s.setCollegeID(collegeId);
	    	
	    	System.out.print(s.getUserName());
	    	
	    	 int count =dao.insert(s);
	    	 if(count>0){
	    		 
	    		 response.sendRedirect("StudentManageServlet.do?action=list");
	    	 }
	    }
	    else if("goEdit".equals(action)){
	    	//int id = Integer.parseInt();
	    	int id = Integer.parseInt(request.getParameter("id"));
			// 调用DAO 执行select 语句
			Student student = dao.getById(id);
			request.setAttribute("stu", student);// 将数据存入 请求，就可以被传到界面上了
			
			request.getRequestDispatcher("/JSP/admin/editStudent.jsp")
			.forward(request, response);
	    	
	    }else if("update".equals(action)){ // 实现学生信息更新
			int userId = Integer.parseInt(request.getParameter("userid"));
			String userName = request.getParameter("username");
			String sex = request.getParameter("sex");
			String birthYear = request.getParameter("birthyear");
			String grade = request.getParameter("grade");
			int collegeId = Integer.parseInt(request.getParameter("collegeid"));
			
			Student s = new Student();
			s.setUserID(userId);
			s.setUserName(userName);
			s.setSex(sex);
			// *****java 字符串如何转化为 日期类型*****
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			try {
				// 转换为日期类型
				s.setBirthYear(format.parse(birthYear));
				s.setGrade(format.parse(grade));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			s.setCollegeID(collegeId);
			
			// 调用dao中的方法 执行 update语句
			int count = dao.updateById(s);
			// 根据执行的结果进行跳转
			if(count>0){
				// 重新执行查询
				response.sendRedirect("StudentManageServlet.do?action=list");
			}
	    }
	    else if("search".equals(action)){//查询
	    	String search = request.getParameter("search");
			Page page = new Page();
			// 查询总的行数 ，并设置到page中
			page.setTotalCount(dao.getTotalCount(search));
			List<Student> stuList = dao.getStudents(page.getPageNumber(), page.getPageSize(), search);

			request.setAttribute("stuList", stuList);
			request.setAttribute("page", page);
			request.setAttribute("search", search); // ① 将查询条件回传界面上,显示到查询的文本框里面

			request.getRequestDispatcher("/JSP/admin/showStudent.jsp").forward(request, response);
	    	
	    }
	    else if("changeOther".equals(action)){
	    	
	    	HttpSession session=request.getSession();
			String username=request.getParameter("username");
			String password=request.getParameter("password");
			dao.chanagePassword(password, username);
			session.setAttribute("password", password);
			request.getRequestDispatcher("/JSP/admin/userPasswordRest.jsp")
			.forward(request, response);
	    	
	    }
		
	}

	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
