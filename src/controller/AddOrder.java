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
 * Servlet implementation class AddOrder
 * A customer use this function to add a new order in customer.jsp.
 * redirect the page to myOrders.jsp if succeed and ordersListById will be reloaded.
 * Remain in customer.jsp if failed.
 * @author yhe
 */
public class AddOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddOrder() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Orders newOrder = new Orders();
		int orderedBy = Integer.parseInt(request.getParameter("userId"));
		int orderBuy = Integer.parseInt(request.getParameter("productId"));
		int orderAmount = Integer.parseInt(request.getParameter("productAmount"));
		
		newOrder.setOrderedBy(orderedBy);
		newOrder.setOrderBuy(orderBuy);
		newOrder.setOrderAmount(orderAmount);
		
		OrdersManager manager = new OrdersManager();
		boolean result = manager.addNewOrder(newOrder);
		if(result==true){
			//System.out.println("Add succeed.");
			HttpSession session = request.getSession(true);
			List<Orders> newOrdersById = new ArrayList<Orders>();
			newOrdersById = manager.listOrdersByCustomerId(orderedBy);
			session.setAttribute("ordersListById", newOrdersById);
			response.sendRedirect("myOrders.jsp");
			return;
		} else{
			//System.out.println("Add failed.");
			response.sendRedirect("customer.jsp");
			return;
		}
	}

}
