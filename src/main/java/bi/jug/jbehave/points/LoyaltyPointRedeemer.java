package bi.jug.jbehave.points;

import java.math.BigDecimal;

import bi.jug.jbehave.customer.Customer;

/**
 * The {@link LoyaltyPointRedeemer} knows how to handle loyalty points.
 */
public class LoyaltyPointRedeemer {

    public static final BigDecimal LOYALTY_POINT_FAKTOR = BigDecimal.valueOf( 100 );

    public static final int MIN_LOYALITY_POINTS = 100;

    private final Customer customer;

    public LoyaltyPointRedeemer( Customer customer ) {
        this.customer = customer;
    }

    /**
     * Calculates the customers loyalty point discount and resets his point balance.
     *
     * @return
     */
    public BigDecimal calculatePointDiscount() {
        Long loyaltyPoints = customer.getLoyaltyPoints().getPoints();
        if ( loyaltyPoints <= MIN_LOYALITY_POINTS ) {
            return BigDecimal.ZERO;
        }

        if ( customer.getCountry().equalsIgnoreCase( "usa" ) &&
            customer.getHairColor().equalsIgnoreCase( "green" ) ) {
            loyaltyPoints = loyaltyPoints * 2;
        }

        customer.resetLoyaltyPoints();
        return BigDecimal.valueOf( loyaltyPoints ).divide( LOYALTY_POINT_FAKTOR );

    }
}
