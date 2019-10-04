package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Business.LoginBean;

public class LoginDao {

	public boolean validate(LoginBean loginBean) throws ClassNotFoundException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		boolean status = false;

		try {
			connection = DBConnection.getConnection();
			preparedStatement = connection
					.prepareStatement("select * from user_reg where user_name = ? and passwd = ? ");
			preparedStatement.setString(1, loginBean.getUsername());
			preparedStatement.setString(2, loginBean.getPassword());

			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			status = rs.next();

		} catch (SQLException e) {
			DBConnection.printSQLException(e);
		}
		return status;
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
		}
		return userID;
	}

}