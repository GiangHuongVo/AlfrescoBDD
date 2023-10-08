package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.google.common.collect.Table.Cell;

public class Utilitaire {
	
	public static Object[][] getDataFromExcel(String sheetname) throws IOException {

		//Specifier a java ou se trouve notre fichier excel(objet java maintenant)afin de pouvoir le manipuler

		// On utilisant la classe 'System' pour ne pas dependre de l'emplacement local de notre projet

		File excelFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\testData\\datapool.xlsx");
				
		// Creer un objet a partir de la classe (XSSFworkbook) qui va nous permettre d'ecrire , de lire a partir d'Excel

		// Pour lire nos donnees (notre fichier)on utilise la classe:

		FileInputStream fis = new FileInputStream(excelFile);

		//Manipuler nos donnees excell dans 'fis' avec l'objet workbook

		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		
		XSSFSheet sheet = workbook.getSheet(sheetname);
		int rows = sheet.getLastRowNum();
		int cols = sheet.getRow(0).getLastCellNum();
		
		Object[][] data = new Object[rows][cols];
		
		for(int i=0; i <rows; i++) {
			XSSFRow row = sheet.getRow(i+1);
			for(int j=0; j<cols; j++) {
				XSSFCell cell = row.getCell(j);
				CellType cellType = cell.getCellType();
				switch(cellType) {
				case STRING:
					data[i][j]=cell.getStringCellValue();
					break;
				case NUMERIC:
					data[i][j]=cell.getNumericCellValue();
					break;
				case BOOLEAN:
					data[i][j]=cell.getBooleanCellValue();
					break;				
				}
			}
		}

		return data;	 

	}
	
	public static String[] getColumnData(String sheetname, String colName) throws IOException {
		File excelFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\testData\\datapool.xlsx");
		
		// Creer un objet a partir de la classe (XSSFworkbook) qui va nous permettre d'ecrire , de lire a partir d'Excel

		// Pour lire nos donnees (notre fichier)on utilise la classe:

		FileInputStream fis = new FileInputStream(excelFile);

		//Manipuler nos donnees excell dans 'fis' avec l'objet workbook

		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		
		XSSFSheet sheet = workbook.getSheet(sheetname);		
        int columnIndex = -1;
        XSSFRow firstRow = sheet.getRow(0);
        
        
        for (int i = 0; i < firstRow.getLastCellNum(); i++) {
            XSSFCell cell = firstRow.getCell(i);
            if (cell != null && cell.getStringCellValue().equals(colName)) {
                columnIndex = i;
                break;
            }
        }

        if (columnIndex != -1) {
            List<String> columnValues = new ArrayList<String>();
            // Iterate through the rows and extract values from the specified column
            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
            	XSSFRow row = sheet.getRow(rowIndex);
                if (row != null) {
                	XSSFCell cell = row.getCell(columnIndex);
                    if (cell != null) {
                        // Extract and add the cell value to the list
                        columnValues.add(cell.toString());
                    }
                }
            }
            return columnValues.toArray(new String[0]);
        } else {
            return null; // Column not found
        }
    }
}
