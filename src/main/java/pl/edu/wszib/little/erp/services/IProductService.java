package pl.edu.wszib.little.erp.services;

import pl.edu.wszib.little.erp.models.Product;

import java.util.List;

public interface IProductService
{
    List<Product> getUserProducts(int userId);
    void addProduct(Product product);
}
