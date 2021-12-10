package nl.marktplaats.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@NamedQueries({
        @NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p"),
        @NamedQuery(name = "Product.findOneUserProduct", query = "SELECT p FROM Product p where p.id = :id and p.user = :user_id")
})
public class Product implements AbstractEntity<Integer> {

    @Id
    @GeneratedValue
    private int id;

    @Size(min = 1, max = 200, message = "Maximaal 200 karakters")
    private String name;

    private Category category;

    private double price;

    @Size(min = 1, max = 200, message = "Maximaal 200 karakters")
    private String description;

    private LocalDate datePublished;

    @ManyToOne
    private User user;

    @ManyToOne
    private ShoppingCart shoppingCart;

    @Override
    public String toString() {
        return "id: " + id +
                " titel: " + name + '\n' +
                "category: " + category + '\n' +
                "omschrijving: " + description + '\n' +
                "prijs: " + price + '\n' +
                "plaatsingsdatum: " + datePublished + '\n';
    }


    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return id;
    }
}

