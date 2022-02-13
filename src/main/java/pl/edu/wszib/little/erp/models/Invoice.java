package pl.edu.wszib.little.erp.models;

import javax.persistence.*;
import java.util.List;

@Entity(name="invoices")
public class Invoice
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    @ManyToOne(fetch = FetchType.EAGER)
    private Customer customer;
    private boolean confirmed = false;
    private boolean salesInvoice = true;
    @OneToMany( cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<InvoicePosition> positions;

    public Invoice(){}

    public Invoice(int id, User user, Customer customer, boolean confirmed, boolean salesInvoice, List<InvoicePosition> positions)
    {
        this.id = id;
        this.user = user;
        this.customer = customer;
        this.confirmed = confirmed;
        this.salesInvoice = salesInvoice;
        this.positions = positions;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public Customer getCustomer()
    {
        return customer;
    }

    public void setCustomer(Customer customer)
    {
        this.customer = customer;
    }

    public boolean isConfirmed()
    {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed)
    {
        this.confirmed = confirmed;
    }

    public boolean isSalesInvoice()
    {
        return salesInvoice;
    }

    public void setSalesInvoice(boolean salesInvoice)
    {
        this.salesInvoice = salesInvoice;
    }

    public List<InvoicePosition> getPositions()
    {
        return positions;
    }

    public void setPositions(List<InvoicePosition> positions)
    {
        this.positions = positions;
    }

    public double getNetSum()
    {
        double sum =0;

        for(InvoicePosition position : this.positions)
        {
            sum += position.getValue() * (1 - (double)position.getProduct().getVat()/100);
        }

        return Math.round(sum*100)/100.0;
    }

    public double getGrossSum()
    {
        double sum =0;

        for(InvoicePosition position : this.positions)
        {
            sum += position.getValue();
        }

        return Math.round(sum*100)/100.0;
    }
}
