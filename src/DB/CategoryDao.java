package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Business.Category;

public class CategoryDao {
	private static final String INSERT_CATEGORY_SQL = "INSERT INTO category (cat_name) VALUES (?)";
	private static final String SELECT_CATEGORY_BY_ID = "select cat_name from category where cat_id =?";
	private static final String DELETE_CATEGORY_SQL = "DELETE FROM category where cat_id = ?";
	private static final String SELECT_ALL_CATEGORIES = "select * from category";
	private static final String UPDATE_CATEGORY_SQL = "UPDATE category SET cat_name = ? WHERE cat_id = ?";

	public CategoryDao() {
	}

	public Category selectCategory(int cat_id) {
		Connection connection = null;
		PreparedStatement preparedStatement;
		Category category = null;

		try {
			connection = DBConnection.getConnection();
			preparedStatement = connection.prepareStatement(SELECT_CATEGORY_BY_ID);
			preparedStatement.setInt(1, cat_id);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String cat_name = rs.getString("cat_name");
				category = new Category(cat_name);
			}
		} catch (SQLException e) {
			DBConnection.printSQLException(e);
		}
		return category;
	}

	public void insertCategory(Category category) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement;

		System.out.println(INSERT_CATEGORY_SQL);

		try {
			connection = DBConnection.getConnection();
			preparedStatement = connection.prepareStatement(INSERT_CATEGORY_SQL);
			preparedStatement.setString(1, category.getCat_name());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			DBConnection.printSQLException(e);
		}
	}

	public List<Category> selectAllCategories() {
		Connection connection = null;
		PreparedStatement preparedStatement;
		List<Category> categories = new ArrayList<>();

		try {
			connection = DBConnection.getConnection();
			preparedStatement = connection.prepareStatement(SELECT_ALL_CATEGORIES);
			System.out.println(preparedStatement);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int cat_id = rs.getInt("cat_id");
				String cat_name = rs.getString("cat_name");
				categories.add(new Category(cat_id, cat_name));
			}
		} catch (SQLException e) {
			DBConnection.printSQLException(e);
		}
		return categories;
	}

	public boolean deleteCategory(int cat_id) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement;
		boolean rowDeleted = false;
		System.out.println("DELETEE: " + cat_id);
		try {
			connection = DBConnection.getConnection();
			preparedStatement = connection.prepareStatement(DELETE_CATEGORY_SQL);
			preparedStatement.setInt(1, cat_id);
			rowDeleted = preparedStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			DBConnection.printSQLException(e);
		}
		return rowDeleted;
	}

	public boolean updateCategory(Category category) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement;
		boolean rowUpdated = false;
		try {
			connection = DBConnection.getConnection();
			preparedStatement = connection.prepareStatement(UPDATE_CATEGORY_SQL);
			preparedStatement.setString(1, category.getCat_name());
			preparedStatement.setInt(2, category.getCat_id());

			rowUpdated = preparedStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			DBConnection.printSQLException(e);
		}
		return rowUpdated;
	}

}