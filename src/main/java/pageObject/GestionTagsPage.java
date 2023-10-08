package pageObject;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class GestionTagsPage {
	WebDriver driver;
	
	String nomTag1 = "//a[@title='";
	String nomTag2 = "']";
	
	String btnEdit = "/ancestor::tr/td[4]/descendant::a[@class='edit-tag']";
	
	By inputRenomer = By.xpath("//div[@class='yui-u']/input[@name='name']");
	By btnOK = By.xpath("//button[contains(text(), 'OK')]");
	By nomList = By.xpath("//td[@headers='yui-dt1-th-name ']/div/span/a/b");
	
	
	public GestionTagsPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void modifierTag (String nomTag, String reNomer) throws InterruptedException {
		String tagLocator = nomTag1 + nomTag + nomTag2+btnEdit;
		String tagNomLocator = nomTag1 + nomTag + nomTag2;
		
		boolean isNameInList = listName().contains(nomTag);
		
	    if (isNameInList) {
	        	WebElement elementNom= driver.findElement(By.xpath(tagNomLocator));
	    		WebElement elementEdit= driver.findElement(By.xpath(tagLocator));	    		
	    				
	    		Actions actions = new Actions(driver);
	
	            // Move the mouse cursor to the specified element
	            actions.moveToElement(elementNom).build().perform();
	
	    		Thread.sleep(4000);	
	    		actions.moveToElement(elementEdit).build().perform();
	    		// Click sur Edit icon
	    		elementEdit.click();
	    		Thread.sleep(4000);
	    		driver.findElement(inputRenomer).clear();
	    		
	    		driver.findElement(inputRenomer).sendKeys(reNomer);
	    		driver.findElement(btnOK).click();	        			
		}
		else {
			System.out.println(nomTag + " is not in the list.");
			
		}
	}
	// Get a list of name tags
	public List<String> listName (){
		List<WebElement> list = driver.findElements(nomList);
		
		List<String> namesList = new ArrayList<>();
        for (WebElement listItem : list) {
            String name = listItem.getText();
            namesList.add(name);
        }
		return namesList;
	}

}
