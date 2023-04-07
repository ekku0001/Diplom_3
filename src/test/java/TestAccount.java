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
import pageObjects.PersonalAccountPage;

public class TestAccount {
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
    @DisplayName("Enter to account page")
    @Description("переход в личный кабинет по клику на «Личный кабинет»")
    public void testEnterAccountPage() {
        LoginRegistrationPageBurger objLoginPage = new LoginRegistrationPageBurger(driver);
        objLoginPage.gotoLoginPageByEnterAccountButton();
        objLoginPage.loginAccount(email, password);

        PersonalAccountPage objPersonalAccountPage = new PersonalAccountPage(driver);
        objPersonalAccountPage.gotoAccountPage();

        Assert.assertTrue(objPersonalAccountPage.isLogoutButtonDisplayed());
    }

    @Test
    @DisplayName("Enter to constructor from account page")
    @Description("переход в конструктор по клику на «Конструктор»")
    public void testEnterConstructorByButton() {
        LoginRegistrationPageBurger objLoginPage = new LoginRegistrationPageBurger(driver);
        objLoginPage.gotoLoginPageByEnterAccountButton();
        objLoginPage.loginAccount(email, password);

        PersonalAccountPage objPersonalAccountPage = new PersonalAccountPage(driver);
        objPersonalAccountPage.gotoAccountPage();

        objPersonalAccountPage.gotoConstructorByButton();

        Assert.assertTrue(objPersonalAccountPage.isConstructorPageDisplayed());
    }

    @Test
    @DisplayName("Enter to constructor from account page")
    @Description("переход в конструктор по клику на на логотип Stellar Burgers")
    public void testEnterConstructorByLogo() {
        LoginRegistrationPageBurger objLoginPage = new LoginRegistrationPageBurger(driver);
        objLoginPage.gotoLoginPageByEnterAccountButton();
        objLoginPage.loginAccount(email, password);

        PersonalAccountPage objPersonalAccountPage = new PersonalAccountPage(driver);
        objPersonalAccountPage.gotoAccountPage();

        objPersonalAccountPage.gotoConstructorByLogo();

        Assert.assertTrue(objPersonalAccountPage.isConstructorPageDisplayed());
    }

    @Test
    @DisplayName("Logout from account")
    @Description("Проверь выход по кнопке «Выйти» в личном кабинете")
    public void testLogoutAccount() {
        LoginRegistrationPageBurger objLoginPage = new LoginRegistrationPageBurger(driver);
        objLoginPage.gotoLoginPageByEnterAccountButton();
        objLoginPage.loginAccount(email, password);

        PersonalAccountPage objPersonalAccountPage = new PersonalAccountPage(driver);
        objPersonalAccountPage.gotoAccountPage();
        objPersonalAccountPage.clickLogoutButton();

        Assert.assertTrue(objLoginPage.isLoginButtonDisplayed());
    }

    @After
    public void teardown() {
        //delete user
        userClient.deleteUser(email, password);
        // Закрыть браузер
        driver.quit();
    }
}
