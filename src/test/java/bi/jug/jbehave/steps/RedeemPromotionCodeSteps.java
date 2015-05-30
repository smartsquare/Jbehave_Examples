package bi.jug.jbehave.steps;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.math.BigDecimal;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bi.jug.jbehave.cart.ShoppingCart;
import bi.jug.jbehave.cart.ShoppingCartItem;

public class RedeemPromotionCodeSteps {

    private Logger LOG = LoggerFactory.getLogger( RedeemPromotionCodeSteps.class.getName() );

    private ShoppingCart cart;

    private boolean redeemResult;

    @Given( "a customers shopping cart" )
    public void aShoppingCart() {
        cart = new ShoppingCart();
    }

    @Given( "the shopping cart contains one item" )
    public void aItemInShoppingCart() {
        ShoppingCartItem.Builder builder = ShoppingCartItem.Builder.newItem();
        builder.withPrice( BigDecimal.valueOf( 100l ) );
        builder.withQuantity( BigDecimal.ONE );
        builder.withProductName( "Large Gizmo" );

        cart.addItem( builder.build() );
    }

    @When( "the customer redeems the promo code $promo_code" )
    public void redeemPromoCode( @Named( "promo_code" ) String promoCode ) {
        redeemResult = cart.redeemPromoCode( promoCode );
    }

    @Then( "is the shopping cart total reduced by $discount" )
    public void verifyDiscount( @Named( "discount" ) BigDecimal discount ) {

        BigDecimal total = cart.getTotal();

        BigDecimal discountedPrice = cart.getDiscountedPrice();

        assertThat( discountedPrice, equalTo( total.subtract( discount ) ) );
    }

    @Then( "is the shopping cart total not reduced" )
    public void verifyNoDiscount() {

        BigDecimal total = cart.getTotal();

        BigDecimal discountedPrice = cart.getDiscountedPrice();

        assertThat( discountedPrice, equalTo( total ) );
    }

    @Then( "is the promo code rejected" )
    public void verifyPromoCodeValidationError() {

        BigDecimal total = cart.getTotal();

        BigDecimal discountedPrice = cart.getDiscountedPrice();

        assertThat( redeemResult, equalTo( Boolean.FALSE ) );
    }

}
