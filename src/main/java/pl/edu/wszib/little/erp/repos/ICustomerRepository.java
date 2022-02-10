package pl.edu.wszib.little.erp.repos;

import pl.edu.wszib.little.erp.models.Customer;

import java.util.List;

public interface ICustomerRepository
{
    List<Customer> getUserCustomers(int userId);
}
