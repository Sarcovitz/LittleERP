package pl.edu.wszib.little.erp.services.implementation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.little.erp.exceptions.CustomerAlreadyExistsException;
import pl.edu.wszib.little.erp.models.Customer;
import pl.edu.wszib.little.erp.repos.ICustomerRepository;
import pl.edu.wszib.little.erp.services.ICustomerService;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements ICustomerService
{
    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    ICustomerRepository customerRepository;

    @Override
    public List<Customer> getUserCustomers(int userId)
    {
        return this.customerRepository.getUserCustomers(userId);
    }

    @Override
    public void addCustomer(Customer customer)
    {
        Optional<Customer> customerBox = this.customerRepository.getCustomerByCode(customer.getCode());

        if(customerBox.isPresent()) throw new CustomerAlreadyExistsException();

        this.customerRepository.addCustomer(customer);
    }
}
