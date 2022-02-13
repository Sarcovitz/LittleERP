package pl.edu.wszib.little.erp.repos.implementation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.little.erp.models.Customer;
import pl.edu.wszib.little.erp.models.Invoice;
import pl.edu.wszib.little.erp.models.Product;
import pl.edu.wszib.little.erp.repos.IInvoiceRepository;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

@Repository
public class InvoiceRepository implements IInvoiceRepository
{
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Invoice> getUserInvoices(int userId)
    {
        Session session = this.sessionFactory.openSession();
        Query<Invoice> query = session.createQuery("FROM pl.edu.wszib.little.erp.models.Invoice WHERE user.id = :userId");
        query.setParameter("userId", userId);
        List<Invoice> result = query.getResultList();
        session.close();
        return result;
    }

    @Override
    public void addInvoice(Invoice invoice)
    {
        Session session =  this.sessionFactory.openSession();
        Transaction tran = null;
        try
        {
            tran = session.beginTransaction();
            session.save(invoice);
            tran.commit();
        }
        catch  (Exception e)
        {
            if(tran!=null) tran.rollback();
        }
        finally
        {
            session.close();
        }
    }

    @Override
    public Optional<Invoice> getInvoiceById(int id)
    {
        Session session = this.sessionFactory.openSession();
        Query<Invoice> query = session.createQuery("FROM pl.edu.wszib.little.erp.models.Invoice WHERE id = :id");
        query.setParameter("id", id);
        try
        {
            Invoice invoice = query.getSingleResult();
            session.close();
            return Optional.of(invoice);
        } catch (NoResultException e)
        {
            session.close();
            return Optional.empty();
        }
    }

    @Override
    public void updateInvoice(Invoice invoice)
    {
        Session session =  this.sessionFactory.openSession();
        Transaction tran = null;
        try
        {
            tran = session.beginTransaction();
            session.update(invoice);
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
