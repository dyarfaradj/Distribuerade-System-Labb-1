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

import DB.CategoryDB;
import DB.ItemDB;

@WebServlet(name = "ItemServlet", urlPatterns = { "/new", "/insert", "/delete", "/edit", "/update", "/list",
		"/ItemList_Kund" })
public class ItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ItemDB itemDAO;
	private CategoryDB categoryDao;

	public void init() {
		itemDAO = new ItemDB();
		categoryDao = new CategoryDB();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertItem(request, response);
				break;
			case "/delete":
				deleteItem(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateItem(request, response);
				break;
			case "/list":
				listItem(request, response);
				break;
			case "/ItemList_Kund":
				listItem_(request, response);
				break;
			default:
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listItem(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Item> listItem = itemDAO.selectAllItems();
		request.setAttribute("listItem", listItem);
		// for(int i=0; i<listItem.size(); i++)
		// {
		// System.out.println(listItem.get(i).getProduct_id());
		// }
		RequestDispatcher dispatcher = request.getRequestDispatcher("ItemList.jsp");
		dispatcher.forward(request, response);
	}

	private void listItem_(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		int cat_id = Integer.parseInt(request.getParameter("cat_id"));
		List<Item> listItem1 = null;
		if (cat_id != 0) {
			listItem1 = itemDAO.selectAllItemsByCat(cat_id);
		} else {
			listItem1 = itemDAO.selectAllItems();
		}
		List<Category> listCategory = categoryDao.selectAllCategories();
		request.setAttribute("listCategories", listCategory);
		request.setAttribute("listItem1", listItem1);
		RequestDispatcher dispatcher = request.getRequestDispatcher("ItemList_Kund.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("ItemForm.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int product_id = Integer.parseInt(request.getParameter("product_id"));
		System.out.println(product_id);
		Item existingItem = itemDAO.selectItem(product_id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("ItemForm.jsp");
		request.setAttribute("item", existingItem);
		dispatcher.forward(request, response);

	}

	private void insertItem(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String product_name = request.getParameter("product_name");
		int cat_id = Integer.parseInt(request.getParameter("cat_id"));
		int stock = Integer.parseInt(request.getParameter("stock"));
		int price = Integer.parseInt(request.getParameter("price"));
		Item newItem = new Item(product_name, cat_id, stock, price);
		itemDAO.insertItem(newItem);
		response.sendRedirect("list");
	}

	private void updateItem(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int product_id = Integer.parseInt(request.getParameter("product_id"));
		String product_name = request.getParameter("product_name");
		int cat_id = Integer.parseInt(request.getParameter("cat_id"));
		int stock = Integer.parseInt(request.getParameter("stock"));
		int price = Integer.parseInt(request.getParameter("price"));
		Item item = new Item(product_id, product_name, cat_id, stock, price);
		itemDAO.updateItem(item);
		response.sendRedirect("list");
	}

	private void deleteItem(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int product_id = Integer.parseInt(request.getParameter("product_id"));
		itemDAO.deleteItem(product_id);
		response.sendRedirect("list");

	}
}