package de.cron3x.cor3.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class LaunchChrome {
    public LaunchChrome(String webdriverfile, String binary) {
        if (System.getProperty("os.name").equalsIgnoreCase("windows 10")){
            System.setProperty("webdriver.chrome.driver", webdriverfile + "//chromedriver.exe");
        }
        else {
            System.setProperty("webdriver.chrome.driver", webdriverfile + "//chromedriver");
        }


        WebDriver driver = new ChromeDriver();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--ignore-certificate-errors","--test-type","--headless");
        options.setBinary(binary);
        driver.get("https://aternos.org/servers/");

        driver.close();
    }
}
