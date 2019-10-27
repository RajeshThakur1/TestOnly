package Main;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeWebDriver {
	
	
	    // static variable single_instance of type Singleton 
	    private static ChromeWebDriver single_instance = null; 
	  
	    // variable of type String 
	    public WebDriver s; 
	  
	    // private constructor restricted to this class itself 
	    private ChromeWebDriver() 
	    { 
	        s = new ChromeDriver();
	    } 
	  
	    // static method to create instance of Singleton class 
	    public static ChromeWebDriver getInstance() 
	    { 
	        if (single_instance == null) 
	            single_instance = new ChromeWebDriver(); 
	  
	        return single_instance; 
	    } 
}
