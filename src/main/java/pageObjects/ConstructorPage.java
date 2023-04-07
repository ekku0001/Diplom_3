package pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ConstructorPage {

    private WebDriver driver;

    private final By BUN_HEADER = By.xpath("//span[text() = 'Булки']");
    private final By SAUCE_HEADER = By.xpath("//span[text() = 'Соусы']");
    private final By FILLING_HEADER = By.xpath("//span[text() = 'Начинки']");

    private final By BUN_SECTION = By.xpath("//h2[text() = 'Булки']");
    private final By SAUCE_SECTION = By.xpath("//h2[text() = 'Соусы']");
    private final By FILLING_SECTION = By.xpath("//h2[text() = 'Начинки']");

    public ConstructorPage(WebDriver driver) {
        this.driver = driver;
    }
    @Step("go to bun section")
    public void gotoBunSection() {
        driver.findElement(BUN_HEADER).click();
    }

    @Step("go to sauce section")
    public void gotoSauceSection() {
        driver.findElement(SAUCE_HEADER).click();
    }

    @Step("go to filling section")
    public void gotoFillingSection() {
        driver.findElement(FILLING_HEADER).click();
    }

    @Step("is buns displayed")
    public boolean isBunDisplayed() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(BUN_SECTION));

        return driver.findElement(BUN_SECTION).isDisplayed();
    }

    @Step("is sauces displayed")
    public boolean isSauceDisplayed() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(SAUCE_SECTION));

        return driver.findElement(SAUCE_SECTION).isDisplayed();
    }

    @Step("is fillings displayed")
    public boolean isFillingDisplayed() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(FILLING_SECTION));

        return driver.findElement(FILLING_SECTION).isDisplayed();
    }

}
