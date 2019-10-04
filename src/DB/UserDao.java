package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Business.User;;

public class UserDao {

	private static final String INSERT_USERS_SQL = "INSERT INTO user_reg (name, user_name, passwd, address, mobile_no) VALUES (?, ?, ?, ?,?)";

	public int registerEmployee(User user) throws ClassNotFoundException {
		Connection connection = null;
		PreparedStatement preparedStatement;

		int result = 0;

		Class.forName("com.mysql.cj.jdbc.Driver");

		try {
			connection = DBConnection.getConnection();
			preparedStatement = connection.prepareStatement(INSERT_USERS_SQL);
			preparedStatement.setString(1, user.getFirstName());
			preparedStatement.setString(2, user.getUsername());
			preparedStatement.setString(3, user.getPassword());
			preparedStatement.setString(4, user.getAddress());
			preparedStatement.setString(5, user.getContact());

			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			result = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			DBConnection.printSQLException(e);
		}
		return result;
	}

}