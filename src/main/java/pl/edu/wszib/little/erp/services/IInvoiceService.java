package pl.edu.wszib.little.erp.services;

import pl.edu.wszib.little.erp.models.Invoice;

import java.util.List;
import java.util.Optional;

public interface IInvoiceService
{
    List<Invoice> getUserInvoices(int userId);
    void addInvoice(Invoice invoice);
    Invoice getInvoiceById(int id);
    void updateInvoice(Invoice invoice);
}
