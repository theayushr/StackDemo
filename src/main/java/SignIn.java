import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignIn extends Abstract{
    WebDriver driver;
    public SignIn(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy (css = ".Navbar_link__3Blki.logout-link")
    WebElement signIn;

    public void clickOnSignIn(){
        waitThenClick(signIn);
    }

    @FindBy(css = "#username")
    WebElement username;

    @FindBy(xpath = "//*[text()='demouser']")
    WebElement userid;

    @FindBy(css= "#password")
    WebElement password;

    @FindBy(xpath = "//*[text()='testingisfun99']")
    WebElement passwordId;

    @FindBy(css = "#login-btn")
    WebElement loginButton;

    public void usernameAndPassword(){

        waitThenClick(username);
        waitThenClick(userid);

        waitThenClick(password);
        waitThenClick(passwordId);

        waitThenClick(loginButton);
    }
}
