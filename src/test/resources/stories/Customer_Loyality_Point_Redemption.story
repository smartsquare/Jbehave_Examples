Meta:

Narrative:
As a customer
I want to get a discount for my earned loyality points
So that I'm willing to collect loyality points

Scenario: A customer with earned loyality points should get a corresponding discount on his cart
Given an existing customer
And with a loyalty point balance of <loyalty_balance>
And with <hair_color> hair
And lives in <country>
When the customer add an item to his shopping card
Then is the shopping card total reduced by <cart_reduced_by>
And the customers loyalty balance is <expected_loyalty_balance>

Examples:
loyalty_balance  | hair_color | country | cart_reduced_by | expected_loyalty_balance | desc
500              | blue       | germany | 5               | 0                         |
99               | blue       | germany | 0               | 99                        | Loyality points only redeemed over 100 points
100              | blue       | germany | 0               | 100                       | Loyality points only redeemed over 100 points
101              | blue       | germany | 1.01            | 0                         | Loyality points redeemed from 101 points
500              | green      | usa     | 10              | 0                         | double discout for usa citiens with green hair
