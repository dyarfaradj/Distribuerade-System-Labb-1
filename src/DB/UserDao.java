package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Business.User;;

public class UserDao {

	private static final String INSERT_USERS_SQL = "INSERT INTO user_reg (name, user_name, passwd, address, mobile_no, email, role) VALUES (?, ?, ?, ?, ?, ?, ?)";

	public int registerEmployee(User user) throws ClassNotFoundException {
		Connection connection = null;
		PreparedStatement preparedStatement;

		int result = 0;

		try {
			connection = DBConnection.getConnection();
			preparedStatement = connection.prepareStatement(INSERT_USERS_SQL);
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getUsername());
			preparedStatement.setString(3, user.getPassword());
			preparedStatement.setString(4, user.getAddress());
			preparedStatement.setString(5, user.getPhone());
			preparedStatement.setString(6, user.getEmail());
			preparedStatement.setString(7, user.getRole());

			System.out.println(preparedStatement);
			result = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			DBConnection.printSQLException(e);
		}
		return result;
	}

}