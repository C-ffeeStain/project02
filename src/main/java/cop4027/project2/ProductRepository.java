package cop4027.project2;

import java.util.List;

public interface ProductRepository {
    List<Product> findAll();

    List<Product> search(String text);

    void add(String name, String category,
             int quantity, double price);

    void update(Product product);

    void delete(long id);
}