package nl.marktplaats.resources;

import nl.marktplaats.App;
import nl.marktplaats.domain.Category;
import nl.marktplaats.domain.Product;
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
public class ProductResourceIT {

    @ArquillianResource
    private URL deploymentURL;
    private String productResource;
    private String uproductResource;
    private String uproductURI = "/api/products/1";
    private String productURI = "/api/products";

    @Before
    public void setup() {
        productResource = deploymentURL + productURI;
        uproductResource = deploymentURL + uproductURI;
    }

    @Deployment
    public static Archive<?> createDeployment() {
        WebArchive archive = ShrinkWrap.create(WebArchive.class, "UpdateProduct.war")
                .addPackages(true, App.class.getPackage())
                .addAsWebInfResource("META-INF/beans-test.xml", "META-INF/beans.xml")
                .addAsResource("META-INF/persistence-test.xml", "META-INF/persistence.xml");

        System.out.println(archive.toString(true));
        return archive;
    }

    @Test
    public void whenUpdateProductIsSuccessful() {
        Client http = ClientBuilder.newClient();
        Product p = Product.builder().id(1).name("Puppies").price(12.99).description("Zeer schattig").category(Category.DIEREN_EN_TOEBEHOREN).build();

        String createdProduct = http
                .target(productResource)
                .request()
                .post(entity(p, APPLICATION_JSON), String.class);
        System.out.println(createdProduct);

        p.setName("Meer puppies");
        p.setDescription("Schattiger dan de vorige");

        String updatedProduct = http
                .target(uproductResource)
                .request()
                .put(entity(p, APPLICATION_JSON), String.class);
        System.out.println(updatedProduct);

        assertThat(updatedProduct, containsString("1"));
        assertThat(updatedProduct, containsString("Meer"));
        assertThat(updatedProduct, containsString("vorige"));
    }
}
