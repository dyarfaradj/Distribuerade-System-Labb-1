package Business;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DataAccess.ItemDAO;
import Business.Item;

/**
 * ControllerServlet.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the user.
 * @email Ramesh Fadatare
 */

@WebServlet("/")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1 L;
    private ItemDAO itemDAO;

    public void init() {
    	itemDAO = new ItemDAO();
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
                default:
                    listItem(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listItem(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
        List < Item > listItem = itemDAO.selectAllItems();
        request.setAttribute("listItem", listItem);
        RequestDispatcher dispatcher = request.getRequestDispatcher("ItemList.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("ItemList.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Item existingItem = itemDAO.selectItem(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("ItemList.jsp");
        request.setAttribute("item", existingItem);
        dispatcher.forward(request, response);

    }

    private void insertItem(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String quantity = request.getParameter("quantity");
        float price = Float.parseFloat(request.getParameter("price"));
        Item newItem = new Item(title, description, quantity, price);
        itemDAO.insertItem(newItem);
        response.sendRedirect("list");
    }

    private void updateItem(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String quantity = request.getParameter("quantity");
        float price = Float.parseFloat(request.getParameter("price"));

        Item item = new Item(id, title, description, quantity, price);
        itemDAO.updateItem(item);
        response.sendRedirect("list");
    }

    private void deleteItem(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        itemDAO.deleteItem(id);
        response.sendRedirect("list");

    }
}