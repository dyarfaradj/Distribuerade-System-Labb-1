package Business;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DataAccess.OrderDao;

@WebServlet(name = "OrderServlet", urlPatterns = { "/placeorder" })
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private OrderDao orderDAO;

	public void init() {
		orderDAO = new OrderDao();
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}