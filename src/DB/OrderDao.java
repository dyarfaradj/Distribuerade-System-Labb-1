package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Business.Cart;

public class OrderDao {

	public void placeorder(int uid) {
		Connection connection = null;
		PreparedStatement preparedStatement;
		ResultSet rs;
		int total_price = 0;

		try {
			connection = DBConnection.getConnection();

			// To get list of all cart
			CartDao cartDAO = new CartDao();
			List<Cart> result = new ArrayList<Cart>();
			result = cartDAO.selectAllItems(uid);

			// Total bill amount
			total_price = 2;

			// Insert into Billing table
			connection.setAutoCommit(false);

			String insertinbilling = "insert into billing (uid,total_amt) values(?,?)";

			preparedStatement = connection.prepareStatement(insertinbilling);

			preparedStatement.setInt(1, uid);
			preparedStatement.setInt(2, total_price);

			preparedStatement.execute();
			preparedStatement.clearParameters();
			preparedStatement.close();

			// Get the latest bill

			String getBillNo = "select MAX(bill_no) from billing ";

			preparedStatement = connection.prepareStatement(getBillNo);

			rs = preparedStatement.executeQuery();

			rs.next();
			int bill_no = rs.getInt("MAX(bill_no)");

			preparedStatement.clearParameters();
			preparedStatement.close();

			// Insert into order_inv table

			String query = "insert into order_inv(bill_no,product_id,quantity,total_prod_price) values(?,?,?,?)";

			preparedStatement = connection.prepareStatement(query);

			for (Cart list : result) {
				preparedStatement.setInt(1, bill_no);
				preparedStatement.setInt(2, list.getProduct_id());
				preparedStatement.setInt(3, list.getQuantity());
				preparedStatement.setInt(4, list.getPrice());
				preparedStatement.execute();
				preparedStatement.clearParameters();
			}

			preparedStatement.close();

			// Delete entry from cart table

			String deletequery = "DELETE from cart where uid=? ";
			preparedStatement = connection.prepareStatement(deletequery);
			preparedStatement.setInt(1, uid);
			preparedStatement.execute();
			preparedStatement.clearParameters();
			preparedStatement.close();
			connection.commit();
			connection.setAutoCommit(true);

		} catch (SQLException e) {
			DBConnection.printSQLException(e);
		}

	}

}