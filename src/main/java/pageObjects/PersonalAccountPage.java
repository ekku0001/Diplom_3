package pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PersonalAccountPage {
    private WebDriver driver;

    //переход в личный кабинет
    private final By GOTO_ACCOUNT = By.xpath("//p[text() = 'Личный Кабинет']");

    private final By GOTO_CONSTUCTOR = By.xpath("//p[text() = 'Конструктор']");
    private final By LOGO_STELLAR_BURGERS = By.className("AppHeader_header__logo__2D0X2");

    private final By LOGOUT_BUTTON = By.xpath("//button[text() = 'Выход']");

    private final By CREATEBURGER_HEADER = By.xpath("//h1[text() = 'Соберите бургер']");




    public PersonalAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("go to account page")
    public void gotoAccountPage(){
        driver.findElement(GOTO_ACCOUNT).click();
    }

    @Step("go to constructor page")
    public void gotoConstructorByButton() {
        driver.findElement(GOTO_CONSTUCTOR).click();
    }

    @Step("go to account page")
    public void gotoConstructorByLogo() {
        driver.findElement(LOGO_STELLAR_BURGERS).click();
    }

    @Step("click logout button")
    public void clickLogoutButton() {
        //wait until logout button is loaded
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(LOGOUT_BUTTON));

        driver.findElement(LOGOUT_BUTTON).click();
    }

    @Step ("is Logout button displayed")
    public boolean isLogoutButtonDisplayed(){
        //wait until logout button is loaded
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(LOGOUT_BUTTON));

        return driver.findElement(LOGOUT_BUTTON).isDisplayed();
    }

    @Step ("is Login button displayed")
    public boolean isConstructorPageDisplayed(){
        //wait until login page is loaded
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(CREATEBURGER_HEADER));

        return driver.findElement(CREATEBURGER_HEADER).isDisplayed();
    }
}
