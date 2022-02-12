package pl.edu.wszib.little.erp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.little.erp.models.Product;
import pl.edu.wszib.little.erp.services.IProductService;
import pl.edu.wszib.little.erp.session.Session;

import javax.annotation.Resource;

@Controller
public class ProductController
{
    @Autowired
    IProductService productService;

    @Resource
    Session session;

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    String products (Model model)
    {
        model.addAttribute("logged", this.session.isLogged());
        if(!this.session.isLogged()) return "redirect:/login";

        model.addAttribute("products", this.productService.getUserProducts(this.session.getUser().getId()));

        return "products";
    }

    @RequestMapping(value = "/products/add", method = RequestMethod.GET)
    public String add (Model model)
    {
        model.addAttribute("logged", this.session.isLogged());
        if(!this.session.isLogged()) return "redirect:/login";

        model.addAttribute("error", "");
        model.addAttribute("add", true);
        model.addAttribute("product", new Product());
        return "addproduct";
    }

    @RequestMapping(value = "/products/add", method = RequestMethod.POST)
    public String add (@ModelAttribute Product product, Model model)
    {
        model.addAttribute("logged", this.session.isLogged());
        if(!this.session.isLogged()) return "redirect:/login";
        try
        {
            product.setUser(this.session.getUser());
            productService.addProduct(product);
        } catch(Exception e)
        {
            model.addAttribute("product", product);
            model.addAttribute("error", "Product with this code already exists");
            model.addAttribute("add", true);
            return "addproduct";
        }

        return "redirect:/products";
    }
}
