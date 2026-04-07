package com;

import com.utils.LogUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

public abstract class BaseTest {

    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    @BeforeMethod(alwaysRun = true)
    public void setUp(Method method) {
        LogUtil.info("========== Starting test: " + method.getName() + " ==========");
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");

        WebDriver driver = new ChromeDriver(options);
        driverThreadLocal.set(driver);
        LogUtil.info("Browser launched successfully");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(Method method) {
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            driver.quit();
            driverThreadLocal.remove();
            LogUtil.info("Browser closed");
        }
        LogUtil.info("========== Finished test: " + method.getName() + " ==========");
    }

    protected WebDriver getDriver() {
        return driverThreadLocal.get();
    }
}
