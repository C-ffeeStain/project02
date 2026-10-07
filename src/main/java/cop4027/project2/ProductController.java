package cop4027.project2;

public class ProductController {
	ProductModel model;
	ProductView view;
	ProductRepository repository;
	
	public ProductController(ProductModel pModel, ProductView pView, ProductRepository pRepository) {
		model = pModel;
		view = pView;
		repository = pRepository;
		
		connectHandlers();
	}
	
	private void addClicked() {
		String name = view.getEnteredName();
		if (name.isBlank()) {
			view.warn("Missing Name", "The product name must not be blank.");
			return;
		}
		String category = view.getEnteredCategory();
		if (category.isBlank()) {
			view.warn("Missing Category", "The product category must not be blank.");
			return;
		}
		
		String quantity = view.getEnteredCategory();
		if (category.isBlank() || !category.c) {
			view.warn("Missing Category", "The product category must not be blank.");
			return;
		}
	}
	
	public void connectHandlers() {
		view.getAddButton().setOnAction(event -> addClicked());
	}

}
