package pl.edu.wszib.little.erp.services;

import pl.edu.wszib.little.erp.models.Customer;

import java.util.List;
import java.util.Optional;

public interface ICustomerService
{
    List<Customer> getUserCustomers (int userId);
    void addCustomer (Customer customer);
    Customer getCustomerByCode(String code);
}
