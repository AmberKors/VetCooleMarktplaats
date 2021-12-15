package nl.marktplaats.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CustomerUser")
public class User implements AbstractEntity<Integer> {

    @Id
    @GeneratedValue
    private Integer id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private Delivery delivery;
    private String street;
    private String postalcode;
    private String housenumber;

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    @OneToOne
    private ShoppingCart shoppingCart;
}
