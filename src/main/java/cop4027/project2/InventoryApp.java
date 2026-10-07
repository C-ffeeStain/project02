package cop4027.project2;

import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class InventoryApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        
        DatabaseConfig config = DatabaseConfig.load("/database.properties");
		ProductRepository repository = new JbdcProductRepository(config);
        
		ProductModel model = new ProductModel(repository.findAll());
	    ProductView view = new ProductView(model);
	    ProductController controller =
	            new ProductController(model, view, repository);
		
	    stage.setTitle("Inventory Manager");
	    
        stage.setScene(new Scene(new StackPane(view), 640, 480));
        stage.show();
    }

    public static void main(String[] args) {
    	launch();
    }

}