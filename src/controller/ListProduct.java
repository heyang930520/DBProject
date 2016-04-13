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
 * Servlet implementation class test
 * List all the products.
 * @author yhe
 */
public class ListProduct extends HttpServlet{
	private static final long serialVersionUID = 1L;
    
    public ListProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ProductManager manager = new ProductManager();
		List<Product> productList = (ArrayList<Product>) manager.listAllProducts();
		HttpSession session = request.getSession(true);
		session.setAttribute("productList", productList);
		//System.out.println(productList.size());
	}
}
