package bi.jug.jbehave.steps;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

import java.math.BigDecimal;
import java.util.List;

import org.jbehave.core.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bi.jug.jbehave.cart.ShoppingCart;
import bi.jug.jbehave.cart.ShoppingCartItem;
import bi.jug.jbehave.customer.Customer;

public class CustomerShoppingCartSteps {

    private Logger LOG = LoggerFactory.getLogger( this.getClass().getName() );

    private ShoppingCart cart;

    @Given( "a shopping cart" )
    public void aShoppingCart() {
        cart = new ShoppingCart( Customer.newCustomer().build() );
    }

    @When( "a customer adds an item to his shopping cart" )
    public void addItem() {
        ShoppingCartItem.Builder builder = ShoppingCartItem.Builder.newItem();
        builder.withPrice( BigDecimal.valueOf( 100l ) );
        builder.withQuantity( BigDecimal.ONE );
        builder.withProductName( "Large Gizmo" );

        cart.addItem( builder.build() );
    }

    @When( "the customer add $quantity $name to his shopping cart with a price of $price dollar (each)" )
    public void addCertainItem( @Named( "quantity" ) long quantity,
                                @Named( "name" ) String name,
                                @Named( "price" ) BigDecimal price ) {

        ShoppingCartItem.Builder builder = ShoppingCartItem.Builder.newItem();
        builder.withPrice( price );
        builder.withQuantity( BigDecimal.valueOf( quantity ) );
        builder.withProductName( name );

        cart.addItem( builder.build() );

    }

    @Then( "the shopping cart contains $quantity different item" )
    @Alias( "the shopping cart contains $quantity different items" )
    public void verifyCartItems( @Named( "quantity" ) int quantity ) {
        List<ShoppingCartItem> items = cart.getItems();
        assertThat( items.size(), equalTo( quantity ) );
    }

    @Then( "the shopping cart total is $total dollar" )
    public void verifyCartItems( @Named( "total" ) BigDecimal total ) {
        assertThat( String.format( "A cart total of %s is expected, but the cart has a total of %s", total, cart.getTotal() ),
            cart.getTotal(), equalTo( total ) );
    }

}
