@placeOrder @webui @cucumberTest
Feature:  user  purchase items from  automationpractive  eComence website

  Background:
    Given I am on online shopping website

  @submitOrder
  Scenario: User add item to shopping cart
    And I view "first" item in Quick view
    And change the size of the item to "M"
    And add that item to basket
    And select continue shopping option
    And I view "second" item in Quick view
    And add that item to basket
    And select proceeds to checkout
    When I validate my basket items are correct
      | name                        | price | quantity | colour | size |
      | Faded Short Sleeve T-shirts | 16.51 | 1        | Orange | M    |
      | Blouse                      | 27.00 | 1        | White  | S    |
    #ToDo username and password should be pickup from testdata file based on the env ( not good practice to expose uer details
    And login using "test.dummy@gmail.com" and password "testbjs"
    And I make payment using "wire" as payment option

   @checkOrder
  Scenario: Add comment to existing order
    And user login with username "test.dummy@gmail.com" and password "testbjs"
    And view existing orders
    And select latest order from orders history
    When user add message
    Then message should be saved

# This test is expected to fail to demonstrate screen shot capability of framework capturing screen shot when test fails
  @failingTest @captureScreenShot
  Scenario:  validate colour of item in the existing order
    And user login with username "test.dummy@gmail.com" and password "testbjs"
    And view existing orders
    When select latest order from orders history
    Then colour of my order should be "Yellow"

#TODo
  # we got 2 different login steps they both can be merged using feature flag

#  @submitOrder @wip
#  Scenario: User add item to shopping cart
#    And I view and add following items into basket
#      | name                        | quantity | colour | size |
#      | Faded Short Sleeve T-shirts | 2        | blue   | M    |
#      | Blouse                      | 3        | White  | L    |
#    And select proceeds to checkout
#    When I validate my basket items are correct
#      | name                        | price | quantity | colour | size |
#      | Faded Short Sleeve T-shirts | 16.51 | 1        | Orange | M    |
#      | Blouse                      | 27.00 | 1        | White  | S    |
#    And login using "test.dummy@gmail.com" and password "testbjs"
#    And I make payment using "wire" as payment option
