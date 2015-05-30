Narrative:
As a Customer
I want to redeem a promo code
In order to get a discount to my shopping cart

Scenario: the customer redeems a promo code which gives a discount of 10 $

Given a customers shopping cart
And the shopping cart contains one item
When the customer redeems the promo code ABCD10
Then is the shopping cart total reduced by 10

Scenario: Test multiple promo codes with different discounts

Given a customers shopping cart
And the shopping cart contains one item
When the customer redeems the promo code <promo_code>
Then is the shopping cart total reduced by <discount>

Examples:
promo_code | discount
ABCD10     | 10
JZV25      | 25
HAPPY5     | 5

Scenario: the customer redeems a not valid promo code

Given a customers shopping cart
And the shopping cart contains one item
When the customer redeems the promo code FOO
Then is the promo code rejected
Then is the shopping cart total not reduced