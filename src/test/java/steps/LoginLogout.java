package steps;

import java.time.Duration;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginLogout {
	private WebDriver driver;
	ChromeOptions chromeOptions = new ChromeOptions();
	
	@Before // This method runs before each scenario
    public void setUp() {
        // Initialize WebDriver and perform any setup needed before each scenario
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\hgian\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		chromeOptions.addArguments("--lang=fr");
		driver = new ChromeDriver(chromeOptions);	    
        driver.manage().window().maximize();
    }
    
	@Given("Open browser {string}")
	public void openBrowser(String url) throws InterruptedException {		
        driver.get(url);
        Thread.sleep(2000);
	}
	
	// @tclogin Scenario Outline: User logs in
	@When("Je saisis {string} dans le champ Username")
	public void inputUsername(String usernameValue) {
		WebElement userNameLocator = driver.findElement(By.name("username"));
		userNameLocator.sendKeys(usernameValue);
	}

	@When("Je saisis {string} dans le champ Password")
	public void inputPassword(String pass) {
		WebElement pwdLocator = driver.findElement(By.name("password"));
		pwdLocator.sendKeys(pass);
	}

	@When("Je clique sur le bouton Connexion")
	public void submitConnexion() {	
		WebElement btnConnexion = driver.findElement(By.xpath("//button[contains(text(), 'Connexion')]"));
	    btnConnexion.click();
	}

	@Then("Il affiche Tableau de bord de")
	public void pageTitle() { 
		WebElement titleLocator=driver.findElement(By.xpath("//h1[@id='HEADER_TITLE']/span"));
	    String titleRecu = titleLocator.getText();
	    String titleAttendu = "Tableau de bord de";	    
	    Assert.assertTrue(titleRecu.toLowerCase().contains(titleAttendu.toLowerCase()));
	}
	
	// @tclogout Scenario: User logs out
	
	@Given("the user is logged in {string} et {string}")
	public void userLogin(String usernameValue, String pass) {
		WebElement pwdLocator = driver.findElement(By.name("password"));
		WebElement userNameLocator = driver.findElement(By.name("username"));
		WebElement btnConnexion = driver.findElement(By.xpath("//button[contains(text(), 'Connexion')]"));
		userNameLocator.sendKeys(usernameValue);
		pwdLocator.sendKeys(pass);
		btnConnexion.click();		
	}

	@When("Je clique sur le link de header Deconnexion")
	public void cliqueDeconnexion() {
		 WebElement deconnexion = driver.findElement(By.id("HEADER_USER_MENU_LOGOUT"));
		 WebElement userMenu = driver.findElement(By.id("HEADER_USER_MENU_POPUP"));
		 userMenu.click();
		 deconnexion.click();
	}

	@Then("Je vois le bouton Connexion")
	public void btnConnexion() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		By btnConnexion =By.xpath("//button[contains(text(), 'Connexion')]");
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(btnConnexion));       
	}
	
	@After // This method runs after each scenario
    public void tearDown() {
        // Close the WebDriver session
        driver.quit();
    }
}
