package DataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import Business.Cart;

public class CartDao {

	private static final String SELECT_ALL_ITEMS = "select cart.product_id,product_name,quantity,cart.price from cart inner join product on cart.product_id = product.product_id where uid=?";
	private String jdbcURL = "jdbc:mysql://remotemysql.com:3306/XdVvV2OhRA?useSSL=true";
    private String jdbcUsername = "XdVvV2OhRA";
    private String jdbcPassword = "qFzNXNgR0v";
    
	public static void insertIntoCart(int id,int pid,int quantity)
	{
		Connection con=null;
		java.sql.PreparedStatement pstmt;
		ResultSet rs;
		

		try {
				con=DBConnection.getConnection();
		
				String updateStock="select product_id,price, stock from product where product_id= ?";
				
				pstmt=con.prepareStatement(updateStock,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
				pstmt.setInt(1, pid);
				rs=pstmt.executeQuery();
				rs.next();
				int price=rs.getInt("price");
				int new_stock=rs.getInt("stock");
				
				new_stock = new_stock-quantity;
				
				rs.updateInt("stock", new_stock);
				
				rs.updateRow();
				
				pstmt.clearParameters();
				
				pstmt.close();
				
				String sql="INSERT INTO cart(uid,product_id,quantity,price)VALUES(?,?,?,?)";
				
				int total_price = price * quantity;
				
				pstmt=con.prepareStatement(sql);
				
				pstmt.setInt(1, id);
				pstmt.setInt(2, pid);
				pstmt.setInt(3, quantity);
				pstmt.setInt(4, total_price);
				
				pstmt.execute();
				
				pstmt.clearParameters();
				pstmt.close();
				
				
					
				
		} catch (SQLException e) {

			e.printStackTrace();
		}
		finally
		{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }
	public List < Cart > selectAllItems(int uid) {

        List < Cart > cart = new ArrayList < > ();
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ITEMS);) {
        	preparedStatement.setInt(1, uid);
        	System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int product_id = rs.getInt("product_id");
                String product_name = rs.getString("product_name");
                int quantity = rs.getInt("quantity");
                int price = rs.getInt("price");
                cart.add(new Cart(product_id, product_name, quantity, price));
            }
        } catch (SQLException e) {
        	e.printStackTrace();
        }
        return cart;
    }
	
public static int getStock(int productId) {
		
		int stockId = 0;
		Connection connection = DBConnection.getConnection();
			    		
		String sql="select stock from product where product_id= ?";
		
		try {
			
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, productId);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				stockId = rs.getInt("stock");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
	    	try {
	    		connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    }
		
		return stockId;
		
	}
	

	public static List<Cart> getProducts(int userId) {
		
		String query = "select cart.product_id,product_name,quantity,cart.price from cart inner join product on cart.product_id = product.product_id where uid=?";
		List<Cart> carts = new ArrayList<Cart>();
		Connection connection = DBConnection.getConnection();
		try {
				PreparedStatement pstmt = connection.prepareStatement(query);
				pstmt.setInt(1, userId);
				ResultSet rs=pstmt.executeQuery();
				
				while(rs.next())
				{
					Cart cart = new Cart();
					
					cart.setProduct_id(rs.getInt("product_id"));
					cart.setProduct_name(rs.getString("product_name"));
					cart.setQuantity(rs.getInt("quantity"));
					cart.setPrice(rs.getInt("price"));
					
					carts.add(cart);
				}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally
		{
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return carts;
	}



	public static int getTotal(int userId) {
		
			int totalprice=0;
			
			String query="select price from cart where uid= ? ";
			
			Connection connection =DBConnection.getConnection();
			
			try {
					
					PreparedStatement pstmt= connection.prepareStatement(query);
					pstmt.setInt(1, userId);
					ResultSet rs= pstmt.executeQuery();
					
					while(rs.next())
					{
						totalprice=totalprice+rs.getInt("price");
					}
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
		return totalprice;
	}
}