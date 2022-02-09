package pl.edu.wszib.little.erp.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.edu.wszib.little.erp.exceptions.LoginInUseException;
import pl.edu.wszib.little.erp.exceptions.OnLoginException;
import pl.edu.wszib.little.erp.models.User;
import pl.edu.wszib.little.erp.services.IAuthenticateService;
import pl.edu.wszib.little.erp.session.Session;
import pl.edu.wszib.little.erp.validators.LoginValidator;

import javax.annotation.Resource;

@Controller
public class LoginController
{
    @Resource
    Session session;

    @Autowired
    IAuthenticateService authenticateService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginForm(Model model)
    {
        model.addAttribute("logged", this.session.isLogged());
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam String login, @RequestParam String password)
    {
        try
        {
            LoginValidator.validateLogin(login);
            LoginValidator.validatePassword(password);
        } catch(OnLoginException e ){return "redirect:/login";}

        this.authenticateService.authenticate(login, password);

        if(this.session.isLogged()) return "redirect:/main";
        else return "redirect:/login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout()
    {
        this.session.setUser(null);
        return "redirect:/main";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute User user)
    {
        try
        {
            this.authenticateService.register(user);
        }
        catch (OnLoginException | LoginInUseException e) { return "redirect:/register"; }

        return "redirect:/main";
    }

}
