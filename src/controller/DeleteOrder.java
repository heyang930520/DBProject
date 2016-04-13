package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sql.Orders;
import sql.OrdersManager;;

/**
 * Servlet implementation class DeleteOrder
 * Customer use this to delete his own order.
 * ordersListById will be reloaded if succeed.
 * Remain in myOrders.jsp if failed.
 * @author yhe
 */
public class DeleteOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteOrder() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		int orderedBy = (Integer) session.getAttribute("userId");
		int deleteOrderId = Integer.parseInt(request.getParameter("deleteOrderId"));
		
		OrdersManager manager = new OrdersManager();
		boolean result = manager.deleteOrder(deleteOrderId);
		if(result==true){
			//System.out.println("delete succeed.");
			List<Orders> newOrdersById= new ArrayList<Orders>();
			newOrdersById = manager.listOrdersByCustomerId(orderedBy);
			session.setAttribute("ordersListById", newOrdersById);
			response.sendRedirect("myOrders.jsp");
			return;
		} else{
			//System.out.println("delete failed.");
			response.sendRedirect("myOrders.jsp");
			return;
		}
	}

}
