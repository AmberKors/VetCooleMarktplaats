package nl.marktplaats.resources;

import nl.marktplaats.App;
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
public class CreateNewUserIT {

    @ArquillianResource
    private URL deploymentURL;

    private String userResource;
    private String userURI = "/api/users";


    @Before
    public void setup() {
        userResource = deploymentURL + userURI;
    }

    @Deployment
    public static Archive<?> createDeployment() {
        WebArchive archive = ShrinkWrap.create(WebArchive.class, "CreateNewUserIT.war")
                .addPackages(true, App.class.getPackage())
                .addAsWebInfResource("META-INF/beans-test.xml", "META-INF/beans.xml")
                .addAsResource("META-INF/persistence-test.xml", "META-INF/persistence.xml");

        System.out.println(archive.toString(true));
        return archive;

    }

    @Test
    public void whenCreationIsSuccesfull(){
        Client http = ClientBuilder.newClient();
        User u = User.builder().id(1).firstName("Richard").lastName("Kameel").username("Rkam29").password("Zomer2021").build();


        String CreatedUser= http
                .target(userResource)
                .request().post(entity(u, APPLICATION_JSON), String.class);

        System.out.println(CreatedUser);

        assertThat(CreatedUser, containsString("\"id\":1"));
        assertThat(CreatedUser, containsString("\"firstName\":\"Richard\""));
        assertThat(CreatedUser, containsString("\"lastName\":\"Kameel\""));
        assertThat(CreatedUser, containsString("\"username\":\"Rkam29\""));
        assertThat(CreatedUser, containsString("\"password\":\"Zomer2021\""));
    }
}
