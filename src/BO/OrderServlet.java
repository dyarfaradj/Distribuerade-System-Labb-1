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

import DB.OrderDB;
import BO.Billing;


@WebServlet(name = "OrderServlet", urlPatterns = { "/placeorder", "/orderlist", "/vieworder" })
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private OrderDB orderDAO;

	public void init() {
		orderDAO = new OrderDB();
	}

	public OrderServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		try {
			switch (action) {
			case "/placeorder":
				placerOrder(request, response);
				break;
			case "/orderlist":
				orderList(request, response);
				break;
			case "/vieworder":
				viewOrder(request, response);
				break;
			case "/sendorder":
				placerOrder(request, response);
				break;
			default:
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
	private void placerOrder(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		int user_id = (int) request.getSession().getAttribute("user_id");

		orderDAO.placeorder(user_id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/cart.jsp");
		dispatcher.forward(request, response);
	}
	
	private void orderList(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		List<Billing> orderList = orderDAO.selectAllOrder();
		request.setAttribute("orderList", orderList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/OrderList.jsp");
		dispatcher.forward(request, response);
	}
	private void viewOrder(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		int order_id = 22;

		List<Billing> viewOrder  = orderDAO.selectOrderByID(order_id);
		request.setAttribute("viewOrder", viewOrder);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/ViewOrder.jsp");
		dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}