package pl.edu.wszib.little.erp.repos.implementation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.little.erp.models.Customer;
import pl.edu.wszib.little.erp.models.Product;
import pl.edu.wszib.little.erp.repos.IProdcutRepository;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository implements IProdcutRepository
{
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Product> getUserProducts(int userId)
    {
        Session session = this.sessionFactory.openSession();
        Query<Product> query = session.createQuery("FROM pl.edu.wszib.little.erp.models.Product WHERE user.id = :userId");
        query.setParameter("userId", userId);
        List<Product> result = query.getResultList();
        session.close();
        return result;
    }

    @Override
    public Optional<Product> getProductByCode(String code)
    {
        Session session = this.sessionFactory.openSession();
        Query<Product> query = session.createQuery("FROM pl.edu.wszib.little.erp.models.Product WHERE code = :code");
        query.setParameter("code", code);
        try
        {
            Product product = query.getSingleResult();
            session.close();
            return Optional.of(product);
        } catch (NoResultException e)
        {
            session.close();
            return Optional.empty();
        }
    }

    @Override
    public void addProduct(Product product)
    {
        Session session =  this.sessionFactory.openSession();
        Transaction tran = null;
        try
        {
            tran = session.beginTransaction();
            session.save(product);
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
    public void updateProduct(Product product)
    {
        Session session =  this.sessionFactory.openSession();
        Transaction tran = null;
        try
        {
            tran = session.beginTransaction();
            session.update(product);
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
}
