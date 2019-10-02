package Business;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DataAccess.LoginDao;


@WebServlet(name="LoginServlet", urlPatterns={"/login", "/logout"})
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private LoginDao loginDao;

    public void init() {
        loginDao = new LoginDao();
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
                case "/login":
                	login(request, response);
                    break;
                case "/logout":
                    logout(request, response);
                    break;
                default:
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
        
    }
    
    private void login(HttpServletRequest request, HttpServletResponse response)
    	    throws SQLException, IOException, ServletException {
    	String username = request.getParameter("username");
        String password = request.getParameter("password");
        LoginBean loginBean = new LoginBean();
        loginBean.setUsername(username);
        loginBean.setPassword(password);

        try {
            if (loginDao.validate(loginBean)) {
                HttpSession session = request.getSession();
                session.setAttribute("user_id", loginDao.getUserID(loginBean));
                session.setAttribute("username",username);
                response.sendRedirect("index.jsp");
            } else {
                HttpSession session = request.getSession();
                //session.setAttribute("user", username);
                response.sendRedirect("login.jsp");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
   }
    
    private void logout(HttpServletRequest request, HttpServletResponse response)
    	    throws SQLException, IOException, ServletException {
    	
	    	request.getSession().invalidate();
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
	        dispatcher.forward(request, response);
   }
    

}
