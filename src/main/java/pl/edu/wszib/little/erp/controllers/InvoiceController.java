package pl.edu.wszib.little.erp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.little.erp.exceptions.ProductAlreadyExistsException;
import pl.edu.wszib.little.erp.exceptions.ProductShortageException;
import pl.edu.wszib.little.erp.models.Invoice;
import pl.edu.wszib.little.erp.models.InvoicePosition;
import pl.edu.wszib.little.erp.models.ViewModels.AddInvoice;
import pl.edu.wszib.little.erp.models.ViewModels.AddInvoicePosition;
import pl.edu.wszib.little.erp.services.ICustomerService;
import pl.edu.wszib.little.erp.services.IInvoiceService;
import pl.edu.wszib.little.erp.services.IProductService;
import pl.edu.wszib.little.erp.session.Session;

import javax.annotation.Resource;

@Controller
public class InvoiceController
{
    @Autowired
    IInvoiceService invoiceService;

    @Autowired
    ICustomerService customerService;

    @Autowired
    IProductService productService;

    @Resource
    Session session;

    @RequestMapping(value = "/invoices", method = RequestMethod.GET)
    public String invoices (Model model)
    {
        model.addAttribute("logged", this.session.isLogged());
        if(!this.session.isLogged()) return "redirect:/login";

        model.addAttribute("invoices", this.invoiceService.getUserInvoices(this.session.getUser().getId()));
        return "invoices";
    }

    @RequestMapping(value = "/invoices/add", method = RequestMethod.GET)
    public String add (Model model)
    {
        model.addAttribute("logged", this.session.isLogged());
        if(!this.session.isLogged()) return "redirect:/login";

        model.addAttribute("invoice", new Invoice());
        model.addAttribute("addInvoice", new AddInvoice());
        model.addAttribute("customers", this.customerService.getUserCustomers(this.session.getUser().getId()));
        model.addAttribute("add", true);
        model.addAttribute("error", "");

        return "addinvoice";
    }

    @RequestMapping(value = "/invoices/add", method = RequestMethod.POST)
    public String add (@ModelAttribute AddInvoice addInvoice, Model model)
    {
        model.addAttribute("logged", this.session.isLogged());
        if(!this.session.isLogged()) return "redirect:/login";
        var invoice = new Invoice();
        try
        {
            invoice.setUser(this.session.getUser());
            invoice.setSalesInvoice(addInvoice.isSalesInvoice());
            invoice.setCustomer(this.customerService.getCustomerByCode(addInvoice.getCustomerCode()));
            invoiceService.addInvoice(invoice);
        } catch(Exception e)
        {
            model.addAttribute("invoice", invoice);
            model.addAttribute("error", "Error occurred while making invoice");
            model.addAttribute("add", true);
            return "addinvoice";
        }

        return "redirect:/invoices";
    }

    @RequestMapping(value = "/invoices/addposition/{invoiceId}", method = RequestMethod.GET)
    String addPosition(@PathVariable int invoiceId, Model model)
    {
        model.addAttribute("logged", this.session.isLogged());
        if(!this.session.isLogged()) return "redirect:/login";

        model.addAttribute("position", new InvoicePosition());
        model.addAttribute("invoiceId", invoiceId);
        model.addAttribute("addPosition", new AddInvoicePosition());
        model.addAttribute("products", this.productService.getUserProducts(this.session.getUser().getId()));
        model.addAttribute("error", "");

        return "addposition";
    }

    @RequestMapping(value = "/invoices/addposition/{invoiceId}", method = RequestMethod.POST)
    String addPosition(@PathVariable int invoiceId, @ModelAttribute AddInvoicePosition addInvoicePosition, Model model)
    {
        model.addAttribute("logged", this.session.isLogged());
        if(!this.session.isLogged()) return "redirect:/login";

        var invoice = this.invoiceService.getInvoiceById(invoiceId);
        var invoicePosition = new InvoicePosition();
        boolean shortage = false;
        try
        {
            var product = productService.getProductByCode(addInvoicePosition.getProductCode());

            if(invoice.isSalesInvoice()) product.setQuantity(product.getQuantity() - addInvoicePosition.getQuantity());
            else product.setQuantity(product.getQuantity() + addInvoicePosition.getQuantity());

            if(product.getQuantity() < 0)
            {
                shortage = true;
                throw new ProductShortageException();
            }

            invoicePosition.setQuantity(addInvoicePosition.getQuantity());
            invoicePosition.setProduct(product);
            invoice.getPositions().add(invoicePosition);
            invoiceService.updateInvoice(invoice);
            productService.updateProduct(product);
        } catch(Exception e)
        {
            model.addAttribute("position", invoicePosition);
            model.addAttribute("invoiceId", invoiceId);
            model.addAttribute("addPosition", addInvoicePosition);
            model.addAttribute("products", this.productService.getUserProducts(this.session.getUser().getId()));
            if(shortage) model.addAttribute("error", "There is not enough amount of this product!");
            else model.addAttribute("error", "");

            return "redirect:/invoices/addposition/"+invoiceId;
        }

        return "redirect:/invoices";
    }

    @RequestMapping(value = "/invoices/confirm/{id}", method = RequestMethod.GET)
    public String confirm(Model model, @PathVariable int id)
    {
        model.addAttribute("logged", this.session.isLogged());
        if(!this.session.isLogged()) return "redirect:/login";

        var invoice = invoiceService.getInvoiceById(id);
        invoice.setConfirmed(true);

        invoiceService.updateInvoice(invoice);

        return "redirect:/invoices";
    }
}
