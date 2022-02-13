package pl.edu.wszib.little.erp.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.little.erp.exceptions.CustomerAlreadyExistsException;
import pl.edu.wszib.little.erp.exceptions.NoCustomerWithThisCodeException;
import pl.edu.wszib.little.erp.exceptions.NoProductWithThisCodeException;
import pl.edu.wszib.little.erp.exceptions.ProductAlreadyExistsException;
import pl.edu.wszib.little.erp.models.Customer;
import pl.edu.wszib.little.erp.models.Product;
import pl.edu.wszib.little.erp.repos.IProdcutRepository;
import pl.edu.wszib.little.erp.services.IProductService;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService
{
    @Autowired
    IProdcutRepository productRepository;

    @Override
    public List<Product> getUserProducts(int userId)
    {
        return this.productRepository.getUserProducts(userId);
    }

    @Override
    public void addProduct(Product product)
    {
        Optional<Product> customerBox = this.productRepository.getProductByCode(product.getCode());

        if(customerBox.isPresent()) throw new ProductAlreadyExistsException();

        this.productRepository.addProduct(product);
    }

    @Override
    public Product getProductByCode(String code)
    {
        Optional<Product> productBox = this.productRepository.getProductByCode(code);

        if(productBox.isEmpty()) throw new NoProductWithThisCodeException();

        return productBox.get();
    }

    @Override
    public void updateProduct(Product product)
    {
        productRepository.updateProduct(product);
    }
}
