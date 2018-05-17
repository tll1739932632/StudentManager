package com.lulu.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lulu.dao.UserDAO;
import com.lulu.vo.UserLogin;
import com.sun.org.apache.bcel.internal.generic.DALOAD;

/**
 * 登录Servlet 
 * 
 * @version 1.0
 * @author 田露露
 */
@WebServlet("/LoginServlet.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	UserDAO dao= new UserDAO();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.接收界面上的数据
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		//2.实现业务逻辑，判断用户名是否正确；分析用户角色
		UserLogin user=dao.getUser(username, password);
		if(user==null){//用户名为空或者密码为空
			//请求中添加一个错误信息
			request.setAttribute("message", "用户名或者密码错误！");
			request.getRequestDispatcher("/login.jsp").forward(request, response);	
		}else if(user.getRole()==0){
			
			//response.sendRedirect("JSP/admin/showStudent.jsp");
			HttpSession session=request.getSession();
			session.setAttribute("USER", user.getUserName());
			response.sendRedirect("StudentManageServlet.do?action=list");
			
		}else if(user.getRole()==1){//教师账号
			
		}else if(user.getRole()==2){
			
		}
		
		//3.跳转
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
