package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GestionModelePage {
	WebDriver driver;
	// Locator la page de gestion modeles
	By btnCreerUnModele=By.xpath("//div[@class='alfresco-layout-LeftAndRight__left']//span[contains(text(),'Créer un modèle')]");
	String linkActions1= "//span[text()='";
	String linkActions2="']/ancestor::tr/td//span[text()='Actions']";
	By localisation_listeModeles=By.xpath("//tr[@class='alfresco-lists-views-layouts-Row alfresco-lists-views-layout-_MultiItemRendererMixin__item']");
	By bouton_supprimer= By.xpath("//div[contains(@style,'visible')]//descendant::*[text()='Supprimer']");
	By bouton_confirmerSuppression= By.xpath("//div[@class='footer']/descendant::*[text()='Supprimer']");

	//Locators de la formulaire creer un modele
	By espaceName=By.name("namespace");
	By prefix = By.xpath("//input[@name='prefix']");
	By modeleName = By.xpath("//input[@name='name']");
	By btnCreerModeleFormulaire=By.xpath("//*[@id='CMM_CREATE_MODEL_DIALOG_OK_label']");
	
	public GestionModelePage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void afficheUnFormulaireCreerUnModele() {
		driver.findElement(btnCreerUnModele).click();
	}
	
	public void creerModele(String espaceNom, String prefixValue, String nom) {
		driver.findElement(espaceName).sendKeys(espaceNom);
		driver.findElement(prefix).sendKeys(prefixValue);
		driver.findElement(modeleName).sendKeys(nom);
		driver.findElement(btnCreerModeleFormulaire).click();		
	}
	
	public void supprimeUnModele(String nomModele) {		
		String modeleLocator = linkActions1 + nomModele + linkActions2;
		driver.findElement(By.xpath(modeleLocator)).click();
		driver.findElement(bouton_supprimer).click();
		driver.findElement(bouton_confirmerSuppression).click();
		
	}
	
	public boolean isNom (String nomModele) {
		String modeleLocator = linkActions1 + nomModele + linkActions2;
		WebElement result = driver.findElement(By.xpath(modeleLocator));
		if(result == null) {
			return false;
		}
		return true;
	}

}
