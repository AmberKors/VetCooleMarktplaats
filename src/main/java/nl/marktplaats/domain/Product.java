package nl.marktplaats.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@NamedQueries({
        @NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p"),
        @NamedQuery(name = "Product.findOne", query = "SELECT p FROM Product p WHERE p.id=:id"),
        // Filters in frontend of backend? (mijn voorkeur frontend)
        @NamedQuery(name = "Product.filterOnName", query = "SELECT p from Product p WHERE p.name LIKE :str"),
        @NamedQuery(name = "Product.filterOnDescription", query = "SELECT p from Product p WHERE p.description LIKE :str"),
        @NamedQuery(name = "Product.filterOnPrice", query = "SELECT p from Product p WHERE p.price BETWEEN :min AND :max"),
        // @NamedQuery(name = "Product.filterOnCategory", query = "SELECT p from Product p WHERE p.category LIKE :str"),
})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(min = 1, max = 200, message = "Maximaal 200 karakters")
    private String name;

    private Category category;

    @Pattern(regexp = "\\d*[.]\\d{2}")
    private double price;
    @Size(min = 1, max = 200, message = "Maximaal 200 karakters")
    private String description;
    //    @PastOrPresent
    private LocalDate datePublished;

    @ManyToOne
    private User user;


    @Override
    public String toString() {
        return "id: " + id +
                " titel: " + name + '\n' +
                "category: " + category + '\n' +
                "omschrijving: " + description + '\n' +
                "prijs: " + price + '\n' +
                "plaatsingsdatum: " + datePublished + '\n';
    }
}

