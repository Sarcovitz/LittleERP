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
}
