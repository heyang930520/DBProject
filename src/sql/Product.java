package sql;

/**
 * create table Product(
 * id int primary key auto_increment,
 * productName varchar(250) not null unique,
 * productCount int not null,
 * productType enum('food', 'drink', 'daily_use') not null
 * );
 * @author yhe
 * 
 */
public class Product {
	private int productId;
	private String productName;
	private int productCount;
	private String productType;
	
	public int getProductId(){
		return productId;
	}
	public void setProductId(int productId){
		this.productId = productId;
	}
	
	public String getProductName(){
		return productName;
	}
	public void setProductName(String productName){
		this.productName = productName;
	}
	
	public int getProductCount(){
		return productCount;
	}
	public void setProductCount(int productCount){
		this.productCount = productCount;
	}
	
	public String getProductType(){
		return productType;
	}
	public void setProductType(String productType){
		this.productType = productType;
	}
	
	public Product(int productId, String productName, int productCount, String productType){
		super();
		this.productId = productId;
		this.productName = productName;
		this.productCount = productCount;
		this.productType = productType;
	}
	
	public Product(){
		super();
	}
}
