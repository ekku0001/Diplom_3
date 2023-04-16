package ru.yandex.praktikum.objects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ConstructorPage {

    private WebDriver driver;

    private By bunHeader = By.xpath("//span[text() = 'Булки']");
    private By sauceHeader = By.xpath("//span[text() = 'Соусы']");
    private By fillingHeader = By.xpath("//span[text() = 'Начинки']");

    private By bunSection = By.xpath("//h2[text() = 'Булки']");
    private By sauceSection = By.xpath("//h2[text() = 'Соусы']");
    private By fillingSection = By.xpath("//h2[text() = 'Начинки']");

    public ConstructorPage(WebDriver driver) {
        this.driver = driver;
    }
    @Step("go to bun section")
    public void gotoBunSection() {
        driver.findElement(bunHeader).click();
    }

    @Step("go to sauce section")
    public void gotoSauceSection() {
        driver.findElement(sauceHeader).click();
    }

    @Step("go to filling section")
    public void gotoFillingSection() {
        driver.findElement(fillingHeader).click();
    }

    @Step("is buns displayed")
    public boolean isBunDisplayed() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(bunSection));

        return driver.findElement(bunSection).isDisplayed();
    }

    @Step("is sauces displayed")
    public boolean isSauceDisplayed() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(sauceSection));

        return driver.findElement(sauceSection).isDisplayed();
    }

    @Step("is fillings displayed")
    public boolean isFillingDisplayed() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(fillingSection));

        return driver.findElement(fillingSection).isDisplayed();
    }

}
