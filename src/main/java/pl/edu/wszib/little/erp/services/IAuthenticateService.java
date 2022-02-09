package pl.edu.wszib.little.erp.services;

import pl.edu.wszib.little.erp.models.User;

public interface IAuthenticateService
{
    void authenticate(String login, String password);
    void register(User user);
}
