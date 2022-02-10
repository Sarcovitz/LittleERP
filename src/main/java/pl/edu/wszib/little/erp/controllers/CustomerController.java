package pl.edu.wszib.little.erp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
        model.addAttribute("customers", this.customerService.getUserCustomers(this.session.getUser().getId()));

        return "customers";
    }
}
