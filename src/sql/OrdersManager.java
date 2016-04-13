package sql;

/**
 * Functions on the Orders table.
 * @author yhe
 * 
 */
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import sql.Orders;
import sql.SqlConnector;

public class OrdersManager {
	SqlConnector sqlConnector = new SqlConnector();
	
	//List all the orders.
	public List<Orders> listAllOrders(){
		Connection conn = sqlConnector.sqlConnect();
		List<Orders> allOrders = new ArrayList<Orders>();
		try{
			String sql = "select * from Orders";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				int orderId = rs.getInt("id");
				int orderedBy = rs.getInt("orderedBy");
				int orderBuy = rs.getInt("orderBuy");
				int orderAmount = rs.getInt("orderAmount");
				String orderStatus = rs.getString("orderStatus");
				
				Orders order = new Orders();
				order.setOrderId(orderId);
				order.setOrderedBy(orderedBy);
				order.setOrderBuy(orderBuy);
				order.setOrderAmount(orderAmount);
				order.setOrderStatus(orderStatus);
				allOrders.add(order);
			}
			conn.close();
		} catch(SQLException e){
			e.printStackTrace();
		}
		return allOrders;
	}
	
	//List a customer's orders.
	public List<Orders> listOrdersByCustomerId(int orderedBy){
		Connection conn = sqlConnector.sqlConnect();
		List<Orders> customerOrders = new ArrayList<Orders>();
		try{
			String sql = "select * from Orders where orderedBy='"+orderedBy+"'";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				int orderId = rs.getInt("id");
				int orderBuy = rs.getInt("orderBuy");
				int orderAmount = rs.getInt("orderAmount");
				String orderStatus = rs.getString("orderStatus");
				
				Orders order = new Orders();
				order.setOrderId(orderId);
				order.setOrderedBy(orderedBy);
				order.setOrderBuy(orderBuy);
				order.setOrderAmount(orderAmount);
				order.setOrderStatus(orderStatus);
				customerOrders.add(order);
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return customerOrders;
	}
	
	//Add a new order.
	public boolean addNewOrder(Orders order){
		Connection conn = sqlConnector.sqlConnect();
		try{
			String preSql = "alter table Orders AUTO_INCREMENT = 1";
			Statement preSt = conn.createStatement();
			preSt.executeUpdate(preSql);
			String sql = "insert into Orders(orderedBy, orderBuy, orderAmount) values('"+order.getOrderedBy()+"', '"+order.getOrderBuy()+"', '"+order.getOrderAmount()+"')";
			Statement st = conn.createStatement();
			st.executeUpdate(sql);
			//System.out.println("Add new order succeed.");
			conn.close();
			return true;
		} catch(SQLException e){
			e.printStackTrace();
			return false;
		}
	}
	
	//Update the order's status.
	public boolean updateOrderStatus(int orderId, String orderStatus){
		Connection conn = sqlConnector.sqlConnect();
		try{
			String sql = "update Orders set orderStatus='"+orderStatus+"' where id='"+orderId+"'";
			Statement st = conn.createStatement();
			st.executeUpdate(sql);
			//System.out.println("Update status succeed.");
			conn.close();
			return true;
		} catch(SQLException e){
			e.printStackTrace();
			return false;
		}
	}
	
	//Delete an order.
	public boolean deleteOrder(int orderId){
		Connection conn = sqlConnector.sqlConnect();
		try{
			String sql = "delete from Orders where id='"+orderId+"'";
			Statement st = conn.createStatement();
			st.executeUpdate(sql);
			//System.out.println("Delete Order succeed.");
			conn.close();
			return true;
		} catch(SQLException e){
			e.printStackTrace();
			return false;
		}
	}
}
