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
 * Servlet implementation class AddProduct
 * Admin use this to add a new product in amdin.jsp.
 * productList will be loaded if succeed.
 * Remain in the admin.jsp if failed.
 * @author yhe
 */
public class AddProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Product newProduct = new Product();
		String productName = request.getParameter("newProductName");
		int productCount = Integer.parseInt(request.getParameter("newProductCount"));
		String productType = request.getParameter("newProductType");
		
		newProduct.setProductName(productName);
		newProduct.setProductCount(productCount);
		newProduct.setProductType(productType);
		
		ProductManager manager = new ProductManager();
		boolean result = manager.addNewProduct(newProduct);
		if(result==true){
			//System.out.println("Add succeed.");
			HttpSession session = request.getSession(true);
			List<Product> newAllProducts= new ArrayList<Product>();
			newAllProducts = manager.listAllProducts();
			session.setAttribute("productList", newAllProducts);
			response.sendRedirect("admin.jsp");
			return;
		} else{
			//System.out.println("add failed.");
			response.sendRedirect("admin.jsp");
			return;
		}
	}

}
