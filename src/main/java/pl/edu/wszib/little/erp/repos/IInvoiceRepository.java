package pl.edu.wszib.little.erp.repos;

import pl.edu.wszib.little.erp.models.Invoice;

import java.util.List;
import java.util.Optional;

public interface IInvoiceRepository
{
    List<Invoice> getUserInvoices(int userId);
    void addInvoice(Invoice invoice);
    Optional<Invoice> getInvoiceById(int id);
    void updateInvoice(Invoice invoice);
}
