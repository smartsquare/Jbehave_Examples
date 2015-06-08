Title: Customer Shopping Cart

Narrative:
As customer
I want to add items to my shopping cart
So that I can place an new order

Scenario: Customer should add an item to his shopping cart

Given a shopping cart
When a customer adds an item to his shopping cart
Then the shopping cart contains 1 item

Scenario: A customer should add different items to his shopping cart

Given a shopping cart
When the customer add 1 pen to his shopping cart with a price of 2 dollar (each)
And the customer add 3 books to his shopping cart with a price of 5 dollar (each)
Then the shopping cart contains 2 different items
And the shopping cart total is 17 dollar