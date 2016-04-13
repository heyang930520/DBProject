package controller;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sql.Product;
import sql.ProductManager;

/**
 * Servlet implementation class DeleteProduct
 * Admin use this to delete a product in admin.jsp.
 * productList will be reloaded if succeed.
 * Remain in admin.jsp if failed.
 * @author yhe
 */
public class DeleteProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int deleteProductId = Integer.parseInt(request.getParameter("deleteProductId"));
		
		ProductManager manager = new ProductManager();
		boolean result = manager.deleteProduct(deleteProductId);
		if(result==true){
			//System.out.println("delete succeed.");
			HttpSession session = request.getSession(true);
			List<Product> newAllProducts= new ArrayList<Product>();
			newAllProducts = manager.listAllProducts();
			session.setAttribute("productList", newAllProducts);
			response.sendRedirect("admin.jsp");
			return;
		} else{
			//System.out.println("delete failed.");
			response.sendRedirect("admin.jsp");
			return;
		}
	}

}
