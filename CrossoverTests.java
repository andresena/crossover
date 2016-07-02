package crossover;

import org.testng.Assert;
import junit.framework.TestCase;

public class CrossoverTests extends TestCase {	
	
	public void test1() {
	  Crossover crossover = new Crossover();	  
	  String result = crossover.verify("ANDORRA", true, true, true);	  
	  Assert.assertEquals("ANDORRA is present in three graphs.", result);
	}
	
	public void test2() {
      Crossover crossover = new Crossover();		  
	  String result = crossover.verify("ARGENTINA", false, true, true);	  
	  Assert.assertEquals("ARGENTINA is not set in GDP at market prices graph.", result);
	}
	
	public void test3() {
	  Crossover crossover = new Crossover();			  
	  String result = crossover.verify("ALBANIA", true, false, true);	  
	  Assert.assertEquals("ALBANIA is not set in Population, total graph.", result);
	}
	
	public void test4() {
	  Crossover crossover = new Crossover();				  
	  String result = crossover.verify("NY", true, true, false);	  
	  Assert.assertEquals("NY is not set in CO2 emissions graph.", result);
	}
}
