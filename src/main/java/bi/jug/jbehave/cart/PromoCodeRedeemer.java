package bi.jug.jbehave.cart;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * The PromoCodeRedeemer knows which promo codes are valid for the system and the discount a certain code will assigned
 * to.
 */
public class PromoCodeRedeemer {

    private static PromoCodeRedeemer redeemer = new PromoCodeRedeemer();

    private static Map<String, BigDecimal> codes = new HashMap() {
        {
            put( "ABCD10", BigDecimal.TEN );
            put( "JZV25", BigDecimal.valueOf( 25 ) );
            put( "HAPPY5", BigDecimal.valueOf( 5 ) );
        }
    };

    public static PromoCodeRedeemer getInstance() {
        return redeemer;
    }

    /**
     * Checks if the given promo code is valid or not.
     * 
     * @param promoCode
     * @return
     */
    public boolean isValid( String promoCode ) {
        return codes.containsKey( promoCode );
    }

    /**
     * Returns the discount which is assigned to the given promo code
     * 
     * @param promoCode
     * @return
     */
    public BigDecimal getDiscount( String promoCode ) {
        if ( !isValid( promoCode ) ) {
            return BigDecimal.ZERO;
        }

        return codes.get( promoCode );
    }
}
