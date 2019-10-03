package DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Business.Cart;

public class OrderDao {

	public void placeorder(int uid) {
		Connection conn = null;
		PreparedStatement pstmt;
		ResultSet rs;
		conn = DBConnection.getConnection();
		int total_price = 0;

		try {

			// To get list of all cart
			CartDao cartDAO = new CartDao();

			List<Cart> result = new ArrayList<Cart>();

			result = cartDAO.selectAllItems(uid);

			// Total bill amount

			total_price = 2;

			// Insert into Billing table

			String insertinbilling = "insert into billing (uid,total_amt) values(?,?)";

			pstmt = conn.prepareStatement(insertinbilling);

			pstmt.setInt(1, uid);
			pstmt.setInt(2, total_price);

			pstmt.execute();
			pstmt.clearParameters();
			pstmt.close();

			// Get the latest bill

			String getBillNo = "select MAX(bill_no) from billing ";

			pstmt = conn.prepareStatement(getBillNo);

			rs = pstmt.executeQuery();

			rs.next();
			int bill_no = rs.getInt("MAX(bill_no)");

			pstmt.clearParameters();
			pstmt.close();

			// Insert into order_inv table

			String query = "insert into order_inv(bill_no,product_id,quantity,total_prod_price) values(?,?,?,?)";

			pstmt = conn.prepareStatement(query);

			for (Cart list : result) {
				pstmt.setInt(1, bill_no);
				pstmt.setInt(2, list.getProduct_id());
				pstmt.setInt(3, list.getQuantity());
				pstmt.setInt(4, list.getPrice());
				pstmt.execute();
				pstmt.clearParameters();
			}

			pstmt.close();

			// Delete entry from cart table

			String deletequery = "DELETE from cart where uid=? ";
			pstmt = conn.prepareStatement(deletequery);
			pstmt.setInt(1, uid);
			pstmt.execute();
			pstmt.clearParameters();
			pstmt.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

}