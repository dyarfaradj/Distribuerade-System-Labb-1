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

@WebServlet(name = "CategoryServlet", urlPatterns = { "/showcategories", "/addcategory", "/removecategory",
		"/editcategory", "/updatecategory" })
public class CategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CategoryDB categoryDao;

	public void init() {
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
			case "/showcategories":
				showCategories(request, response);
				break;
			case "/addcategory":
				addCategory(request, response);
				break;
			case "/removecategory":
				removeCategory(request, response);
				break;
			case "/updatecategory":
				updateCategory(request, response);
				break;
			case "/editcategory":
				showEditForm(request, response);
				break;
			default:
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void showCategories(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Category> listCategory = categoryDao.selectAllCategories();
		request.setAttribute("listCategories", listCategory);
		RequestDispatcher dispatcher = request.getRequestDispatcher("CategoryList.jsp");
		dispatcher.forward(request, response);
	}

	private void addCategory(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		String cat_name = request.getParameter("cat_name");
		System.out.println("TEST: " + cat_name);
		Category newCategory = new Category(cat_name);
		categoryDao.insertCategory(newCategory);
		response.sendRedirect("showcategories");
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int cat_id = Integer.parseInt(request.getParameter("cat_id"));
		Category existingCategory = categoryDao.selectCategory(cat_id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("CategoryForm.jsp");
		request.setAttribute("category", existingCategory);
		dispatcher.forward(request, response);
	}

	private void updateCategory(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int cat_id = Integer.parseInt(request.getParameter("cat_id"));
		String cat_name = request.getParameter("cat_name");
		Category category = new Category(cat_id, cat_name);
		categoryDao.updateCategory(category);
		response.sendRedirect("showcategories");
	}

	private void removeCategory(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int cat_id = Integer.parseInt(request.getParameter("cat_id"));
		categoryDao.deleteCategory(cat_id);
		response.sendRedirect("showcategories");
	}
}