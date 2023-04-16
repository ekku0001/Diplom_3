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
import ru.yandex.praktikum.objects.ConstructorPage;

public class TestConstructor {
    private WebDriver driver;
    private final String TESTED_URL = "https://stellarburgers.nomoreparties.site/";

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        // Открыть браузер
        driver = new ChromeDriver(options);
        driver.get(TESTED_URL);
    }

    @Test
    @DisplayName("Go to Filling section")
    @Description("переход к разделу «Начинки»")
    public void testGotoFillingSection() {
        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.gotoFillingSection();

        Assert.assertTrue(constructorPage.isFillingDisplayed());
    }

    @Test
    @DisplayName("Go to Sauce section")
    @Description("переход к разделу «Соусы» от «Начинки»")
    public void testGotoSauceSection() {
        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.gotoFillingSection();
        constructorPage.gotoSauceSection();

        Assert.assertTrue(constructorPage.isSauceDisplayed());
    }

    @Test
    @DisplayName("Go to Bun section")
    @Description("переход к разделу «Булки» от «Соусы»")
    public void testGotoBunSection() {
        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.gotoSauceSection();
        constructorPage.gotoBunSection();

        Assert.assertTrue(constructorPage.isBunDisplayed());
    }


    @After
    public void teardown() {
        // Закрыть браузер
        driver.quit();
    }

}
