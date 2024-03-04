import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;

public class Base {

    WebDriver driver;

    @BeforeTest
    public void setDriver(){
        driver = new ChromeDriver();
    }

    @BeforeTest (dependsOnMethods = "setDriver")
    public void goToPage(){
        driver.manage().window().maximize();
        driver.get("https://www.bstackdemo.com/");
    }


}
