import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class ProductPage extends Abstract{

    WebDriver driver;
    public ProductPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    int totalPrice = 0;
    public void buyNow() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));

        List<WebElement> items = driver.findElements(By.cssSelector(".shelf-item__title"));
        List<String> likeMe = Arrays.asList("iPhone 12", "iPhone 11", "One Plus 8", "iPhone XS Max","Galaxy S10","Pixel 4","One Plus 8T","One Plus 6T", "Galaxy S20 Ultra");


        for(int i=1; i<=items.size(); i++){
            WebElement itemName = driver.findElement(By.xpath("(//*[@class='shelf-item__title'])["+i+"]"));
            if(likeMe.contains(itemName.getText())){
                WebElement like = driver.findElement(By.xpath("(//*[@class='shelf-stopper'])["+i+"]"));
                like.click();

                WebElement addToCart = driver.findElement(By.xpath("(//div[@class='shelf-item__buy-btn'])["+i+"]"));
                addToCart.click();

                WebElement cross = driver.findElement(By.cssSelector(".float-cart__close-btn"));
                wait.until(ExpectedConditions.visibilityOf(cross));
                Thread.sleep(1000);
                cross.click();

                WebElement getprice = driver.findElement(By.xpath("(//div[@class='shelf-item__price'])["+i+"]//div[@class='val']/b"));
                totalPrice += Integer.parseInt(getprice.getText());
            }
        }
        driver.findElement(By.cssSelector(".buy-btn")).click();
    }

    public void fillForm(){

        driver.findElement(By.cssSelector("#firstNameInput")).sendKeys("Thomas");
        driver.findElement(By.cssSelector("#lastNameInput")).sendKeys("Shelby");

        driver.findElement(By.cssSelector("#addressLine1Input")).sendKeys("Birmingham");
        driver.findElement(By.cssSelector("#provinceInput")).sendKeys("England");
        driver.findElement(By.cssSelector("#postCodeInput")).sendKeys("2312");

        driver.findElement(By.cssSelector("#checkout-shipping-continue")).click();

    }

    public void validation(){
        String confirmation = driver.findElement(By.cssSelector("#confirmation-message")).getText();
        String confirm = "Your Order has been successfully placed.";
        Assert.assertEquals(confirmation, confirm);

        String stringPrice = driver.findElement(By.cssSelector(".cart-priceItem-value")).getText();
        stringPrice = stringPrice.split("\\.")[0].trim().substring(1);

        int price = Integer.parseInt(stringPrice);
        System.out.println(price);
        System.out.println(totalPrice);
        Assert.assertEquals(price, totalPrice);

        driver.findElement(By.cssSelector("#downloadpdf")).click();
    }

    public void close(){
        driver.quit();
    }


}
