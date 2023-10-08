package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
	WebDriver driver;
	
	By usrLocator = By.name("username");
	By pwdLocator = By.name("password");
	By btnLogin = By.xpath("//button[contains(text(), 'Connexion')]");
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void login(String usr, String pwd) {
		driver.findElement(usrLocator).sendKeys(usr);
		driver.findElement(pwdLocator).sendKeys(pwd);
		driver.findElement(btnLogin).click();
	}

}
