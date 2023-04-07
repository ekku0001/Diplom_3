package pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginRegistrationPageBurger {

    private WebDriver driver;

    //переход в форму входа в личный кабинет
    private final By GOTO_ACCOUNT_FROM_HEADER = By.xpath("//p[text() = 'Личный Кабинет']");

    private final By ENTER_ACCOUNT_BUTTON  = By.xpath("//button[text() = 'Войти в аккаунт']");

    private final By ACCOUNT_LINK_BUTTON  = By.xpath("//a[text() = 'Войти']");

    //переход в форму регистрации
    private final By REGISTRATION_LINK_BUTTON = By.xpath("//a[text() = 'Зарегистрироваться']");

    //переход в форму восстановления пароля
    private final By FORGOTPASSWORD_LINK_BUTTON = By.xpath("//a[text() = 'Восстановить пароль']");


    //поля имя, почта, пароль
    private final By NAME_INPUT = By.xpath("//label[text() = 'Имя']/parent::*/input");
    private final By EMAIL_INPUT = By.xpath("//label[text() = 'Email']/parent::*/input");
    private final By PASSWORD_INPUT = By.xpath("//label[text() = 'Пароль']/parent::*/input");

    //кнопка войти
    private final By LOGIN_BUTTON = By.xpath("//button[text() = 'Войти']");

    //кнопка регистрация
    private final By REGISTER_BUTTON = By.xpath("//button[text() = 'Зарегистрироваться']");

    private final By INCORRECT_PASSWORD = By.xpath("//*[text() = 'Некорректный пароль']");

    //кнопка отображаемая после входа на сайт
    private final By CHECKOUT_BUTTON = By.xpath("//button[text() = 'Оформить заказ']");


    public LoginRegistrationPageBurger(WebDriver driver) {
        this.driver = driver;
    }

    @Step ("go to login page from header link")
    public void gotoLoginPageByHeaderLink(){
        driver.findElement(GOTO_ACCOUNT_FROM_HEADER).click();
    }

    @Step ("go to login page by enter account  button")
    public void gotoLoginPageByEnterAccountButton(){
        driver.findElement(ENTER_ACCOUNT_BUTTON).click();
    }

    @Step ("go to registration page")
    public void gotoRegistrationPage(){
        this.gotoLoginPageByEnterAccountButton();
        WebElement element = driver.findElement(REGISTRATION_LINK_BUTTON);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        element.click();
    }

    @Step ("go to forgot password page")
    public void gotoForgotpasswordPage(){
        this.gotoLoginPageByEnterAccountButton();
        WebElement element = driver.findElement(FORGOTPASSWORD_LINK_BUTTON);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        element.click();
    }

    @Step ("go to login page from registration page")
    public void gotoLoginPageFromRegistration(){
        this.gotoRegistrationPage();
        WebElement element = driver.findElement(ACCOUNT_LINK_BUTTON);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        element.click();
    }

    @Step ("go to login page from forgot password page")
    public void gotoLoginPageFromForgotpassword(){
        this.gotoForgotpasswordPage();
        WebElement element = driver.findElement(ACCOUNT_LINK_BUTTON);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        element.click();
    }

    @Step ("fill name, email and password and register")
    public void registerNewAccount(String name, String email, String password){
        driver.findElement(NAME_INPUT).clear();
        driver.findElement(NAME_INPUT).sendKeys(name);

        driver.findElement(EMAIL_INPUT).clear();
        driver.findElement(EMAIL_INPUT).sendKeys(email);

        driver.findElement(PASSWORD_INPUT).clear();
        driver.findElement(PASSWORD_INPUT).sendKeys(password);

        this.clickRegisterButton();
    }

    @Step ("fill name, email and password and register")
    public void loginAccount(String email, String password){
        //wait until login page is loaded
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(LOGIN_BUTTON));

        driver.findElement(EMAIL_INPUT).clear();
        driver.findElement(EMAIL_INPUT).sendKeys(email);

        driver.findElement(PASSWORD_INPUT).clear();
        driver.findElement(PASSWORD_INPUT).sendKeys(password);

        this.clickLoginButton();
    }


    @Step ("press login button")
    public void clickLoginButton(){
        driver.findElement(LOGIN_BUTTON).click();
    }

    @Step ("press registration button")
    public void clickRegisterButton(){
        driver.findElement(REGISTER_BUTTON).click();
    }

    @Step ("is Login button displayed")
    public boolean isLoginButtonDisplayed(){
        //wait until login page is loaded
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(LOGIN_BUTTON));

        return driver.findElement(LOGIN_BUTTON).isDisplayed();
    }

    @Step ("is checkout button displayed after login")
    public boolean isCheckoutButtonDisplayed(){
        //wait until main page with its objects is loaded
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(CHECKOUT_BUTTON));

        return driver.findElement(CHECKOUT_BUTTON).isDisplayed();
    }

    @Step ("is incorrect password displayed")
    public boolean isIncorrectPasswordDisplayed(){
        return driver.findElement(INCORRECT_PASSWORD).isDisplayed();
    }

}
