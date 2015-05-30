package bi.jug.jbehave.cart;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

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

    public boolean isValid( String promoCode ) {
        return codes.containsKey( promoCode );
    }

    public BigDecimal getDiscount( String promoCode ) {
        if ( !isValid( promoCode ) ) {
            return BigDecimal.ZERO;
        }

        return codes.get( promoCode );
    }
}
