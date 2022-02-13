package pl.edu.wszib.little.erp.models;

import javax.persistence.*;

@Entity(name = "invoicePositions")
public class InvoicePosition
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.EAGER)
    private Product product;
    private int quantity;

    public InvoicePosition()
    {    }

    public InvoicePosition(int id, Product product, int quantity)
    {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public Product getProduct()
    {
        return product;
    }

    public void setProduct(Product product)
    {
        this.product = product;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    public double getValue()
    {
        double value = quantity * product.getPrice();
        return Math.round(value*100)/100.0;
    }
}
