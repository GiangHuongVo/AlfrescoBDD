package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OutilAdminPage {
	WebDriver driver;
	
	String admin_Page_Element_P2 = "')]";
	String admin_Page_Element_P1="//a[contains(text(),'";
	
	public OutilAdminPage(WebDriver driver) {
		this.driver = driver;		
	}
	
	public void afficheUnePageOutils(String outil) {
		By option = By.xpath(admin_Page_Element_P1+outil+admin_Page_Element_P2);
		driver.findElement(option).click();		
	}	

}
