package testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

import pageObject.GestionModelePage;
import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.OutilAdminPage;

public class TestLoginLogout {
	WebDriver driver;
	ChromeOptions chromeOptions = new ChromeOptions();
	String url = "http://localhost:8088/share/page";
	
	@BeforeClass
	public void openBrowser() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\hgian\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		  chromeOptions.addArguments("--lang=fr");
		  driver = new ChromeDriver(chromeOptions);	    
	      driver.manage().window().maximize();
	      driver.get(url);
	      driver.manage().window().maximize();
		
	}
	
	@BeforeMethod
	 public void login() throws InterruptedException {
	      
	      LoginPage loginPage = new LoginPage(driver);		
	      loginPage.login("admin", "12345678");
	      Thread.sleep(4000);
	}	
	
	@Test
	public void ouvrirOutilAdmin() throws InterruptedException {
		 HomePage homepage = new HomePage(driver);	      
	      homepage.afficheOultilAdmin();
	      Thread.sleep(4000);
	      OutilAdminPage option = new OutilAdminPage(driver);
	      option.afficheUnePageOutils("Gestionnaire de modèles");
	      Thread.sleep(4000);
	      
	      GestionModelePage model = new GestionModelePage(driver);
	      //model.afficheUnFormulaireCreerUnModele();
	      //model.creerModele("GiangEclipe", "EclipesTest", "Test_Web_Driver");
	      model.supprimeUnModele("Test_Web_Driver");
	      Thread.sleep(4000);
		
	}
	
	@AfterMethod
	 public void logout() throws InterruptedException {      
	      
	      HomePage homepage = new HomePage(driver);
	     
	      homepage.logout();
	      Thread.sleep(4000);
	  }
	  
	  @AfterClass
	  public void closedBrowser () {
		  driver.quit();	  
	  }
}
