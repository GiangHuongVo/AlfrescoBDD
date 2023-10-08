package testcases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;

import pageObject.GestionModelePage;
import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.OutilAdminPage;
import utils.Utilitaire;

public class TestCreerUnModele {
	WebDriver driver;
	ChromeOptions chromeOptions = new ChromeOptions();
	String url = "http://localhost:8088/share/page";
	
	@BeforeClass
	public void openBrowser() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\hgian\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		  chromeOptions.addArguments("--lang=fr");
		  driver = new ChromeDriver(chromeOptions);	    
	      driver.manage().window().maximize();
	      driver.get(url);
	      driver.manage().window().maximize();
	      
	      LoginPage loginPage = new LoginPage(driver);		
	      loginPage.login("admin", "12345678");
	      Thread.sleep(1000);
	      HomePage homepage = new HomePage(driver);	      
		  homepage.afficheOultilAdmin();
		  Thread.sleep(1000);
		    
		  OutilAdminPage option = new OutilAdminPage(driver);
		  option.afficheUnePageOutils("Gestionnaire de modèles");
		  Thread.sleep(1000);
	}
	
	@Test(dataProvider="testData")
	public void testCreerUnModele(String espaceNom, String prefixValue, String nom) throws InterruptedException {
			    
		GestionModelePage modele = new GestionModelePage(driver);
		modele.afficheUnFormulaireCreerUnModele();
		modele.creerModele(espaceNom, prefixValue, nom);
		Thread.sleep(1000);		
		Assert.assertEquals(true, modele.isNom(nom));		
	}
	
	@DataProvider(name="testData")    
	public Object[][] supplyData() throws IOException{        
		Object[][] data = Utilitaire.getDataFromExcel("modele");        
		return data;    
	}
	
	@AfterClass
	public void closedBrowser () throws InterruptedException {
		HomePage homepage = new HomePage(driver);		     
	    homepage.logout();
	    Thread.sleep(2000);
		driver.quit();	  
	}
}
