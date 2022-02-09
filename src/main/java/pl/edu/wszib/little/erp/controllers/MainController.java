package pl.edu.wszib.little.erp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.little.erp.session.Session;

import javax.annotation.Resource;

@Controller
public class MainController
{
    @Resource
    Session session;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home()
    {
        return "redirect:/main";
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main(Model model)
    {
        model.addAttribute("logged", this.session.isLogged());
        return "main";
    }
}
