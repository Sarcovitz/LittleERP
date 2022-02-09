package pl.edu.wszib.little.erp.validators;

import pl.edu.wszib.little.erp.exceptions.OnLoginException;

public class LoginValidator
{
    public static void validateLogin(String login)
    {
        if(login == null || login.length() <= 3 || login.length() >= 17) throw new OnLoginException("Login incorrect");
    }

    public static void validatePassword(String pass)
    {
        if(pass == null || pass.length() <= 3 || pass.length() >=17) throw new OnLoginException("Password incorrect");
    }
}
