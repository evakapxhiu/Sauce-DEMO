package base.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import static base.tests.DriverSetUp.getChromeDriver;
public class BaseTest {
    public static final String BASE_URL = "https://www.saucedemo.com";
    public static WebDriver driver;
    @BeforeEach
    void setUp() {
        driver = getChromeDriver();
    }
//    @AfterEach
//    void cleanUp() {
//        driver.quit();
//    }
}

