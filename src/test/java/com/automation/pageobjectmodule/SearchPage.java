package com.automation.pageobjectmodule;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.datatable.DataTable;

//import io.cucumber.messages.Messages.GherkinDocument.Feature.Step.DataTable;

//import io.cucumber.datatable.DataTable;

public class SearchPage {
	
    private static final Logger logger = LogManager.getLogger(SearchPage.class);

	WebDriver driver;
	
	By search_text_box = By.name("keyword");
	By search_button = By.id("js-btnSubmitSearch");
	By min_price_textbox = By.id("filter-price-from");
	By max_price_textbox = By.id("filter-price-to");
	By price_filtered_button = By.xpath("//a[@class='cateMain_filterSubmit js-filterSubmit']");
	By best_match = By.linkText("Best Match");
	By product_prices = By.xpath("//p[@class='gbGoodsItem_price js-currency js-asyncPrice']");
	By click_on_product = By.xpath("//p[@class='gbGoodsItem_titleInfor']");
	//By add_to_cart_button = By.linkText("Add to Cart");
	By add_to_cart_button =By.xpath("//a[contains(text(), 'Add to Cart')]");
	By cart_basket = By.xpath("//span[@class='headEntries_icon headEntries_iconCart']");
	By product_in_cart = By.xpath("//a[@class='headCart_itemLink']");
	By refinement_categories = By.xpath("//div[@class='cateMain_selectWrap']");
	
	public SearchPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void EnteredproductInsearchbox(String product) {
		WebElement searchtextbox = driver.findElement(search_text_box);
		searchtextbox.clear();
		searchtextbox.sendKeys(product);
	}
	
	public void Clickonsearchbutton() {
		driver.findElement(search_button).click();
	}
	
	public void Enteredminandmaxprice(String min, String max) {
		WebElement minpricetextbox = driver.findElement(min_price_textbox);
		minpricetextbox.sendKeys(min);
	
		WebElement maxpricetextbox = driver.findElement(max_price_textbox);
		maxpricetextbox.sendKeys(max);
		
		driver.findElement(price_filtered_button).click();
	}
	
	public void Verifysearchedproductsareinrange(float min, float max) {
		List<WebElement> product_prices = driver.findElements(By.xpath("//p[@class='gbGoodsItem_price js-currency js-asyncPrice']"));
		logger.info("get all ptoduct pricres");
		boolean bresult= false;
		float price_temp=0;
		
		for(int i=0; i<=product_prices.size();i++) {
			price_temp = Float.parseFloat(product_prices.get(i).getText());
			if(price_temp>=min && price_temp<=max) {
                logger.info("For index: " + i + " Product Price: " + price_temp + " and for Product: " + product_prices.get(i).getText());
            }else{
                bresult = false;
                logger.error("Product list is not with in Price range. Failed.");
                break;
            }
        }

        if (bresult){
            Assert.assertTrue("Search Result is with in the defined range i.e. Min: " + min + " Max: " + max,true);
            logger.info("All product is filtered with right price range. Min: " + min + " Max: " + max);
        }else{
            logger.error("All product is not filtered with right price range. Min: " + min + " Max: " + max);
            Assert.fail("Search Result is not with in the defined range i.e. Min: " + min + " Max: " + max );
        }


			}
	
	public void UserClickOnProductLink() {
		driver.findElement(best_match).click();
		driver.findElement(click_on_product).click();
	}
	
	public void UserClickedOnAddToCart() {
		//driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		WebElement cartbutton = driver.findElement(add_to_cart_button);
		WebDriverWait wait = new WebDriverWait(driver,30);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(cartbutton));
		cartbutton.click();
		
		//cartbutton.click();	
			}
	
	public void UserIsabletoSeeProductInCart() {
		WebElement cartdproduct = driver.findElement(cart_basket);
		Actions act = new Actions(driver);
		act.moveToElement(cartdproduct).perform();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		String producttext = driver.findElement(product_in_cart).getText();
		//driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		//System.out.println(producttext);		
	}
	
    public void ValidateProductSearchIsSuccessful() {
    	if(driver.findElement(refinement_categories).isDisplayed()) {
    		Assert.assertTrue(true);
    		logger.info("Search Page is displayed because refinement category is displayed");
    	}else {
    		logger.fatal("Search Page is not displayed");
    		Assert.fail("Search Page is not displayed");
    	}
    }
    
   /* public String ClickOntheProductLink(int productIndex) {
    	
    	List<WebElement> listOfProducts = driver.findElements(product_link_list);
    	logger.info("Numbers of product Searched" +listOfProducts.size());
    	
    	//Click on the one link
    	listOfProducts.get(productIndex).click();
    	logger.info("Clicked on the Link in the List with index: " + productIndex + "Link Text: "+listOfProducts.get(productIndex).getText());
		return listOfProducts.get(productIndex).getText();
    	
    }*/
    
    //Code to implement multiple products(datatable)
    public void enterData(DataTable table) {
    	//List<list> product = table.row();
    	List<List<String>> productList =  table.asList(String.class);
    	
    	for(List<String> e : productList) {
    		System.out.println(e);
    	}
    }
    	
   /* public void validateProductSearchsuccessful() {
    	Boolean b = driver.findElement(refinement_categories).isDisplayed();
    	Assert.assertEquals("product search successful", true, b);
    }*/
    
    public void validateProductSearchsuccessful() {
    	Boolean b = driver.findElement(refinement_categories).isDisplayed();
    	Assert.assertEquals("product search successful", true, b);
    }

    	
    	
    
    

	
			
		
		
		
	
}
	
	
	
	
	
	
	



