package crossover;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Crossover {
	public static void main(String[] args) throws InterruptedException, IOException{		
		
		WebDriver driver = new FirefoxDriver();
		driver.get("http://www.worldbank.org/");
		WebElement link;
		link = driver.findElement(By.linkText("DATA"));				
		link.click();
		link = driver.findElement(By.linkText("Country"));				
		link.click();
		Thread.sleep(2000);
		link = driver.findElement(By.linkText("High income"));				
		link.click();
		Thread.sleep(2000);
		
		try (BufferedReader br = new BufferedReader(new FileReader("src/crossover/countries.txt")))
		{
		   String line;
		   int i =1;

		   while ((line = br.readLine()) != null) {
			   
		     link = driver.findElement(By.xpath("//li[contains(@class,'label')][" +i+ "]")); // find first country according countries file.			
		     link.click();		     
		     
		     link = driver.findElement(By.xpath("//div[contains(@class,'chart-item')][1]")); //First graph
			 String test1 =link.getText();			 
			 boolean test1bool = test1.toLowerCase().contains(line.toLowerCase());			
				
			 link = driver.findElement(By.xpath("//div[contains(@class,'chart-item')][2]")); //Second graph
			 String test2 =link.getText();			 
			 boolean test2bool = test2.toLowerCase().contains(line.toLowerCase());			 
				
			 link = driver.findElement(By.xpath("//div[contains(@class,'chart-item')][4]")); // Fourth graph
			 String test4 =link.getText();			 
			 boolean test4bool = test4.toLowerCase().contains(line.toLowerCase());			 
			 
			 verify(line,test1bool,test2bool,test4bool);			 		 
					 
			 driver.navigate().back(); // Navigate back to the High income page.
		     i++;
		}
		} catch (IOException e) {
		    e.printStackTrace();
		}
		driver.quit();		
	}	

	public static String verify(String line, boolean test1bool, boolean test2bool, boolean test4bool) {
		
		if (test1bool == false){			
			return line + " is not set in GDP at market prices graph.";
		} else if (test2bool == false){
			return line + " is not set in Population, total graph.";
		} else if (test4bool == false){
			return line + " is not set in CO2 emissions graph.";
		} else if ((test1bool == true) && (test2bool == true) && (test4bool == true)){			
		    return line + " is present in three graphs.";
		} 				
		return line;
	}
}