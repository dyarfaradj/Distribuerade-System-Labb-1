package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	public static Connection getConnection() {

		Connection connection = null;
		try {
			String url = "jdbc:mysql://remotemysql.com:3306/XdVvV2OhRA?useSSL=true";
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, "XdVvV2OhRA", "qFzNXNgR0v");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			printSQLException(e);
		}

		return connection;
	}

	public static void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}
}