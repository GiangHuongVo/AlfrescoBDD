package steps;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ViderCorbeille {
	private WebDriver driver;
	ChromeOptions chromeOptions = new ChromeOptions();
	
	@Before 
    public void setUp() {
        // Initialize WebDriver and perform any setup needed before each scenario
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\hgian\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		chromeOptions.addArguments("--lang=fr");
		driver = new ChromeDriver(chromeOptions);	    
        driver.manage().window().maximize();
        driver.get("http://localhost:8088/share/page");
        
    }
	
	// Open browser is in the LoginLogout feature
	
	@When("Je suis dans la page Corbeille")
	public void corbeillePage() {
		WebElement pwdLocator = driver.findElement(By.name("password"));
		WebElement userNameLocator = driver.findElement(By.name("username"));
		WebElement btnConnexion = driver.findElement(By.xpath("//button[contains(text(), 'Connexion')]"));
		
		userNameLocator.sendKeys("giangtest");
		pwdLocator.sendKeys("12345");
		btnConnexion.click();
		
		// Ouvrir le dropdown du compte
	    WebElement nomCompte = driver.findElement(By.id("HEADER_USER_MENU_POPUP_text"));
	    nomCompte.click();
	    WebElement monProfile = driver.findElement(By.xpath("//a[@title='Mon profil']"));
	    // Choisir Mon profile
	    monProfile.click();
	    
	    WebElement corbeille = driver.findElement(By.xpath("//a[@href='user-trashcan']"));
	    // Ouvrir la corbeille
	    corbeille.click();	    
	    
	}

	@When("Je clique sur le bouton Vider")
	public void cliqueVider() {
		 WebElement btnVider = driver.findElement(By.xpath("//button[contains(text(), 'Vider')]"));
		 btnVider.click();
	}

	@When("Je clique sur le bouton OK pour confirmer")
	public void cliqueOK() {
	    WebElement btnOK = driver.findElement(By.xpath("//button[contains(text(),'OK')]"));
	    btnOK.click();
	}

	@Then("Je vois la liste est vide")
	public void corbeilleContent() {
		WebElement content = driver.findElement(By.xpath("//tbody[@class='yui-dt-message']/descendant::div[@class='yui-dt-liner']"));
		
		System.out.println(content.getText());
		
		String resultatText = content.getText();
		String textExpect = "Aucun élément existant";
		Assert.assertEquals(resultatText, textExpect);
	}
	
	@After 
    public void tearDown() {
        // Close the WebDriver session
        driver.quit();
    }
    
}
