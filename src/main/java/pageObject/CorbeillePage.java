package pageObject;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CorbeillePage {
	WebDriver driver;
	By nomCompte = By.id("HEADER_USER_MENU_POPUP_text");
	By monProfile = By.xpath("//a[@title='Mon profil']");
	By corbeillePage = By.xpath("//a[@href='user-trashcan']");
	String divClass = "//div[@class='name']";
	By nomList = By.xpath(divClass);
	By nomListByTag = By.xpath(divClass+"/a");
	
	String nomCorbeilleP1="//a[contains(text(), '";
	String nomCorbeilleP2="')]";
	
	String btnSuprimer = "/ancestor::tr/td[4]/descendant::button[contains(text(),'Supprimer')]";
	
	By btnOK = By.xpath("//button[contains(text(), 'OK')]");
	
	public CorbeillePage (WebDriver driver) {
		this.driver = driver;
	}
	
	public void afficheCorbeille () {
		driver.findElement(nomCompte).click();
		driver.findElement(monProfile).click();
		driver.findElement(corbeillePage).click();		
	}
	
	public void deleteCorbeille(String nom) {
		// From div class = name
		boolean isNameInList = listName().contains(nom);
		boolean isNom = listNameFromTagA().contains(nom);
		String supprimerBtn = nomCorbeilleP1 + nom + nomCorbeilleP2 + btnSuprimer;	
		
		if( isNameInList) {			
					
			driver.findElement(By.xpath(supprimerBtn)).click();	
			driver.findElement(btnOK).click();
			System.out.println("Succes");
			isNameInList = false;			
		}
		if (isNom)
		{					
			driver.findElement(By.xpath(supprimerBtn)).click();	
			driver.findElement(btnOK).click();
			System.out.println("Succes");
			isNom = false;
		}
		else 
		{
			System.out.println("Corbeille is not found");
		}
		
	}
	
	// Get a list of name corbeille from div class=name
	public List<String> listName (){
		// List name from div class=name
		List<WebElement> list = driver.findElements(nomList);
			
		List<String> namesList = new ArrayList<>();
	       for (WebElement listItem : list) {
	           String name = listItem.getText();
	           namesList.add(name);
	       }	    
	       
		return namesList;
	}
	
	public List<String> listNameFromTagA(){
		
		// List name from div class=name with tag a
		List<WebElement> list = driver.findElements(nomListByTag);
					
		List<String> namesList = new ArrayList<>();
		for (WebElement listItem : list) {
			 String name = listItem.getText();
			 namesList.add(name);
		}	    
			       
	return namesList;
	}
	
}
