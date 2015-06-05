package bi.jug.jbehave.cart;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bi.jug.jbehave.customer.Customer;
import bi.jug.jbehave.points.LoyaltyPointRedeemer;

/**
 * A ShoppingCart is assigned to a certain customer. It holds all items a customer adds to the cart. The cart can also
 * redeem codes and calculate it's price based on the {@link ShoppingCartItem}s
 */
public class ShoppingCart {

    private Logger LOG = LoggerFactory.getLogger( ShoppingCart.class.getName() );

    private List<ShoppingCartItem> cartItems;

    private List<BigDecimal> promoDiscounts;

    private PromoCodeRedeemer promoCodeRedeemer;

    private LoyaltyPointRedeemer loyaltyPointRedeemer;

    private Customer customer;

    public ShoppingCart( Customer customer ) {
        this.cartItems = new ArrayList();
        this.promoDiscounts = new ArrayList();
        this.customer = customer;

        this.promoCodeRedeemer = PromoCodeRedeemer.getInstance();
        this.loyaltyPointRedeemer = new LoyaltyPointRedeemer( customer );

    }

    public Customer getCustomer() {
        return customer;
    }

    /*
     * Adds a {@link ShoppingCartItem} to the cart.
     */
    public void addItem( ShoppingCartItem shoppingCartItem ) {
        cartItems.add( shoppingCartItem );
    }

    /**
     * Redeems a given promo code. Returns true if the code was accepted, false if not.
     * 
     * @param promoCode
     * @return
     */
    public boolean redeemPromoCode( String promoCode ) {
        if ( !promoCodeRedeemer.isValid( promoCode ) ) {
            return false;
        }

        promoDiscounts.add( promoCodeRedeemer.getDiscount( promoCode ) );

        return true;
    }

    /**
     * Sum up all {@link ShoppingCartItem} prices and quantities.
     * 
     * @return
     */
    public BigDecimal getTotal() {
        return cartItems.stream().map( item -> item.getPrice().multiply( item.getQuantity() ) ).reduce( BigDecimal.ZERO, BigDecimal::add );
    }

    /**
     * Sum up all {@link ShoppingCartItem} prices and quantities reduced by redeemed promos and/or loyalty points.
     * 
     * @return
     */
    public BigDecimal getDiscountedPrice() {
        BigDecimal discounts = promoDiscounts.stream().reduce( BigDecimal.ZERO, BigDecimal::add );

        discounts = discounts.add( loyaltyPointRedeemer.calculatePointDiscount() );

        return getTotal().subtract( discounts );
    }

    public List<ShoppingCartItem> getItems() {
        return cartItems;
    }
}
