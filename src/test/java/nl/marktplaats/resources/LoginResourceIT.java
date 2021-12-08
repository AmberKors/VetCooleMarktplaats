package nl.marktplaats.resources;

import nl.marktplaats.App;
import nl.marktplaats.dao.Dao;
import nl.marktplaats.dao.UserDao;
import nl.marktplaats.domain.SimplifiedUser;
import nl.marktplaats.domain.User;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import java.net.URL;

import static javax.ws.rs.client.Entity.entity;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;


@RunWith(Arquillian.class)
public class LoginResourceIT {

    @ArquillianResource
    private URL deploymentURL;

    private String loginResource;
    private String loginURI = "/api/login";

    @Before
    public void setup() {
        loginResource = deploymentURL + loginURI;
    }

    @Deployment
    public static Archive<?> createDeployment() {
        WebArchive archive = ShrinkWrap.create(WebArchive.class, "LoginResourceIT.war")
                .addClass(App.class) // dont forget!
                .addClass(User.class)
                .addClass(SimplifiedUser.class)
                .addClass(Dao.class)
                .addClass(UserDao.class)
                .addClass(LoginResource.class)
                .addAsWebInfResource("test-beans.xml", "beans.xml")
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml");

        System.out.println(archive.toString(true));
        return archive;

    }

    @Test
    public void whenLoginIsSuccesfull(){
        Client http = ClientBuilder.newClient();
        User u = User.builder().id(1).firstName("Richard").lastName("Kameel").username("Rkam29").password("Zomer2021").build();

        String LoggedUser= http
                .target(loginResource)
                .request().post(entity(u, APPLICATION_JSON), String.class);

        System.out.println(LoggedUser);

        assertThat(LoggedUser, containsString("\"id\":\"1\""));
        assertThat(LoggedUser, containsString("\"firstName\":\"Richard\""));
        assertThat(LoggedUser, containsString("\"surname\":\"Kameel\""));
        assertThat(LoggedUser, containsString("\"password\":\"Rkam29\""));
        assertThat(LoggedUser, containsString("\"password\":\"Rkam29\""));
    }
}
