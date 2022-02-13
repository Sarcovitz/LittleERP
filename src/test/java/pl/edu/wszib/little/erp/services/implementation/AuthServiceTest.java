package pl.edu.wszib.little.erp.services.implementation;

import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import pl.edu.wszib.little.erp.config.AppConfiguration;
import pl.edu.wszib.little.erp.models.User;
import pl.edu.wszib.little.erp.repos.implementation.UserRepository;
import pl.edu.wszib.little.erp.services.IAuthenticateService;
import pl.edu.wszib.little.erp.session.Session;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {AppConfiguration.class})
public class AuthServiceTest
{

    @Autowired
    IAuthenticateService authenticateService;

    @Resource
    Session session;

    @Test
    public void authenticateRightUserTest()
    {
        this.authenticateService.authenticate("test", "test123");

        Assert.assertTrue(this.session.isLogged());
    }
}
