import org.testng.annotations.Test;

public class MainTest extends Base{
    
    @Test
    public void signInTest(){
        SignIn obj = new SignIn(driver);
        obj.clickOnSignIn();
        obj.usernameAndPassword();
    }

    ProductPage obj;
    @Test (dependsOnMethods = "signInTest")
    public void buy() throws InterruptedException {
        obj = new ProductPage(driver);
        obj.buyNow();
    }

    @Test (dependsOnMethods = "buy")
    public void formFill(){
        obj.fillForm();
    }

    @Test (dependsOnMethods = "formFill")
    public void validate(){
        obj.validation();
        obj.close();
    }
}
