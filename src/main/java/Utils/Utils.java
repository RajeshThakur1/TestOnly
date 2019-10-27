package Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Utils {

	public String GetXpath(String TestName, String VariableName, String[][] xpathData) {
		for (int row = 1; row < xpathData.length; row++) {
			if (TestName.equals(xpathData[row][0]) && VariableName.equals(xpathData[row][1]))
				return xpathData[row][2];
		}
		return "";
	}

	public void ChromeDriverSample() {
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		String baseUrl = "http://demo.guru99.com/test/newtours/";
		String expectedTitle = "Welcome: Mercury Tours";
		String actualTitle = "";

		// launch Fire fox and direct it to the Base URL
		driver.get(baseUrl);

		// get the actual value of the title
		actualTitle = driver.getTitle();

		/*
		 * compare the actual title of the page with the expected one and print the
		 * result as "Passed" or "Failed"
		 */
		if (actualTitle.contentEquals(expectedTitle)) {
			System.out.println("Test Passed!");
		} else {
			System.out.println("Test Failed");
		}

		// close Fire fox
		driver.close();
	}

	public String[][] ReadExcel(String path, String sheetName) throws FileNotFoundException, IOException {

		XSSFWorkbook myExcelBook = new XSSFWorkbook(new FileInputStream(path));
		XSSFSheet myExcelSheet = myExcelBook.getSheet(sheetName);

		// System.out.println(myExcelSheet.getPhysicalNumberOfRows());

		int noOfRows = myExcelSheet.getPhysicalNumberOfRows();

		// gives column count in sheet
		XSSFRow xlRow = myExcelSheet.getRow(0);
		int noOfColumns = xlRow.getLastCellNum();

		String[][] excelData = new String[noOfRows][noOfColumns];

		// r - row c- column
		for (int r = 1; r < noOfRows; r++) {
			for (int c = 0; c < noOfColumns; c++) {
				xlRow = myExcelSheet.getRow(r);
				XSSFCell xlCell = xlRow.getCell(c);
				excelData[r][c] = xlCell.toString();
			}
		}
		myExcelBook.close();
		return excelData;
	}

	public void WriteExcel(TreeMap<String, Object[]> data, String Path, String SheetName) {

		// Blank workbook
		XSSFWorkbook workbook = new XSSFWorkbook();

		// Create a blank sheet
		XSSFSheet sheet = workbook.createSheet(SheetName);

		// Sample Code
		// data = InputData;//new TreeMap<String, Object[]>();
		// data.put("1", new Object[] { "ID", "NAME", "LASTNAME" });
		// data.put("2", new Object[] { 1, "Amit", "Shukla" });
		// data.put("3", new Object[] { 2, "Lokesh", "Gupta" });
		// data.put("4", new Object[] { 3, "John", "Adwards" });
		// data.put("5", new Object[] { 4, "Brian", "Schultz" });

		// Iterate over data and write to sheet
		Set<String> keyset = data.keySet();
		int rownum = 0;
		for (String key : keyset) {
			Row row = sheet.createRow(rownum++);
			Object[] objArr = data.get(key);
			int cellnum = 0;
			for (Object obj : objArr) {
				Cell cell = row.createCell(cellnum++);
				if (obj instanceof String)
					cell.setCellValue((String) obj);
				else if (obj instanceof Integer)
					cell.setCellValue((Integer) obj);
			}
		}
		try {
			// Write the workbook in file system
			FileOutputStream out = new FileOutputStream(new File(Path));
			workbook.write(out);
			out.close();
			System.out.println(SheetName + "written successfully on disk.");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String ReadConfig(String key) throws IOException {
		String result = "";
		InputStream inputStream = null;
		Properties prop = new Properties();
		String propFileName = "config.properties";
		try {
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
			String propValue = prop.getProperty(key);
			result = propValue;
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
		}

		return result;
	}
}
