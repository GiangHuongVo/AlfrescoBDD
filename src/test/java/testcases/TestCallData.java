package testcases;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import utils.Utilitaire;

public class TestCallData {
	@Test(dataProvider="testData")
	public void lireData(String espaceNom, String prefixValue, String nom) {
		System.out.println(espaceNom);
		System.out.println(prefixValue);
		System.out.println(nom);
		
	}
	
	@DataProvider(name="testData")    
		public Object[][] supplyData() throws IOException{        
			Object[][] data = Utilitaire.getDataFromExcel("modele");        
		return data;    
	}	
	
	@Test(dataProvider="testDataCol")
	public void lireDataCol( String colName) {
			
		System.out.println(colName);
		
	}
	@DataProvider(name="testDataCol")
	public Object[] colData() throws IOException {
		Object[] data = Utilitaire.getColumnData("modele", "nom"); 
		return data;
	}
	
	

}
