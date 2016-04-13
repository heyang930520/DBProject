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
 * Servlet implementation class UpdateProduct
 * Admin use this to update the product count.
 * productList will be reloaded if succeed.
 * Remain in admin.jsp if failed.
 * @author yhe
 */
public class UpdateProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdateProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int productId = Integer.parseInt(request.getParameter("updateProductId"));
		int productCount = Integer.parseInt(request.getParameter("updateProductCount"));
		
		ProductManager manager = new ProductManager();
		boolean result = manager.updateProductCount(productId, productCount);
		if(result==true){
			//System.out.println("update succeed.");
			HttpSession session = request.getSession(true);
			List<Product> newAllProducts= new ArrayList<Product>();
			newAllProducts = manager.listAllProducts();
			session.setAttribute("productList", newAllProducts);
			response.sendRedirect("admin.jsp");
			return;
		} else{
			//System.out.println("update failed.");
			response.sendRedirect("admin.jsp");
			return;
		}
	}

}
