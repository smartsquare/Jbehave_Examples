package bi.jug.jbehave.cart;

import java.math.BigDecimal;

/**
 * The ShoppingCartItem describes one element of the shopping cart.
 */
public class ShoppingCartItem {

    private BigDecimal price;

    private BigDecimal quantity;

    private String productName;

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public static final class Builder {

        private ShoppingCartItem instance;

        public static Builder newItem() {
            Builder builder = new Builder();
            builder.instance = new ShoppingCartItem();
            return builder;
        }

        public Builder withPrice( BigDecimal price ) {
            instance.price = price;
            return this;
        }

        public Builder withQuantity( BigDecimal quantity ) {
            instance.quantity = quantity;
            return this;
        }

        public Builder withProductName( String productName ) {
            instance.productName = productName;
            return this;
        }

        public ShoppingCartItem build() {

            return instance;

        }

    }

}
