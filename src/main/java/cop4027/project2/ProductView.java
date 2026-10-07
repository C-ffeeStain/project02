package cop4027.project2;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

public class ProductView extends VBox {
	private ProductModel model;
	
	private TextField searchBar = new TextField();
	private Button showAllButton = new Button("Show All");
	
	private TableView<Product> table = new TableView<>();
	
	private TextField nameInput = new TextField();
	private TextField categoryInput = new TextField();
	private TextField quantityInput = new TextField();
	private TextField priceInput = new TextField();
	
	private Button addButton = new Button("Add");
	private Button updateButton = new Button("Update");
	private Button deleteButton = new Button("Delete");


	private List<HBox> createProductInputs() {
		Label nameLabel = new Label("Name:");
		Label categoryLabel = new Label("Category:");
		Label quantityLabel = new Label("Quantity:");
		Label priceLabel = new Label("Name:");
		
		nameInput.setPromptText("Product name...");		
		categoryInput.setPromptText("Product category...");
		quantityInput.setPromptText("Product quantity...");		
		priceInput.setPromptText("Product price...");
		
		List<HBox> hBoxes = new ArrayList<HBox>();
		
		hBoxes.add(new HBox(nameLabel, nameInput));
		hBoxes.add(new HBox(categoryLabel, categoryInput));
		hBoxes.add(new HBox(quantityLabel, quantityInput));
		hBoxes.add(new HBox(priceLabel, priceInput));
		
		return hBoxes;
	}
	
	private void initTableColumns() {
		var columns = table.getColumns();
		
		TableColumn<Product, String> nameCol = new TableColumn<>("Name");
		nameCol.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
		columns.add(nameCol);
		
		TableColumn<Product, String> categoryCol = new TableColumn<>("Category");
		categoryCol.setCellValueFactory(new PropertyValueFactory<Product, String>("category"));
		columns.add(categoryCol);
		
		TableColumn<Product, String> quantityCol = new TableColumn<>("Quantity");
		nameCol.setCellValueFactory(new PropertyValueFactory<Product, String>("quantity"));
		columns.add(quantityCol);
		
		TableColumn<Product, String> priceCol = new TableColumn<>("Price");
		nameCol.setCellValueFactory(new PropertyValueFactory<Product, String>("price"));
		columns.add(priceCol);		
	}
	
	public ProductView(ProductModel pModel) {
		model = pModel;

		setAlignment(Pos.TOP_CENTER);
		
		searchBar.setPromptText("Search...");
		
		showAllButton.setTextAlignment(TextAlignment.CENTER);
		
		VBox.setVgrow(table, Priority.ALWAYS);
		initTableColumns();
		updateProductTable();
		
		List<HBox> hBoxes = createProductInputs();
		
		getChildren().addAll(
			searchBar,
			showAllButton,
			table
		);
		getChildren().addAll(hBoxes);
		HBox buttonHBox = new HBox(addButton, updateButton, deleteButton);
		buttonHBox.setAlignment(Pos.CENTER);
		getChildren().add(buttonHBox);
		
		setSpacing(10);
	}
	
	public Button getShowAllButton() {
		return showAllButton;
	}
	
	public Button getAddButton() {
		return addButton;
	}
	
	public Button getUpdateButton() {
		return updateButton;
	}
	
	public Button getDeleteButton() {
		return deleteButton;
	}
	
	public Product getSelectedProduct() {
		return table.getSelectionModel().getSelectedItem();
	}
	
	public String getSearchPrompt() {
		return searchBar.getText();
	}
	
	public String getEnteredName() {
		return nameInput.getText();
	}
	
	public String getEnteredCategory() {
		return categoryInput.getText();
	}
	
	public String getEnteredQuantity() {
		return quantityInput.getText();
	}
	
	public String getEnteredPrice() {
		return priceInput.getText();
	}
	
	public void updateProductTable() {
		table.getItems().clear();
		table.getItems().addAll(model.getProducts());
 	}
	
	public void setSearchPrompt(String newPrompt) {
		searchBar.setText(newPrompt);
	}
	
	public void warn(String title, String message) {
		Alert warning = new Alert(AlertType.WARNING, message, new ButtonType[] {ButtonType.OK} );
		warning.setHeaderText(title);
		warning.setTitle("Warning");
		warning.showAndWait();
	}
}
