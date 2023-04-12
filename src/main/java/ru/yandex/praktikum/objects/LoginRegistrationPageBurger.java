package ru.yandex.praktikum.objects;

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
    private By gotoAccountFromHeader = By.xpath("//p[text() = 'Личный Кабинет']");

    private By enterAccountButton  = By.xpath("//button[text() = 'Войти в аккаунт']");

    private By accountLinkButton  = By.xpath("//a[text() = 'Войти']");

    //переход в форму регистрации
    private By registrationLinkButton = By.xpath("//a[text() = 'Зарегистрироваться']");

    //переход в форму восстановления пароля
    private By forgotPasswordLinkButton = By.xpath("//a[text() = 'Восстановить пароль']");


    //поля имя, почта, пароль
    private By nameInput = By.xpath("//label[text() = 'Имя']/parent::*/input");
    private By emailInput = By.xpath("//label[text() = 'Email']/parent::*/input");
    private By passwordInput = By.xpath("//label[text() = 'Пароль']/parent::*/input");

    //кнопка войти
    private By loginButton = By.xpath("//button[text() = 'Войти']");

    //кнопка регистрация
    private By registerButton = By.xpath("//button[text() = 'Зарегистрироваться']");

    private By incorrectPassword = By.xpath("//*[text() = 'Некорректный пароль']");

    //кнопка отображаемая после входа на сайт
    private By checkoutButton = By.xpath("//button[text() = 'Оформить заказ']");


    public LoginRegistrationPageBurger(WebDriver driver) {
        this.driver = driver;
    }

    @Step ("go to login page from header link")
    public void gotoLoginPageByHeaderLink(){
        driver.findElement(gotoAccountFromHeader).click();
    }

    @Step ("go to login page by enter account  button")
    public void gotoLoginPageByEnterAccountButton(){
        driver.findElement(enterAccountButton).click();
    }

    @Step ("go to registration page")
    public void gotoRegistrationPage(){
        this.gotoLoginPageByEnterAccountButton();
        WebElement element = driver.findElement(registrationLinkButton);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        element.click();
    }

    @Step ("go to forgot password page")
    public void gotoForgotpasswordPage(){
        this.gotoLoginPageByEnterAccountButton();
        WebElement element = driver.findElement(forgotPasswordLinkButton);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        element.click();
    }

    @Step ("go to login page from registration page")
    public void gotoLoginPageFromRegistration(){
        this.gotoRegistrationPage();
        WebElement element = driver.findElement(accountLinkButton);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        element.click();
    }

    @Step ("go to login page from forgot password page")
    public void gotoLoginPageFromForgotpassword(){
        this.gotoForgotpasswordPage();
        WebElement element = driver.findElement(accountLinkButton);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        element.click();
    }

    @Step ("fill name, email and password and register")
    public void registerNewAccount(String name, String email, String password){
        driver.findElement(nameInput).clear();
        driver.findElement(nameInput).sendKeys(name);

        driver.findElement(emailInput).clear();
        driver.findElement(emailInput).sendKeys(email);

        driver.findElement(passwordInput).clear();
        driver.findElement(passwordInput).sendKeys(password);

        this.clickRegisterButton();
    }

    @Step ("fill name, email and password and register")
    public void loginAccount(String email, String password){
        //wait until login page is loaded
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(loginButton));

        driver.findElement(emailInput).clear();
        driver.findElement(emailInput).sendKeys(email);

        driver.findElement(passwordInput).clear();
        driver.findElement(passwordInput).sendKeys(password);

        this.clickLoginButton();
    }


    @Step ("press login button")
    public void clickLoginButton(){
        driver.findElement(loginButton).click();
    }

    @Step ("press registration button")
    public void clickRegisterButton(){
        driver.findElement(registerButton).click();
    }

    @Step ("is Login button displayed")
    public boolean isLoginButtonDisplayed(){
        //wait until login page is loaded
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(loginButton));

        return driver.findElement(loginButton).isDisplayed();
    }

    @Step ("is checkout button displayed after login")
    public boolean isCheckoutButtonDisplayed(){
        //wait until main page with its objects is loaded
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(checkoutButton));

        return driver.findElement(checkoutButton).isDisplayed();
    }

    @Step ("is incorrect password displayed")
    public boolean isIncorrectPasswordDisplayed(){
        return driver.findElement(incorrectPassword).isDisplayed();
    }

}
