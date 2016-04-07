package sql;

/**
 * create table Orders(
 * id int primary key auto_increment,
 * orderedBy int not null,
 * foreign key(orderedBy) references Customer(id) on update cascade on delete no action,
 * orderBuy int not null,
 * foreign key(orderBuy) references Product(id) on update cascade on delete no action,
 * orderAmount int not null,
 * orderStatus enum('wait_inspect', 'accepted', 'declined') not null
 * );
 * @author yhe
 *
 */
public class Orders {
	private int orderId;
	private int orderedBy;
	private int orderBuy;
	private int orderAmount;
	private String orderStatus;
	
	public int getOrderId(){
		return orderId;
	}
	public void setOrderId(int orderId){
		this.orderId = orderId;
	}
	
	public int getOrderedBy(){
		return orderedBy;
	}
	public void setOrderedBy(int orderedBy){
		this.orderedBy = orderedBy;
	}
	
	public int getOrderBuy(){
		return orderBuy;
	}
	public void setOrderBuy(int orderBuy){
		this.orderBuy = orderBuy;
	}
	
	public int getOrderAmount(){
		return orderAmount;
	}
	public void setOrderAmount(int orderAmount){
		this.orderAmount = orderAmount;
	}
	
	public String getOrderStatus(){
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus){
		this.orderStatus = orderStatus;
	}
	
	public Orders(int orderId, int orderedBy, int orderBuy, int orderAmount, String orderStatus){
		super();
		this.orderId = orderId;
		this.orderedBy = orderedBy;
		this.orderBuy = orderBuy;
		this.orderAmount = orderAmount;
		this.orderStatus = orderStatus;
	}
}
