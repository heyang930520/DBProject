package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.MD5;
import sql.Customer;
import sql.CustomerManager;

/**
 * Servlet implementation class Register
 * Registration process. Add a new row in the Customer table.
 * Jump to login.jsp if succeed.
 * @author yhe
 */
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Customer newUser = new Customer();
		MD5 md5Processor = new MD5();
		String name = request.getParameter("username");
		String password = request.getParameter("password");
		//System.out.println(name+ password);
		String md5Password = md5Processor.getMD5(password);
		newUser.setUserName(name);
		newUser.setUserPassword(md5Password);
		
		CustomerManager manager = new CustomerManager();
		int result = manager.register(newUser);
		if(result > 0){
			//System.out.println("Registration succeed.");
			response.sendRedirect("login.jsp");
		}else if(result == -1){
			//System.out.println("Registration failed.");
		}
	}

}
