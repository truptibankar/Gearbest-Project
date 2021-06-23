package com.automation.stepdefs;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.automation.pageobjectmodule.LoginPage;
import com.automation.pageobjectmodule.SearchPage;
//import com.visionit.automation.core.WebDriverFactory;

import WebDriverFactory.CrossWebDriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class GearbestStepdefs {
	
	
	private static final Logger logger = LogManager.getLogger(GearbestStepdefs.class);

	WebDriver driver;
	String base_url = "https://www.gearbest.com/";
	Scenario scn;
	LoginPage loginPage;
	SearchPage searchPage;
	
	
	
    @Before
    public void setUp(Scenario scn) throws Exception {
        this.scn = scn; 

        
        String browserName = CrossWebDriverFactory.getBrowserName();
        driver = CrossWebDriverFactory.getWebDriverForBrowser(browserName);
        logger.info("Browser invoked.");
        loginPage = new LoginPage(driver);
        searchPage = new SearchPage(driver);
        
      
    }
    
    @After(order=1)
    public void cleanUp(){
    	CrossWebDriverFactory.quitDriver();
        scn.log("Browser Closed");
    }

    @After(order=2)
    public void takeScreenShot(Scenario s) {
        if (s.isFailed()) {
            TakesScreenshot scrnShot = (TakesScreenshot)driver;
            byte[] data = scrnShot.getScreenshotAs(OutputType.BYTES);
            scn.attach(data, "image/png","Failed Step Name: " + s.getName());
        }else{
            scn.log("Test case is passed, no screen shot captured");
        }
    }

	
    @Deprecated
	@Given("User opened browser")
	public void user_opened_browser() {

		
	}


	@Given("User navigates to the url")
	public void user_navigates_to_the_url() {
		CrossWebDriverFactory.navigateToTheUrl(base_url);
        scn.log("Browser navigated to URL: " + base_url);
        loginPage.closepopupofhomepage();
	}
	
	@When("User clicked on register button")
	public void user_clicked_on_register_button() {
		//loginPage.closepopupofhomepage();
		loginPage.mouseovertosignin();
		loginPage.clickedonregisterbutton();
		loginPage.clickonregisteredtab();
	}
	@When("User entered email id {string}")
	public void user_entered_email_id(String email) {
		loginPage.enteredemailId(email);
	}
	@When("User entered password {string}")
	public void user_entered_password(String pwd) {
		loginPage.enterdpwd(pwd);
	}
	@When("User entered confirmed password {string}")
	public void user_entered_confirmed_password(String repwd) {
		loginPage.enterdretypepwd(repwd);
	}
	@Then("Usee new account created")
	public void usee_new_account_created() {
		loginPage.clickonsubmit();
	}
	
	@Given("User navigated to the home application url")
	public void user_navigated_to_the_home_application_url() {
		CrossWebDriverFactory.navigateToTheUrl(base_url);
        scn.log("Browser navigated to URL: " + base_url);
        loginPage.closepopupofhomepage();    
		
	}
	
	@Given("User search for product {string}")
	public void user_search_for_product(String ProductName) {
		
		searchPage.EnteredproductInsearchbox(ProductName);
		searchPage.Clickonsearchbutton();
		
		
		
	}
	@When("User enters min price as {string} and max price as {string}")
	public void user_enters_min_price_as_and_max_price_as(String min, String max) {
		searchPage.Enteredminandmaxprice(min, max);
	}
	@Then("Verify the search results gets filtered price range between {float} and {float}")
	public void verify_the_search_results_gets_filtered_price_range_between_$_and_$(Integer min, Integer max) {
		searchPage.Verifysearchedproductsareinrange(min, max);
	}

	@When("User is on the product details page")
	public void user_is_on_the_product_details_page() {
		searchPage.UserClickOnProductLink();	
	}

	@When("User selects the product quantity as {int}")
	public void user_selects_the_product_quantity_as(Integer int1) {
	}
	@When("User clicks add to cart on the page on product details page")
	public void user_clicks_add_to_cart_on_the_page_on_product_details_page() {
		searchPage.UserClickedOnAddToCart();
	}
	@Then("Product is added to the cart")
	public void product_is_added_to_the_cart() {
		searchPage.UserIsabletoSeeProductInCart();
	}
	
	@When("User enter below products name in serch box")
	//public void user_enter_below_products_name_in_serch_box_and_click_on_add_to_cart(io.cucumber.datatable.DataTable dataTable) {
		
		/*loginPage.closepopupofhomepage();
		searchPage.EnteredproductInsearchbox();
		searchPage.enterData(dataTable);
		searchPage.Clickonsearchbutton();*/
		
		public void user_search_below_product(List<Map<String,String>> data) {
		for(int i=0; i<=data.size()-1;i++) {
			seacrhandaddproducts(data,i);
			scn.log("First product searched: "+data.get(i).toString());
		}
		
	}
	
	public void seacrhandaddproducts(List<Map<String,String>> data, int index) {
		
		String productName = data.get(index).get("Item");
		searchPage.EnteredproductInsearchbox(productName);
		searchPage.Clickonsearchbutton();
		

		
	}

	
	/* @When("User add the products with defined price range and quantity listed below")
	    public void user_add_the_products_with_defined_price_range_and_quantity_listed_below(List<Map<String,String>> data) {
	        for (int i=0; i<=data.size()-1;i++){
	            searchAndAddProducts(data,i);
	            scn.log("First Product added and searched. " + data.get(i).toString());
	        }
	    }

	   // Common Method to Iterated
	    public void searchAndAddProducts(List<Map<String,String>> data, int index){
	        String product_name = data.get(index).get("ITEM");
	        int product_price_limit = Integer.parseInt(data.get(index).get("PRICE_LESS_THAN"));
	        String product_quantity = data.get(index).get("QUANTITY");

	        //Reusing Existing methods.
	        //You can use existing Step Defs methods.
	        //No issues there.
	        user_search_for_product(product_name);

	        //Get the List of Products.
	        //This XPATH will get all the product links.
	        //div[@class='sg-row']//a[@class='a-link-normal a-text-normal']
	        List<WebElement> list_product_links = driver.findElements(By.xpath("//div[@class='sg-row']//a[@class='a-link-normal a-text-normal']"));

	        //This will give all the prices corresponding to the above products.
	        //We are assuming, that indexes of above product links matches the price list indexes in below list.
	        //Most of the time this assumption is right. We need to inspect the element to check the assumption is right.
	        //In this case this assumption certainly is right.
	        List<WebElement> list_product_prices = driver.findElements(By.xpath("//div[@class='sg-row']//span[@class='a-price-whole']"));

	        int product_link_index = -1;// this value is kept negative, to check later
	        //Loop through the List
	        for (int i=0;i< list_product_prices.size();i++){
	            //Value is to be captured, then , (comma) is to be removed and then it is to be converted to a integer.
	            //Below all done in a single step and value stored in temp variable
	            int temp = Integer.parseInt(list_product_prices.get(i).getText().replace(",",""));
	            if (temp<product_price_limit){// if product is less then the price mentioned
	                product_link_index = i;
	                scn.log("Product found with in the price range. ");
	                break;
	            }
	        }

	        //If no product is found in the above loop.
	        if (product_link_index==-1){
	            scn.log("No product found with in the price range");
	            Assert.fail("No product found on page 1 which has price less then mentioned amount");
	        }

	        //if a product with required price is found then click on the link.
	        //Save the name of the Product
	        String product_text = list_product_links.get(product_link_index).getText();
	        scn.log("Product found with in the price range: " + product_text);
	        list_product_links.get(product_link_index).click();

	        //Product description page will be opened
	        product_description_is_displayed_in_new_tab();
	        scn.log("Product Description is displayed in new tab.");

	        //On Product Description Page Select Quantity as mentioned in the feature file
	        productDescriptionPageObjects.selectQuantity(product_quantity);
	        scn.log("Quantity Selected. " + product_quantity);

	        //Click on add to cart Button on product Description Page
	        productDescriptionPageObjects.clickOnAddToCartButton();
	        scn.log("Add to cart to button clicked.");

	        /*
	        Note: Checking Added to Cart Text is displayed is not enough to check the feature.
	                You need to add more validation steps like:
	                a. Checking that the right product is added by matching the text of the product
	                b. Check the right price is displayed.
	                c. If quantity is more than 1, then price is to be multiplied by the quantity and validated.

	                I am skipping this validation steps and leaving up to the student to implement it.
	                Some more complex logic has to be written to implement these validations.
	                I recommend students is to try writing this logic as well, at least the price logic should be there.
	         */
	        //Checking the Added to Cart Text is displayed
	       /* productDescriptionPageObjects.checkAddedToCartMessageIsDisplayed();
	        scn.log("Add to cart message is displayed");

	        //Close the open Product Descp page.
	        //Notice we are using driver.close and not driver.quit.
	        //Driver.close will only close product description tab
	        driver.close();
	        scn.log("Product description tab is closed.");

	        //Clean up for this method is to switch to the original window
	        //Because we need to search new product there again
	        //However you can continue to use the same window to search for new products
	        WebDriverFactory.switchToOriginalTab();
	        scn.log("Driver switched to original tab/window");
	    }*/
	    




	@Then("Search product displayed")
	public void user_cart_is_updtated_with_above_products() {
		searchPage.validateProductSearchsuccessful();
	}











}
