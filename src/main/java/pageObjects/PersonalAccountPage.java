package ru.yandex.praktikum.objects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PersonalAccountPage {
    private WebDriver driver;

    //переход в личный кабинет
    private By gotoAccount = By.xpath("//p[text() = 'Личный Кабинет']");

    private By gotoConstructor = By.xpath("//p[text() = 'Конструктор']");
    private By logoStellarBurgers = By.className("AppHeader_header__logo__2D0X2");

    private By logoutButton = By.xpath("//button[text() = 'Выход']");

    private By createBurgerHeader = By.xpath("//h1[text() = 'Соберите бургер']");



    public PersonalAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("go to account page")
    public void gotoAccountPage(){
        driver.findElement(gotoAccount).click();
    }

    @Step("go to constructor page")
    public void gotoConstructorByButton() {
        driver.findElement(gotoConstructor).click();
    }

    @Step("go to account page")
    public void gotoConstructorByLogo() {
        driver.findElement(logoStellarBurgers).click();
    }

    @Step("click logout button")
    public void clickLogoutButton() {
        //wait until logout button is loaded
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(logoutButton));

        driver.findElement(logoutButton).click();
    }

    @Step ("is Logout button displayed")
    public boolean isLogoutButtonDisplayed(){
        //wait until logout button is loaded
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(logoutButton));

        return driver.findElement(logoutButton).isDisplayed();
    }

    @Step ("is Login button displayed")
    public boolean isConstructorPageDisplayed(){
        //wait until login page is loaded
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(createBurgerHeader));

        return driver.findElement(createBurgerHeader).isDisplayed();
    }
}
