package pl.edu.wszib.little.erp.models;

import org.springframework.context.annotation.EnableMBeanExport;

import javax.persistence.*;

@Entity(name = "products")
public class Product
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    private String name;
    private String code;
    private String ean;
    private Double price;
    private Integer quantity;
    private Integer vat;

    public Product()
    {
    }

    public Product(int id, User user, String name, String code, String ean, Double price, Integer quantity, Integer vat)
    {
        this.id = id;
        this.user = user;
        this.name = name;
        this.code = code;
        this.ean = ean;
        this.price = price;
        this.quantity = quantity;
        this.vat = vat;
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

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getEan()
    {
        return ean;
    }

    public void setEan(String ean)
    {
        this.ean = ean;
    }

    public Double getPrice()
    {
        return price;
    }

    public void setPrice(Double price)
    {
        this.price = price;
    }

    public Integer getQuantity()
    {
        return quantity;
    }

    public void setQuantity(Integer quantity)
    {
        this.quantity = quantity;
    }

    public Integer getVat()
    {
        return vat;
    }

    public void setVat(Integer vat)
    {
        this.vat = vat;
    }
}
