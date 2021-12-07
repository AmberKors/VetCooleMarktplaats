package nl.marktplaats.exceptions;

public class ProductAlreadyInShoppingCartException extends RuntimeException {
    public ProductAlreadyInShoppingCartException() {
        super("Product zit al in winkelwagen.");
    }
}
