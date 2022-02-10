package pl.edu.wszib.little.erp.models;

import javax.persistence.*;

@Entity(name = "customers")
public class Customer
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    private String name;
    private String code;
    private String nip;
    private String city;
    private String address;
    private String mailOrPhone;

    public Customer()
    {
    }

    public Customer(int id, User user, String name, String code, String nip, String city, String address)
    {
        this.id = id;
        this.user = user;
        this.name = name;
        this.code = code;
        this.nip = nip;
        this.city = city;
        this.address = address;
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

    public String getNip()
    {
        return nip;
    }

    public void setNip(String nip)
    {
        this.nip = nip;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getMailOrPhone()
    {
        return mailOrPhone;
    }

    public void setMailOrPhone(String mailOrPhone)
    {
        this.mailOrPhone = mailOrPhone;
    }
}
