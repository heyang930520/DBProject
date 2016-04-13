package sql;

/**
 * Functions on the Product table.
 * @author yhe
 *
 */
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import sql.Product;
import sql.SqlConnector;

public class ProductManager {
	SqlConnector sqlConnector = new SqlConnector();
	
	//List all the products.
	public List<Product> listAllProducts(){
		Connection conn = sqlConnector.sqlConnect();
		List<Product> allProducts = new ArrayList<Product>();
		try{
			String sql = "select * from Product";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				int productId = rs.getInt("id");
				String productName = rs.getString("productName");
				int productCount = rs.getInt("productCount");
				String productType = rs.getString("productType");
				
				Product product =  new Product();
				product.setProductId(productId);
				product.setProductName(productName);
				product.setProductCount(productCount);
				product.setProductType(productType);			
				allProducts.add(product);
			}
			conn.close();
		} catch(SQLException e){
				e.printStackTrace();
		}
		return allProducts;
	}
	
	//Add a new product.
	public boolean addNewProduct(Product product){
		Connection conn = sqlConnector.sqlConnect();
		try{
			String preSql = "alter table Product AUTO_INCREMENT = 1";
			Statement preSt = conn.createStatement();
			preSt.executeUpdate(preSql);
			String sql = "insert into Product(productName, productCount, productType) values('"+product.getProductName()+"', '"+product.getProductCount()+"', '"+product.getProductType()+"')";
			Statement st = conn.createStatement();
			st.executeUpdate(sql);
			//System.out.println("Add new product succeed.");
			conn.close();
			return true;
		} catch(SQLException e){
			e.printStackTrace();
			return false;
		}
	}
	
	//Update the productCount value.
	public boolean updateProductCount(int productId, int productCount){
		Connection conn = sqlConnector.sqlConnect();
		try{
			String sql = "update Product set productCount='"+productCount+"' where id='"+productId+"'";
			Statement st = conn.createStatement();
			st.executeUpdate(sql);
			//System.out.println("Update product count succeed.");
			conn.close();
			return true;
		} catch(SQLException e){
			e.printStackTrace();
			return false;
		}
	}
	
	//Delete the product.
	public boolean deleteProduct(int productId){
		Connection conn = sqlConnector.sqlConnect();
		try{
			String sql = "delete from Product where id='"+productId+"'";
			Statement st = conn.createStatement();
			st.executeUpdate(sql);
			//System.out.println("Delete product succeed.");
			conn.close();
			return true;
		} catch(SQLException e){
			e.printStackTrace();
			return false;
		}
	}
}
