package pl.edu.wszib.little.erp.services;

import pl.edu.wszib.little.erp.models.Customer;

import java.util.List;

public interface ICustomerService
{
    List<Customer> getUserCustomers (int userId);
}