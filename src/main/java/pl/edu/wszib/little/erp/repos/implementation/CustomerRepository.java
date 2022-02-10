package pl.edu.wszib.little.erp.repos.implementation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.little.erp.models.Customer;
import pl.edu.wszib.little.erp.repos.ICustomerRepository;

import java.util.List;

@Repository
public class CustomerRepository implements ICustomerRepository
{
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Customer> getUserCustomers(int userId)
    {
        Session session = this.sessionFactory.openSession();
        Query<Customer> query = session.createQuery("FROM pl.edu.wszib.little.erp.models.Customer WHERE user.id = :userId");
        query.setParameter("userId", userId);
        List<Customer> result = query.getResultList();
        session.close();
        return result;
    }
}
