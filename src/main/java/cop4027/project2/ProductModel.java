package cop4027.project2;

import java.util.List;

public class ProductModel {
	private List<Product> products;

	public ProductModel(List<Product> products) {
		replaceProducts(products);
	}
	
	public List<Product> getProducts() {
		return products;
	}
	
	public void replaceProducts(List<Product> newProducts) {
		products = newProducts;
	}
}
