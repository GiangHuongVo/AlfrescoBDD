package testcases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObject.GestionModelePage;
import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.OutilAdminPage;
import utils.Utilitaire;

public class TestSupprimerModele {
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
	
	@Test(dataProvider="testDataCol")
	public void deleteModele(String nom) throws InterruptedException {		
		GestionModelePage modele = new GestionModelePage(driver);
		try {
			modele.supprimeUnModele(nom);
			Thread.sleep(1000);
			Assert.assertEquals(false, modele.isNom(nom));
		}
		catch(Exception e) {
			System.out.println("Not found nom" + nom);
		}	
		
	}
	
	@DataProvider(name="testDataCol")
	public Object[] colData() throws IOException {
		Object[] data = Utilitaire.getColumnData("modele", "nom"); 
		return data;
	}
	
	 @AfterClass
	 public void closedBrowser () throws InterruptedException {
		  HomePage homepage = new HomePage(driver);		     
	      homepage.logout();
	      Thread.sleep(4000);
		  driver.quit();	  
	  }

}
