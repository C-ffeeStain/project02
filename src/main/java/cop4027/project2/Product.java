package cop4027.project2;

import javafx.beans.property.*;

public class Product {
	private LongProperty id;
	private StringProperty name;
	private StringProperty category;
	private IntegerProperty quantity;
	private DoubleProperty price;
	
	public Product(long id, String name, String category, int quantity, double price) {
		this.id = new SimpleLongProperty(id);
		this.name = new SimpleStringProperty(name);
		this.category = new SimpleStringProperty(category);
		this.quantity = new SimpleIntegerProperty(quantity);
		this.price = new SimpleDoubleProperty(price);
	}
	
	public LongProperty idProperty() {
		return id;
	}
	
	public StringProperty nameProperty() {
		return name;
	}
	public StringProperty categoryProperty() {
		return category;
	}
	public IntegerProperty quantityProperty() {
		return quantity;
	}
	public DoubleProperty priceProperty() {
		return price;
	}
}