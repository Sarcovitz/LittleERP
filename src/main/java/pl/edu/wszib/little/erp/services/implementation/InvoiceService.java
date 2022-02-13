package pl.edu.wszib.little.erp.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.little.erp.exceptions.NoCustomerWithThisCodeException;
import pl.edu.wszib.little.erp.models.Customer;
import pl.edu.wszib.little.erp.models.Invoice;
import pl.edu.wszib.little.erp.repos.IInvoiceRepository;
import pl.edu.wszib.little.erp.services.IInvoiceService;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService implements IInvoiceService
{
    @Autowired
    IInvoiceRepository invoiceRepository;

    @Override
    public List<Invoice> getUserInvoices(int userId)
    {
        return this.invoiceRepository.getUserInvoices(userId);
    }

    @Override
    public void addInvoice(Invoice invoice)
    {
        this.invoiceRepository.addInvoice(invoice);
    }

    @Override
    public Invoice getInvoiceById(int id)
    {
        Optional<Invoice> invoiceBox = this.invoiceRepository.getInvoiceById(id);
        if(invoiceBox.isEmpty()) throw new NoCustomerWithThisCodeException();

        Invoice invoice = invoiceBox.get();

        return invoice;
    }

    @Override
    public void updateInvoice(Invoice invoice)
    {
        this.invoiceRepository.updateInvoice(invoice);
    }
}
