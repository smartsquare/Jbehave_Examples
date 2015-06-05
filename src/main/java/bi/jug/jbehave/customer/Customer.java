package bi.jug.jbehave.customer;

import bi.jug.jbehave.points.LoyaltyPoints;

/**
 * A customer represents the real world user who interacts with a shopping cart. He can collect {@link LoyaltyPoints}
 * for different actions.
 */
public class Customer {

    private String customerId;

    private String hairColor;

    private String country;

    private LoyaltyPoints loyaltyPoints;

    private Customer( Builder builder ) {
        customerId = builder.customerId;
        hairColor = builder.hairColor;
        country = builder.country;
        loyaltyPoints = builder.loyaltyPoints;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getHairColor() {
        return hairColor;
    }

    public String getCountry() {
        return country;
    }

    public LoyaltyPoints getLoyaltyPoints() {
        return loyaltyPoints;
    }

    /**
     * Set's the customers loyalty point balance back to zero.
     */
    public void resetLoyaltyPoints() {
        loyaltyPoints.setPoints( 0L );
    }

    public static Builder newCustomer() {
        return new Builder();
    }

    public static final class Builder {
        private String customerId;

        private String hairColor;

        private String country;

        private LoyaltyPoints loyaltyPoints;

        private Builder() {
            this.loyaltyPoints = new LoyaltyPoints();
        }

        public Builder withCustomerId( String customerId ) {
            this.customerId = customerId;
            return this;
        }

        public Builder withHairColor( String hairColor ) {
            this.hairColor = hairColor;
            return this;
        }

        public Builder withCountry( String country ) {
            this.country = country;
            return this;
        }

        public Builder withLoyaltyPoints( Long loyalityPoints ) {
            this.loyaltyPoints.addPoints( loyalityPoints );
            return this;
        }

        public Customer build() {
            return new Customer( this );
        }
    }
}
