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
import sql.OrdersManager;

/**
 * Servlet implementation class ListOrders
 * List all the orders.
 * @author yhe
 */
public class ListAllOrders extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ListAllOrders() {
        super();
        // TODO Auto-generated constructor stub
    }

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		OrdersManager manager = new OrdersManager();
		List<Orders> allOrders = (ArrayList<Orders>) manager.listAllOrders();
		HttpSession session = request.getSession(true);
		session.setAttribute("allOrders", allOrders);
		//System.out.println(allOrders.size());
	}

}
