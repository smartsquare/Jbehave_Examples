package bi.jug.jbehave.cart;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShoppingCart {

    private Logger LOG = LoggerFactory.getLogger( ShoppingCart.class.getName() );

    private List<ShoppingCartItem> cartItems;

    private List<BigDecimal> promoDiscounts;

    private PromoCodeRedeemer promoCodeRedeemer;

    public ShoppingCart() {
        cartItems = new ArrayList();
        promoDiscounts = new ArrayList();

        promoCodeRedeemer = PromoCodeRedeemer.getInstance();
    }

    public void addItem( ShoppingCartItem shoppingCartItem ) {
        cartItems.add( shoppingCartItem );
    }

    public boolean redeemPromoCode( String promoCode ) {
        if ( !promoCodeRedeemer.isValid( promoCode ) ) {
            return false;
        }

        promoDiscounts.add( promoCodeRedeemer.getDiscount( promoCode ) );

        return true;
    }

    public BigDecimal getTotal() {
        return cartItems.stream().map( item -> item.getPrice().multiply(item.getQuantity()) ).reduce( BigDecimal.ZERO, BigDecimal::add );
    }

    public BigDecimal getDiscountedPrice() {
        BigDecimal discounts = promoDiscounts.stream().reduce( BigDecimal.ZERO, BigDecimal::add );
        return getTotal().subtract( discounts );
    }

    public List<ShoppingCartItem> getItems() {
        return cartItems;
    }
}
