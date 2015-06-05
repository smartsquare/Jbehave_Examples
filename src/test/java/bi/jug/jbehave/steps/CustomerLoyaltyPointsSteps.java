package bi.jug.jbehave.steps;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

import java.math.BigDecimal;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import bi.jug.jbehave.cart.ShoppingCart;
import bi.jug.jbehave.cart.ShoppingCartItem;
import bi.jug.jbehave.customer.Customer;
import bi.jug.jbehave.points.LoyaltyPoints;

public class CustomerLoyaltyPointsSteps {

    private Customer.Builder customerBuilder;

    private Customer customer;

    private ShoppingCart cart;

    @Given( "an existing customer" )
    public void aCustomer() {
        customerBuilder = Customer.newCustomer();
    }

    @Given( "with a loyalty point balance of $loyalty_balance" )
    public void withLoyaltyPoints( @Named( "loyalty_balance" ) Long balance ) {
        customerBuilder.withLoyaltyPoints(balance);
    }

    @Given( "with $hair_color hair" )
    public void withHairColor( @Named( "hair_color" ) String color ) {
        customerBuilder.withHairColor( color );
    }

    @Given( "lives in $country" )
    public void livesInCountry( @Named( "country" ) String country ) {
        customerBuilder.withCountry( country );
    }

    @When( "the customer add an item to his shopping card" )
    public void addItemToCart() {
        cart = new ShoppingCart( customerBuilder.build() );

        ShoppingCartItem.Builder builder = ShoppingCartItem.Builder.newItem();
        builder.withPrice( BigDecimal.valueOf( 100l ) );
        builder.withQuantity( BigDecimal.ONE );
        builder.withProductName( "Large Gizmo" );

        cart.addItem( builder.build() );

    }

    @Then( "is the shopping card total reduced by $cart_reduced_by" )
    public void isShoppingCartTotalReducedBy( @Named( "cart_reduced_by" ) BigDecimal expectedDiscount ) {
        BigDecimal discountedPrice = cart.getDiscountedPrice();
        BigDecimal total = cart.getTotal();

        BigDecimal actualDiscount = total.subtract( discountedPrice );
        assertThat( actualDiscount, equalTo( expectedDiscount ) );
    }

    @Then( "the customers loylaity balance is $expected_loyalty_balance" )
    public void isNewBalance( @Named( "expected_loyalty_balance" ) Long expectedBalance ) {
        Customer customer = cart.getCustomer();

        LoyaltyPoints loyaltyPoints = customer.getLoyaltyPoints();

        assertThat( loyaltyPoints.getPoints(), equalTo( expectedBalance ) );
    }

}
