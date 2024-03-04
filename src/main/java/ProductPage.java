import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
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

    @FindBy(css = ".shelf-item__title")
    List<WebElement> items;

    @FindBy (css = ".username")
    WebElement usernamecheck;

    int totalPrice = 0;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));
    public void buyNow() throws InterruptedException {

        wait.until(ExpectedConditions.visibilityOf(usernamecheck));
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
    @FindBy(css = "#firstNameInput")
    WebElement firstName;

    @FindBy(css = "#lastNameInput")
    WebElement lastName;

    @FindBy (css ="#addressLine1Input")
    WebElement address;

    @FindBy (css = "#provinceInput")
    WebElement privince;

    @FindBy (css = "#postCodeInput")
    WebElement postCode;

    @FindBy (css = "#checkout-shipping-continue")
    WebElement checkout;

    public void fillForm(){

        wait.until(ExpectedConditions.visibilityOf(firstName));
        firstName.sendKeys("Thomas");
        lastName.sendKeys("Shelby");

        address.sendKeys("Birmingham");
        privince.sendKeys("England");
        postCode.sendKeys("2312");
        checkout.click();
    }
    @FindBy(css = "#confirmation-message")
    WebElement confirmEle;

    @FindBy(css = ".cart-priceItem-value")
    WebElement pricetag;

    @FindBy(css = "#downloadpdf")
    WebElement download;

    public void validation(){

        wait.until(ExpectedConditions.visibilityOf(confirmEle));
        String confirmation = confirmEle.getText();
        String confirm = "Your Order has been successfully placed.";
        Assert.assertEquals(confirmation, confirm);

        String stringPrice = pricetag.getText();
        stringPrice = stringPrice.split("\\.")[0].trim().substring(1);

        int price = Integer.parseInt(stringPrice);
        System.out.println(price);
        System.out.println(totalPrice);
        Assert.assertEquals(price, totalPrice);

        download.click();
    }

    public void close(){
        driver.quit();
    }


}
