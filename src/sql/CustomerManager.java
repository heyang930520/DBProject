package sql;
/**
 * Functions on the Customer table.
 * @author yhe
 * 
 */
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import sql.SqlConnector;

public class CustomerManager {
	SqlConnector sqlConnector = new SqlConnector();
	
	//User registration. return user id if succeed, return -1 if failed.
	public int register(Customer customer){
		Connection conn = sqlConnector.sqlConnect();
		int userId = 0;
		try{
			String sql = "insert into Customer(userName,userPassword) Values('"+customer.getUserName()+"','"+customer.getUserPassword()+"')";
		    Statement st = conn.createStatement();
		    st.executeUpdate(sql);
		    conn.close();
		    
		    userId = findUserId(customer.getUserName());
		    //System.out.println("The id for new customer is:"+userId);
		    return userId;
		} catch(SQLException e){
			e.printStackTrace();
			return -1;
		}
	}
	
	//Find user id by name. Return id if the user exists in the database, return -1 if not.
	public int findUserId(String name){
		Connection conn = sqlConnector.sqlConnect();
		int userId = 0;
		try{
			String sql = "select id from Customer where userName='"+name+"'";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				userId = rs.getInt("id");
			}
			conn.close();
			return userId;
		} catch(SQLException e){
			System.out.println(e);
			return -1;
		}
	}
	
	// Check the name/password information provided when attempt to login, return true if the information is correct, else false. 
	public boolean login(String name, String password){
		Connection conn =  sqlConnector.sqlConnect();
		int resultCount = 0;
		try{
			String sql = "select * from Customer where userName='"+name+"' and userPassword='"+password+"'";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			rs.last();
			resultCount = rs.getRow();
			conn.close();
		} catch(SQLException e){
			e.printStackTrace();
		}
		
		if(resultCount==1){
			return true;
		}
		else{
			return false;
		}
	}
}
