import clients.UserClient;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageObjects.LoginRegistrationPageBurger;

public class TestRegistration {

    private WebDriver driver;
    private UserClient userClient = new UserClient();
    private final String TESTED_URL = "https://stellarburgers.nomoreparties.site/";

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        // Открыть браузер
        //driver = new YandexDriver();
        driver = new ChromeDriver(options);
        driver.get(TESTED_URL);
    }

    @Test
    public void testRegistrationWithValidData() {
        LoginRegistrationPageBurger objBurgerPage = new LoginRegistrationPageBurger(driver);
        objBurgerPage.gotoRegistrationPage();
        objBurgerPage.registerNewAccount("Петя", "petya@yandex.ru", "1234567");

        Assert.assertTrue(objBurgerPage.isLoginButtonDisplayed());

        //delete user
        userClient.deleteUser("petya@yandex.ru", "1234567");
    }

    @Test
    public void testRegistrationWithInvalidPassword() {
        LoginRegistrationPageBurger objBurgerPage = new LoginRegistrationPageBurger(driver);
        objBurgerPage.gotoRegistrationPage();
        objBurgerPage.registerNewAccount("Петя", "petya@yandex.ru", "12345");

        Assert.assertTrue(objBurgerPage.isIncorrectPasswordDisplayed());
    }

    @After
    public void teardown() {
        // Закрыть браузер
        driver.quit();
    }
}
