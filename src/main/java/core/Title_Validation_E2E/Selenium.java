package core.Title_Validation_E2E;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Selenium {

    public String[][] a2d() throws IOException {
    	
      String csvFile = "./src/main/resources/Test.csv";
      BufferedReader br = null;
      String line = null;
      String[] column = null;
      int lines = 0;
      int columns = 0;
      String SplitBy = ",";
      String text_case_id = null;
      String url = null;
      String title_expected = null;
    
      // COUNTING LINES AND COLUMNS
      br = new BufferedReader(new FileReader(csvFile));
        while ((line = br.readLine()) != null) {
            lines++;
            column = line.split(SplitBy);
            columns = column.length;
        }
        br.close();
        
        String s2d[][] = new String[lines][columns];
        br = new BufferedReader(new FileReader(csvFile));
        WebDriver driver = new HtmlUnitDriver();
        int i = 0;

        while ((line = br.readLine()) != null) {
        	
        String[] csv = line.split(SplitBy);
        
        text_case_id = csv[0];
        url = csv[1];
        title_expected = csv[2];
        
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        String title_actual = driver.getTitle();
        
	    s2d[i][0] = text_case_id;
        s2d[i][1] = title_expected;
        s2d[i][2] = title_actual;
        
        i++;
        }
         driver.quit();
         br.close();
         return s2d;

      }

      public static void main(String[] args) {

  		String csvFile = "./src/main/resources/Test.csv";
  		BufferedReader br = null;
  		String line = null;
  		String cvsSplitBy = ",";
  		String text_case_id = null;
  		String url = null;
  		String title_expected = null;

  		try {
  			br = new BufferedReader(new FileReader(csvFile));
  		} catch (FileNotFoundException e) {
  			e.printStackTrace();
  		}
  		WebDriver driver = new HtmlUnitDriver();

  		try {
  			while ((line = br.readLine()) != null) {

  				String[] csv = line.split(cvsSplitBy);

  				text_case_id = csv[0];
  				url = csv[1];
  				title_expected = csv[2];

  				driver.get(url);
  				driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
  				String title_actual = driver.getTitle();
  				System.out.println("");
  				if (title_expected.equals(title_actual)) {
  					System.out.println("Test Case ID: \t\t" + text_case_id);
  					System.out.println("URL: \t\t\t" + url);
  					System.out.println("Title Expected: \t" + title_expected);
  					System.out.println("Title Actual: \t\t" + title_actual);
  					System.out.println("Test Case Result: \t" + "PASSED");
  				} else {
  					System.out.println("Test Case ID: \t\t" + text_case_id);
  					System.out.println("URL: \t\t\t" + url);
  					System.out.println("Title Expected: \t" + title_expected);
  					System.out.println("Title Actual: \t\t" + title_actual);
  					System.out.println("Test Case Result: \t" + "FAILED");
  				}

  			}
  			
  			br.close();
  			
  		} catch (IOException e) {
  			e.printStackTrace();
  		} finally {
  			driver.quit();
  		}
  	}
    }

