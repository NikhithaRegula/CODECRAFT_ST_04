package cbTesting;
import java.net.URL;
import java.util.HashMap;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class LoginTest {

    public static final String USERNAME = "nikhitharegula_mI1vCw";
    public static final String ACCESS_KEY = "xnuFf89pmxNz2w3Pyzhy";

    public static void main(String[] args) throws Exception {

        String[] browsers = {"Chrome", "Firefox", "Edge", "Safari"};

        for (String browser : browsers) {
            runTest(browser);
        }
    }

    public static void runTest(String browser) throws Exception {

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browserName", browser);
        caps.setCapability("browserVersion", "latest");

        HashMap<String, Object> bstackOptions = new HashMap<>();

        if (browser.equals("Safari")) {
            bstackOptions.put("os", "OS X");
            bstackOptions.put("osVersion", "Monterey");
        } else {
            bstackOptions.put("os", "Windows");
            bstackOptions.put("osVersion", "10");
        }

        bstackOptions.put("projectName", "Cross Browser Testing");
        bstackOptions.put("buildName", "Build 1");
        bstackOptions.put("sessionName", browser + " Test");

        caps.setCapability("bstack:options", bstackOptions);

        WebDriver driver = new RemoteWebDriver(
            new URL("https://" + USERNAME + ":" + ACCESS_KEY + "@hub-cloud.browserstack.com/wd/hub"),
            caps
        );

        // Open SauceDemo
        driver.get("https://www.saucedemo.com/");

        // Login
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        // Validation
        if (driver.getCurrentUrl().contains("inventory")) {
            System.out.println(browser + " → Login Passed");
        } else {
            System.out.println(browser + " → Login Failed");
        }

        driver.quit();
    }
}