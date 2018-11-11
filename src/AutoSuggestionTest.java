

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class AutoSuggestionTest {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\c-deepak.jindal\\Desktop\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://www.ksrtc.in/oprs-web/guest/home.do?h=1");
		driver.findElement(By.id("fromPlaceName")).sendKeys("ben");
		Thread.sleep(5000);
		driver.findElement(By.id("fromPlaceName")).sendKeys(Keys.DOWN);
		System.out.println(driver.findElement(By.id("fromPlaceName")).getText()); // At this point of time, selenium is not able to identify this text as it is hidden. 
		
		// To extract the hidden elements, we use javascript DOM. 
		// To be able to start using javascript DOM, we use "JavascriptExecutor" API provided by selenium itself. 
		//Syntax to initialise JavascriptExecutor- 
		JavascriptExecutor js= (JavascriptExecutor)driver;
		String AutoSuggestiveText = "return document.getElementById(\"fromPlaceName\").value;";
		/*In JS, return is used to print the output. Just like we have system.out.print in java
		To pull out the text of the element, we use "value"
		Make sure to put it into the string. And use slashes forward slashes*/
		
		String finalText = (String) js.executeScript(AutoSuggestiveText);
		System.out.println(finalText);
		while(!finalText.equalsIgnoreCase("BENGALURU INTERNATION AIPORT"))
		{
			driver.findElement(By.id("fromPlaceName")).sendKeys(Keys.DOWN);
			finalText = (String) js.executeScript(AutoSuggestiveText);
			System.out.println(finalText);
		}
		
		
	}
}
		