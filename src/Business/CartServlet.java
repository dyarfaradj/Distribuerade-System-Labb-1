package Business;


import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;

import DataAccess.CartDao;

@WebServlet("/shoppingcart")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
				
    public CartServlet() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	   
    	//int id= (int) request.getSession().getAttribute("id");
    	int id= 1;
		
		//int productId= Integer.parseInt(request.getParameter("productId"));
    	
		int productId= 1;
		int quantity = 1;
		
		int stockId = CartDao.getStock(productId);
		
		if(stockId >=quantity) {
			CartDao.insertIntoCart(id,productId,quantity);	
		}
		
		List<Cart> result = new ArrayList<Cart>();
		
		result = CartDao.getProducts(id);
		
		request.setAttribute("Cart", result);
		
		
		RequestDispatcher rd=request.getRequestDispatcher("/shoppingcart.jsp");
		rd.forward(request, response);
		 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}