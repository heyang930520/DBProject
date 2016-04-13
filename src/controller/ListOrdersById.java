package controller;

import java.util.ArrayList;
import java.util.List;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sql.Orders;
import sql.OrdersManager;;

/**
 * Servlet implementation class ListOrdersById
 * List a customer's orders given his id.
 * @author yhe
 */
public class ListOrdersById extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ListOrdersById() {
        super();
        // TODO Auto-generated constructor stub
    }

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub	
		HttpSession session = request.getSession(true);
		int orderedBy = (Integer) session.getAttribute("userId");
		OrdersManager manager = new OrdersManager();
		List<Orders> ordersListById = (ArrayList<Orders>) manager.listOrdersByCustomerId(orderedBy);
		session.setAttribute("ordersListById", ordersListById);
		//System.out.println(ordersListById.size());				
	}

}
