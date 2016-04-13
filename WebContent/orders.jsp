<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="controller.ListAllOrders" %>
<%@ page import="sql.Orders" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Manager Orders</title>
	<link href="http://blackrockdigital.github.io/startbootstrap-bare/css/bootstrap.min.css" rel="stylesheet">
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
	<script src="http://getbootstrap.com/dist/js/bootstrap.min.js"></script>
	<style>
    	body {
        	padding-top: 70px;
        	/* Required padding for .navbar-fixed-top. Remove if using .navbar-static-top. Change if height of navigation changes. */
   		}
	</style>
	
	<script type="text/javascript">
	$(document).ready(function() {
		$("[id^=button_edit]").click(function(){
			//console.log(this.value);
			$('#updateOrderId').val(this.value);
		});
		$("[id^=button_delete]").click(function(){
			//console.log(this.value);
			$('#deleteOrderId').val(this.value);
		});
	});
	</script>
</head>
<body>
	<%
		if(session.getAttribute("userId")==null || !session.getAttribute("userId").equals(1)){ 	
			String redirectURL = "login.jsp";
    		response.sendRedirect(redirectURL); 
    	}
	%>
	<!-- Navigation -->
   	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
   		<div class="container">
        	<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li>
                    	<a href="admin.jsp">Admin Page</a>
                    <li>
                    	<a href="logout.jsp">Log Out</a>
                    </li>
                </ul>
            </div>
        </div>
        <!-- /.container -->
    </nav>
    
    <div class="container">
	<div class="row">        
        <div class="col-md-12">
        <h3>Order List</h3>
        <div class="table-responsive">                
			<table id="mytable" class="table table-bordred table-striped">                  
                 <thead> 
                 <th>Order Id</th> 
                 <th>Ordered By</th>                
                 <th>Order Buy</th>
                 <th>Order Amount</th>
                 <th>Order Status</th>
                 <th>Edit</th>
                 <th>Delete</th>
                 </thead>
			<tbody>   
   			<%
   			ListAllOrders manager = new ListAllOrders();
   			manager.doGet(request, response);
    		List<Orders> allOrders = (List<Orders>)session.getAttribute("allOrders");
    		if(allOrders == null){
    			%><div class="alert alert-error">
				<button type="button" class="close" data-dismiss="alert">X</button>
				<h4> Null!
				</h4>
				</div>
			<%}	else{
				for(int i=0;i<allOrders.size();i++){
    				Orders order = new Orders();
    				order = allOrders.get(i);
    				%><tr><% 
    				%><td><%=order.getOrderId()
    				%></td><%
    				%><td><%=order.getOrderedBy()
    				%></td><%
    				%><td><%=order.getOrderBuy()
    				%></td><%
    				%><td><%=order.getOrderAmount()
    				%></td><%
    				%><td><%=order.getOrderStatus()
    				%></td><%
    				%><td><p data-placement="top" data-toggle="tooltip" title="Edit"><button class="btn btn-primary btn-xs" data-title="Edit" data-toggle="modal" data-target="#edit" id="button_edit_<%=order.getOrderId()%>" value="<%=order.getOrderId()%>"><span class="glyphicon glyphicon-pencil"></span></button></p></td>
    				<td><p data-placement="top" data-toggle="tooltip" title="Delete"><button class="btn btn-danger btn-xs" data-title="Delete" data-toggle="modal" data-target="#delete" id="button_delete_<%= order.getOrderId()%>" value="<%= order.getOrderId()%>"><span class="glyphicon glyphicon-trash"></span></button></p></td>
    				</tr><%
    			}
    		}%>
    		</tbody>       
			</table>
			</div>
		</div>
	</div>
	</div>

	<div class="modal fade" id="edit" tabindex="-1" role="dialog" aria-labelledby="edit" aria-hidden="true">
    <div class="modal-dialog">
    <div class="modal-content">
    <div class="modal-header">
    	<button type="button" class="close" data-dismiss="modal" aria-hidden="true"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>
    	<h4 class="modal-title custom_align" id="Heading">Edit Order Status</h4>
    </div>
    <form calss="btn btn-warning btn-lg" method="get" action="UpdateOrder">
    	<div class="modal-body">
        <div class="form-group">
        	<input type="hidden" id="updateOrderId" value="" name="updateOrderId">
        	<input class="form-control " type="text" placeholder="Enter the Order Status here." name="updateOrderStatus">
        </div>
        </div>
        <div class="modal-footer ">
        	<button type="submit" class="btn btn-warning btn-lg" style="width: 100%;"><span class="glyphicon glyphicon-ok-sign"></span>Update</button>
        </div>
    </form>
    </div>
    <!-- /.modal-content --> 
    </div>
      <!-- /.modal-dialog --> 
    </div>
    
      
    <div class="modal fade" id="delete" tabindex="-1" role="dialog" aria-labelledby="edit" aria-hidden="true">
    <div class="modal-dialog">
    <div class="modal-content">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>
        <h4 class="modal-title custom_align" id="Heading">Delete this entry</h4>
    </div>
    <form calss="btn" method="get" action="DeleteOrderByAdmin">
    	<div class="modal-body">       
        <div class="alert alert-danger"><span class="glyphicon glyphicon-warning-sign"></span> Are you sure you want to delete this Record?</div>      
        </div>
        <div class="modal-footer ">
        <button type="submit" class="btn btn-success" id="deleteOrderId" name="deleteOrderId" value=""><span class="glyphicon glyphicon-ok-sign"></span> Yes</button>
        <button type="button" class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> No</button>
        </div>
    </form>
    </div>
    <!-- /.modal-content --> 
    </div>
    <!-- /.modal-dialog --> 
    </div>
    
</body>
</html>