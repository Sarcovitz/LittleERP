package pl.edu.wszib.little.erp.repos.implementation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.little.erp.models.Customer;
import pl.edu.wszib.little.erp.repos.ICustomerRepository;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

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

    @Override
    public void addCustomer(Customer customer)
    {
        Session session =  this.sessionFactory.openSession();
        Transaction tran = null;
        try
        {
            tran = session.beginTransaction();
            session.save(customer);
            tran.commit();
        } catch  (Exception e)
        {
            if(tran!=null) tran.rollback();
        }
        finally
        {
            session.close();
        }
    }

    @Override
    public Optional<Customer> getCustomerByCode(String code)
    {
        Session session = this.sessionFactory.openSession();
        Query<Customer> query = session.createQuery("FROM pl.edu.wszib.little.erp.models.Customer WHERE code = :code");
        query.setParameter("code", code);
        try
        {
            Customer customer = query.getSingleResult();
            session.close();
            return Optional.of(customer);
        } catch (NoResultException e)
        {
            session.close();
            return Optional.empty();
        }
    }


}
