package sql;

/**
 * create table Customer(
 * id int primary key auto_increment,
 * userName varchar(250) not null unique,
 * userPassword varchar(250) not null
 * );
 * @author yhe
 * 
 */
public class Customer {
	private int userId;
	private String userName;
	private String userPassword;
	
	public int getUserId(){
		return userId;
	}
	public void setUserId(int userId){
		this.userId = userId;
	}
	
	public String getUserName(){
		return userName;
	}
	public void setUserName(String userName){
		this.userName = userName;
	}
	
	public String getUserPassword(){
		return userPassword;
	}
	public void setUserPassword(String userPassword){
		this.userPassword = userPassword;
	}
	
	public Customer(int userId, String userName, String userPassword) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPassword = userPassword;
	}
	
	public Customer(){
		super();
	}
}
