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
    private boolean confirmed;
    @OneToMany(fetch = FetchType.EAGER)
    List<InvoicePosition> positions;
}
