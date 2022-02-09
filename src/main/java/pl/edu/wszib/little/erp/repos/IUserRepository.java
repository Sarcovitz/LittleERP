package pl.edu.wszib.little.erp.repos;

import pl.edu.wszib.little.erp.models.User;

import java.util.Optional;

public interface IUserRepository
{
    Optional<User> getUserByLogin(String login);
    void addUser(User user);
}
