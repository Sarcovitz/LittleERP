package pl.edu.wszib.little.erp.services.implementation;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.little.erp.exceptions.CustomerAlreadyExistsException;
import pl.edu.wszib.little.erp.exceptions.NoCustomerWithThisCodeException;
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

    @Override
    public Customer getCustomerByCode(String code)
    {
        Optional<Customer> customerBox = this.customerRepository.getCustomerByCode(code);
        if(customerBox.isEmpty()) throw new NoCustomerWithThisCodeException();

        Customer customer = customerBox.get();

        return customer;
    }
}
