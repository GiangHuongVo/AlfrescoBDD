package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
	WebDriver driver;	
	By link_HeaderUserMenu = By.id("HEADER_USER_MENU_POPUP");	
	By btn_logout = By.id("HEADER_USER_MENU_LOGOUT");
	By link_HeaderOutilAdmin = By.xpath("//div[@id='HEADER_ADMIN_CONSOLE']/span/a");
	
	public HomePage(WebDriver driver) {
		this.driver = driver;		
	}
		
	public void logout() {	
	    driver.findElement(link_HeaderUserMenu).click();		
		driver.findElement(btn_logout).click();
	}
	
	public void afficheOultilAdmin () {
		driver.findElement(link_HeaderOutilAdmin).click();
	}

}
