package pl.edu.wszib.little.erp.session;

import org.junit.Assert;
import org.junit.Test;
import pl.edu.wszib.little.erp.models.User;

public class SessionTest
{
    @Test
    public void sessionTest(){
        User user = new User();
        user.setEmail("test@test.pl");
        user.setId(2);
        user.setLogin("Maciek");
        user.setPassword("TESTTEUAGKUSAFJBASJAS");

        Session session = new Session();
        session.setUser(user);

        boolean result = true;

        Assert.assertEquals(result, session.isLogged());

    }
}
