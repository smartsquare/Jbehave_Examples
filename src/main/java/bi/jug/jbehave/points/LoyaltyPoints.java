package bi.jug.jbehave.points;

/**
 * Loyalty points can be collected by a customer and will redeemed in the {@link bi.jug.jbehave.cart.ShoppingCart}
 */
public class LoyaltyPoints {

    private Long balance = 0L;

    public void addPoints( Long loyaltyPoints ) {
        this.balance = balance + loyaltyPoints;
    }

    public Long getPoints() {
        return balance;
    }

    public void setPoints( long points ) {
        this.balance = points;
    }
}
