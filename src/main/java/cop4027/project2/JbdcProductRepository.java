package cop4027.project2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class JbdcProductRepository implements ProductRepository {
	private final DatabaseConfig config;
	private static final String CREATE_TABLE_SQL = """
		CREATE TABLE IF NOT EXISTS products (
		    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
		    name VARCHAR(100) NOT NULL,
		    category VARCHAR(100) NOT NULL,
		    quantity INT NOT NULL,
		    price DECIMAL(10, 2) NOT NULL
		)
	""";
	
	public JbdcProductRepository(DatabaseConfig config) {
		Objects.requireNonNull(config, "config");
		this.config = config;
		createTableIfMissing();
	}
	
	private Connection connect() throws SQLException {
		return config.openConnection();
	}
	
	private void createTableIfMissing() {	
		try (Connection connection = connect();
			 PreparedStatement statement = connection.prepareStatement(CREATE_TABLE_SQL)) {
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new RepositoryException("Could not create products table", e);
		}
	}
	
	@Override
	public List<Product> findAll() {
		String sql = "SELECT id, name, category, quantity, price FROM products ORDER BY id";
		List<Product> products = new ArrayList<Product>();
		
		try (Connection connection = connect();
			 PreparedStatement statement = connection.prepareStatement(sql);
			 ResultSet rows = statement.executeQuery()) {
				while (rows.next()) {
					products.add(mapProduct(rows));
				}
		} catch (SQLException exception) {
			throw new RepositoryException("Could not load products", exception);
		}
		
		return products;
	}

	private Product mapProduct(ResultSet rows) throws SQLException {
		long id = rows.getLong("id");
		String name = rows.getString("name");
		String category = rows.getString("category");
		int quantity = rows.getInt("quantity");
		double price = rows.getDouble("price");
		
		return new Product(id, name, category, quantity, price);
	}

	@Override
	public List<Product> search(String text) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(String name, String category, int quantity, double price) {
		String sql =
	            "INSERT INTO products (name, category, quantity, price) VALUES (?, ?, ?, ?)";
		
		try (Connection connection = connect();
				 PreparedStatement statement = connection.prepareStatement(sql);) {
			
			statement.setString(1, name);
			statement.setString(2, category);
			statement.setInt(3, quantity);
			statement.setDouble(4, price);
			statement.executeUpdate();
		} catch (SQLException exception) {
			throw new RepositoryException(
					"Could not add product", exception);
		}
		
	}

	@Override
	public void update(Product product) {
		String sql = "UPDATE products SET name = ?, category = ?, quantity = ?, price = ? WHERE id = ?";
        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, product.nameProperty().get());
            statement.setString(2, product.categoryProperty().get());
            statement.setInt(3, product.quantityProperty().get());
            statement.setDouble(4, product.priceProperty().get());
            statement.setLong(5, product.idProperty().get());
            statement.executeUpdate();
        } catch (SQLException exception) {
            throw new RepositoryException("Could not update product", exception);
        }
    }

	@Override
	public void delete(long id) {
		String sql = "DELETE FROM products WHERE id = ?";
        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException exception) {
            throw new RepositoryException("Could not delete product", exception);
        }
    }

}
