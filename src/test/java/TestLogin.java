import clients.UserClient;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageObjects.LoginRegistrationPageBurger;

public class TestLogin {
    private WebDriver driver;
    private UserClient userClient = new UserClient();

    private String email;
    private String password;
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

        //create user
        email = "testLogin@yandex.ru";
        password = "qwertyu";
        userClient.registerUser(email, password, "name");
    }

    @Test
    @DisplayName("Login through account button")
    @Description("вход через кнопку «Личный кабинет»")
    public void testLoginByEnterAccountButton() {
        LoginRegistrationPageBurger objBurgerPage = new LoginRegistrationPageBurger(driver);
        objBurgerPage.gotoLoginPageByHeaderLink();
        objBurgerPage.loginAccount(email, password);

        Assert.assertTrue(objBurgerPage.isCheckoutButtonDisplayed());
    }

    @Test
    @DisplayName("Login through enter account button")
    @Description("вход по кнопке «Войти в аккаунт» на главной")
    public void testLoginByHeaderLink() {
        LoginRegistrationPageBurger objBurgerPage = new LoginRegistrationPageBurger(driver);
        objBurgerPage.gotoLoginPageByEnterAccountButton();
        objBurgerPage.loginAccount(email, password);

        Assert.assertTrue(objBurgerPage.isCheckoutButtonDisplayed());
    }

    @Test
    @DisplayName("Login through registration form")
    @Description("вход через кнопку в форме регистрации")
    public void testLoginByButtonFromRegistrationPage() {
        LoginRegistrationPageBurger objBurgerPage = new LoginRegistrationPageBurger(driver);
        objBurgerPage.gotoLoginPageFromRegistration();

        objBurgerPage.loginAccount(email, password);

        Assert.assertTrue(objBurgerPage.isCheckoutButtonDisplayed());
    }
    @Test
    @DisplayName("Login through forgot password form")
    @Description("вход через кнопку в форме восстановления пароля")
    public void testLoginByButtonFromForgotpasswordPage() {
        LoginRegistrationPageBurger objBurgerPage = new LoginRegistrationPageBurger(driver);
        objBurgerPage.gotoLoginPageFromForgotpassword();

        objBurgerPage.loginAccount(email, password);

        Assert.assertTrue(objBurgerPage.isCheckoutButtonDisplayed());
    }
    @After
    public void teardown() {
        //delete user
        userClient.deleteUser(email, password);
        // Закрыть браузер
        driver.quit();
    }
}
