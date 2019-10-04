package BO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DB.CartDB;

@WebServlet(name = "CartServlet", urlPatterns = { "/showcart", "/addtocart" })
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CartDB cartDAO;

	public void init() {
		cartDAO = new CartDB();
	}

	public CartServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		try {
			switch (action) {
			case "/showcart":
				showCart(request, response);
				break;
			case "/addtocart":
				addToCart(request, response);
				break;
			default:
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void addToCart(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		int user_id = (int) request.getSession().getAttribute("user_id");
		int product_id = Integer.parseInt(request.getParameter("product_id"));
		int quantity = 1;
		List<Cart> listItem = cartDAO.selectAllItems(user_id);
		for (int i = 0; i < listItem.size(); i++) {
			if (listItem.get(i).getProduct_id() == product_id) {
				quantity = listItem.get(i).getQuantity();
			}
		}
		int stockId = CartDB.getStock(product_id);

		if (stockId >= quantity) {
			CartDB.insertIntoCart(user_id, product_id, quantity);
		}

		showCart(request, response);
	}

	private void showCart(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		int user_id = (int) request.getSession().getAttribute("user_id");

		List<Cart> listItem = cartDAO.selectAllItems(user_id);
		request.setAttribute("listItem", listItem);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/cart.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}