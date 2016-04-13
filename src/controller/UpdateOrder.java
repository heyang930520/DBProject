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
import sql.Product;
import sql.ProductManager;

/**
 * Servlet implementation class UpdateOrder
 * Admin use this to update the order status.
 * allOrders will be reloaded if succeed.
 * Remain in orders.jsp if failed.
 * @author yhe
 */
public class UpdateOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdateOrder() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int orderId = Integer.parseInt(request.getParameter("updateOrderId"));
		String orderStatus = request.getParameter("updateOrderStatus");
		
		OrdersManager manager = new OrdersManager();
		boolean result = manager.updateOrderStatus(orderId, orderStatus);
		if(result==true){
			//System.out.println("update succeed.");
			HttpSession session = request.getSession(true);
			List<Orders> newAllOrders= new ArrayList<Orders>();
			newAllOrders = manager.listAllOrders();
			session.setAttribute("allOrders", newAllOrders);
			response.sendRedirect("orders.jsp");
			return;
		} else{
			//System.out.println("update failed.");
			response.sendRedirect("orders.jsp");
			return;
		}
	}

}
