<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="controller.ListProduct" %>
<%@ page import="sql.Product" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Customer page</title>
	<link href="http://blackrockdigital.github.io/startbootstrap-bare/css/bootstrap.min.css" rel="stylesheet">
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
	<script src="http://getbootstrap.com/dist/js/bootstrap.min.js"></script>		
	<style>
    	body {
        	padding-top: 70px;
        	/* Required padding for .navbar-fixed-top. Remove if using .navbar-static-top. Change if height of navigation changes. */
   		}
	</style>
</head>

<body>
	<%
		if(session.getAttribute("userId")==null || session.getAttribute("userId").equals(1)){		
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
                    	<a href="myOrders.jsp">My Orders</a>
                    </li>
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
        <h3>All Products</h3>
        <div class="table-responsive">                
			<table id="mytable" class="table table-bordred table-striped">                  
                 <thead>                  
                 <th>ProductId</th>
                 <th>ProductName</th>
                 <th>ProductCount</th>
                 <th>ProductType</th>
                 </thead>
			<tbody>   
   			<%
   			ListProduct manager = new ListProduct();
   			manager.doGet(request, response);
    		List<Product> allProducts = (List<Product>)session.getAttribute("productList");
    		if(allProducts == null){
    			%><div class="alert alert-error">
    				<button type="button" class="close" data-dismiss="alert">X</button>
    				<h4> Null!
    				</h4>
    				</div>
    		<%}
    		else{
   				for(int i=0;i<allProducts.size();i++){
    				Product product = new Product();
    				product = allProducts.get(i);
    				%><tr><% 
    				%><td><%=product.getProductId()
    				%></td><%
    				%><td><%=product.getProductName()
    				%></td><%
    				%><td><%=product.getProductCount()
    				%></td><%
    				%><td><%=product.getProductType()
    				%></td><%
    				%></tr><% 
    			}
    		}%>     
    		</tbody>       
			</table>
			<p data-placement="top" data-toggle="tooltip" title="Add"><button class="btn btn-primary btn-xs" data-title="Add" data-toggle="modal" data-target="#add" id="button_add"><span class="glyphicon glyphicon-plus"></span></button></p>
			
		</div>
		</div>
	</div>
	</div>

	<div class="modal fade" id="add" tabindex="-1" role="dialog" aria-labelledby="add" aria-hidden="true">
    <div class="modal-dialog">
    <div class="modal-content">
    <div class="modal-header">
    	<button type="button" class="close" data-dismiss="modal" aria-hidden="true"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>
    	<h4 class="modal-title custom_align" id="Heading">Make a  new order</h4>
    </div>
    <form calss="btn btn-warning btn-lg" method="get" action="AddOrder">
    	<div class="modal-body">
        <div class="form-group">
        	<input type="hidden" id="updateProductId" value="<%= session.getAttribute("userId") %>" name="userId">
        	<input class="form-control" type="text" placeholder="Enter the Product Id here." name="productId">
        	<input class="form-control" type="text" placeholder="Enter the amount of Product you want buy." name="productAmount">
        </div>
        </div>
        <div class="modal-footer ">
        	<button type="submit" class="btn btn-warning btn-lg" style="width: 100%;"><span class="glyphicon glyphicon-ok-sign"></span>Add</button>
        </div>
    </form>
    </div>
    <!-- /.modal-content --> 
    </div>
      <!-- /.modal-dialog --> 
    </div>    
</body>
</html>