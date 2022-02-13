package pl.edu.wszib.little.erp.repos;

import pl.edu.wszib.little.erp.models.Product;

import java.util.List;
import java.util.Optional;

public interface IProdcutRepository
{
    List<Product> getUserProducts(int userId);
    Optional<Product> getProductByCode(String code);
    void addProduct(Product product);
    void updateProduct(Product product);
}
