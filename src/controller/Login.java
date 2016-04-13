package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import library.MD5;
import sql.CustomerManager;

/**
 * Servlet implementation class test
 * Login process. 
 * If succeed, and userId is 1, which means the user is admin,
 * the web page will be redirected to admin.jsp.
 * If succeed, and userId is not 1, which means the user is customer,
 * the web page will be redirected to customer.jsp.
 * Remain in login.jsp if failed.
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		MD5 md5Processor = new MD5();
		String name = request.getParameter("username");
		String password = request.getParameter("password");
		String md5Password = md5Processor.getMD5(password);
		
		CustomerManager manager = new CustomerManager();
		if(manager.login(name, md5Password) == true){			
			int userId = manager.findUserId(name);
			//System.out.println("Login Succeed. User id is"+userId);
			HttpSession session = request.getSession(true);
			session.setAttribute("userId", userId);
			
			if(userId == 1){
				response.sendRedirect("admin.jsp");
			}
			else{
				response.sendRedirect("customer.jsp");
			}
		}
		else{
			//System.out.println("Login faied");
			response.sendRedirect("login.jsp");
		}
	}

}
