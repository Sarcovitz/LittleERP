package pl.edu.wszib.little.erp.repos;

import pl.edu.wszib.little.erp.models.Customer;

import java.util.List;
import java.util.Optional;

public interface ICustomerRepository
{
    List<Customer> getUserCustomers(int userId);
    void addCustomer(Customer customer);
    Optional<Customer> getCustomerByCode(String code);
}
