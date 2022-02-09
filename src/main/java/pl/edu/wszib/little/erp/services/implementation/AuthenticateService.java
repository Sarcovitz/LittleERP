package pl.edu.wszib.little.erp.services.implementation;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.little.erp.exceptions.LoginInUseException;
import pl.edu.wszib.little.erp.models.User;
import pl.edu.wszib.little.erp.repos.IUserRepository;
import pl.edu.wszib.little.erp.services.IAuthenticateService;
import pl.edu.wszib.little.erp.session.Session;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class AuthenticateService implements IAuthenticateService
{
    @Autowired
    IUserRepository userRepository;

    @Resource
    Session session;

    @Override
    public void authenticate(String login, String password)
    {
        Optional<User> user = this.userRepository.getUserByLogin(login);

        if(user.isEmpty() || !user.get().getPassword().equals(DigestUtils.md5Hex(password))) return;

        this.session.setUser(user.get());
    }

    @Override
    public void register(User userR)
    {
        Optional<User> userBox = this.userRepository.getUserByLogin(userR.getLogin());

        if(userBox.isPresent()) throw new LoginInUseException();

        userR.setPassword(DigestUtils.md5Hex(userR.getPassword()));
        this.userRepository.addUser(userR);
    }
}
