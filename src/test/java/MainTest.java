import org.testng.annotations.Test;

public class MainTest extends Base{

    public void signIn(){
        SignIn obj = new SignIn(driver);
        obj.clickOnSignIn();
        obj.usernameAndPassword();
    }

    @Test
    public void buy() throws InterruptedException {

        SignIn sobj = new SignIn(driver);
        sobj.clickOnSignIn();
        sobj.usernameAndPassword();

        Thread.sleep(2000);

        ProductPage obj = new ProductPage(driver);
        obj.buyNow();

        Thread.sleep(2000);
        obj.fillForm();

        Thread.sleep(2000);
        obj.validation();

        obj.close();
    }


}
