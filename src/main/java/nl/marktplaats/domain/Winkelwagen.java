package nl.marktplaats.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.marktplaats.exceptions.ProductAlreadyInWinkelwagenException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Winkelwagen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(cascade = ALL, fetch = LAZY)
    private List<Product> products = new ArrayList<>();

    public void addProduct(Product p) {
        HashSet<Product> uniqueProducts = new HashSet<>(products);
        uniqueProducts.add(p);
        if (uniqueProducts.size() != products.size()) {
            this.products.add(p);
        } else {
            throw new ProductAlreadyInWinkelwagenException();
        }
    }

    public void removeProduct(Product p) {
        this.products.remove(p);
    }

    public List<Product> getProducts() {
        return products;
    }
}
