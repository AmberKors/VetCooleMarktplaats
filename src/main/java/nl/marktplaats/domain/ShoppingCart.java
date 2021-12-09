package nl.marktplaats.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.marktplaats.exceptions.ProductAlreadyInShoppingCartException;

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
public class ShoppingCart implements AbstractEntity<Integer> {
    @Id
    @GeneratedValue
    private Integer id;

    @OneToMany(cascade = ALL, fetch = LAZY)
    private List<Product> products = new ArrayList<>();

    public void addProduct(Product p) {
        HashSet<Product> uniqueProducts = new HashSet<>(products);
        uniqueProducts.add(p);
        if (uniqueProducts.size() != products.size()) {
            this.products.add(p);
        } else {
            throw new ProductAlreadyInShoppingCartException();
        }
    }

    public void removeProduct(Product p) {
        this.products.remove(p);
    }

    public List<Product> getProducts() {
        return products;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }
}
