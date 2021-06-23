@Search
Feature: Search functionality

  Scenario: Search for a product
    Given User navigated to the home application url
    And User search for product "Laptop"
    When User enters min price as "20.00" and max price as "30.00"
    Then Verify the search results gets filtered price range between 20.00 and 30.00

  @productdeatils
  Scenario: User is able to select the quantity and add to cart from Product details page
    Given User navigated to the home application url
    And User search for product "Laptop"
    When User is on the product details page
    And User selects the product quantity as 1
    And User clicks add to cart on the page on product details page
    Then Product is added to the cart

  @Datatable
  Scenario: User is able to search multiple products
    Given User navigated to the home application url
    When User enter below products name in serch box
      | Item     |
      | Laptop   |
      | Mobile   |
      | Earphone |
    Then Search product displayed
