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
				
				String sql="INSERT INTO cart(user_id,product_id,quantity,price)VALUES(?,?,?,?)";
				
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
					
					cart.setProductId(rs.getInt("product_id"));
					cart.setProductName(rs.getString("product_name"));
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