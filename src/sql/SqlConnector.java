package sql;

/**
 * Connect to the data base.
 * @author yhe
 * 
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException; 

public class SqlConnector{
	public Connection sqlConnect(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			try{
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/DB_Project?autoReconnect=true&useSSL=false", "root", "root");
				//System.out.println("Connection succeed.");
				return conn;
			} catch (SQLException e){
				//System.out.println("Connection failed." + e.toString());
				e.printStackTrace();
				return null;
			}
		} catch (ClassNotFoundException e){
			e.printStackTrace();
			return null;
		}
	}
}