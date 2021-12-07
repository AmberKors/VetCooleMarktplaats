package nl.marktplaats.exceptions;

public class ProductAlreadyInWinkelwagenException extends RuntimeException {
    public ProductAlreadyInWinkelwagenException() {
        super("Product zit al in winkelwagen.");
    }
}
