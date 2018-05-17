package com.lulu.servlet.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lulu.dao.StudentDAO;
import com.lulu.vo.Student;

/**
 * Servlet implementation class PasswordRestServlet
 */
@WebServlet("/PasswordRestServlet.do")
public class PasswordRestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    StudentDAO dao=new StudentDAO();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html,charset=UTF-8");
		
		if("goChange".equals(action)){
			
			request.getRequestDispatcher("/JSP/admin/passwordRest.jsp")
			.forward(request, response);
		}
		//******修改密码***************
		else if("change".equals(action)){
			HttpSession session=request.getSession();
			String username=session.getAttribute("USER").toString();
			String password=request.getParameter("password1");
			int count=dao.chanagePassword(password, username);
			
			request.setAttribute("password", password);
			if(count>0){
	    		//c重新执行查询
	    		response.sendRedirect("TeacherManageServlet.do?action=list");
	    	}
		}else if("goOther".equals(action)){//****跳转到其他用户修改密码的界面
			
			request.getRequestDispatcher("/JSP/admin/userPasswordRest.jsp")
			.forward(request, response);
			
		}else if("otherChange".equals(action)){//*****修改其他的用户的密码
			HttpSession session=request.getSession();
			String uname=session.getAttribute("USER").toString();
			String username=request.getParameter("username");
			String password=request.getParameter("password");
			
			if(!username.equals(uname)){
				int count=dao.chanagePassword(password, username);
				if(count>0){
		    		//c重新执行查询
		    		response.sendRedirect("TeacherManageServlet.do?action=list");
		    	}
				
			} else{
				request.setAttribute("message", "不能输入管理员账号！！");
				request.getRequestDispatcher("/JSP/admin/userPasswordRest.jsp").forward(request, response);
				
			}
			
			
			
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
