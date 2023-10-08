package testcases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObject.CorbeillePage;
import pageObject.LoginPage;
import utils.Utilitaire;

public class TestSupprimeCorbeille {
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
	      loginPage.login("giangtest", "12345");
	      Thread.sleep(1000);	      
	}
	
	@Test(dataProvider="testDataCorbeille")
	public void deleteCorbeille(String nom) throws InterruptedException {
		CorbeillePage corbeille = new CorbeillePage(driver);
		 Thread.sleep(1000);
		 corbeille.afficheCorbeille();
		 corbeille.deleteCorbeille(nom);
		 Thread.sleep(4000);
	}
	
	@DataProvider(name="testDataCorbeille")    
	public Object[]supplyData() throws IOException{        
		Object[] data = Utilitaire.getColumnData("corbeille", "nom");        
		return data;    
	}
	
	@AfterClass
	 public void closedBrowser () throws InterruptedException {	  
	      
		  driver.quit();	  
	  }
	

}
