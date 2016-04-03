package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException; 

public class SqlConnector{
	public Connection sqlConnect(){
		Connection conn = null;
		try{
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/DB_Project?autoReconnect=true&useSSL=false", "root", "123");
			System.out.println("Connection succeed.");
			return conn;
		} catch (SQLException e){
			System.out.println("Connection failed." + e.toString());
			e.printStackTrace();
			return null;
		}
	}
}
