package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import BO.LoginBean;
import BO.User;

public class LoginDB {

	public User validate(LoginBean loginBean) throws ClassNotFoundException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		User user = new User();

		try {
			connection = DBConnection.getConnection();
			preparedStatement = connection
					.prepareStatement("select * from user_reg where user_name = ? and passwd = ? ");
			preparedStatement.setString(1, loginBean.getUsername());
			preparedStatement.setString(2, loginBean.getPassword());

			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			rs.next();
			user.setName(rs.getString("name"));
			user.setUid(rs.getInt("uid"));
			user.setRole(rs.getString("role"));

		} catch (SQLException e) {
			DBConnection.printSQLException(e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		return user;
	}

	public int getUserID(LoginBean loginBean) throws ClassNotFoundException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int userID = 0;

		try {
			connection = DBConnection.getConnection();
			preparedStatement = connection
					.prepareStatement("select * from user_reg where user_name = ? and passwd = ? ");
			preparedStatement.setString(1, loginBean.getUsername());
			preparedStatement.setString(2, loginBean.getPassword());

			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			rs.next();
			userID = rs.getInt("uid");

		} catch (SQLException e) {
			DBConnection.printSQLException(e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		return userID;
	}

}