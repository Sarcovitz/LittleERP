package pl.edu.wszib.little.erp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.little.erp.models.Customer;
import pl.edu.wszib.little.erp.services.ICustomerService;
import pl.edu.wszib.little.erp.session.Session;

import javax.annotation.Resource;

@Controller
public class CustomerController
{
    @Autowired
    ICustomerService customerService;

    @Resource
    Session session;

    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public String customers(Model model)
    {
        model.addAttribute("logged", this.session.isLogged());
        if(!this.session.isLogged()) return "redirect:/login";

        model.addAttribute("customers", this.customerService.getUserCustomers(this.session.getUser().getId()));

        return "customers";
    }

    @RequestMapping(value = "/customers/add", method = RequestMethod.GET)
    public String add(Model model)
    {
        model.addAttribute("logged", this.session.isLogged());
        if(!this.session.isLogged()) return "redirect:/login";

        model.addAttribute("customer", new Customer());
        model.addAttribute("add", true);
        model.addAttribute("error", "");

        return "addcustomer";
    }

    @RequestMapping(value = "/customers/add", method = RequestMethod.POST)
    public String add(@ModelAttribute Customer customer, Model model)
    {
        model.addAttribute("logged", this.session.isLogged());
        if(!this.session.isLogged()) return "redirect:/login";
        try
        {
            customer.setUser(this.session.getUser());
            customerService.addCustomer(customer);
        } catch(Exception e)
        {
            model.addAttribute("customer", customer);
            model.addAttribute("error", "Customer with this code already exists");
            model.addAttribute("add", true);
            return "addcustomer";
        }

        return "redirect:/customers";
    }

}
