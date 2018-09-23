package fwk.rivetest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by stacybustos on 12/26/14.
 * Tester Class for RiveTest framework
 */

public class Tester {

    public static WebDriver driver;

    public Tester() {
    }

    //To perform prior the test case
    public static void setUp(NavigatorDriver navigatorDriver, String url)
    {
        switch (navigatorDriver){
            case Firefox:
                //Set Up the driver with Firefox
                driver = new FirefoxDriver();
                break;
            case Chrome:
                //Set Up the driver with Chrome
                driver = new ChromeDriver();
                break;
            case Safari:
                //Set Up the driver with Safari
                driver = new SafariDriver();
                break;
            case Opera:
                //Set Up the driver with Opera
                driver = new OperaDriver();
                break;
            case InternetExplorer:
                //Set Up the driver with Internet Explorer
                driver = new InternetExplorerDriver();
                break;
            default:
                //Set Up the driver with Firefox
                driver = new FirefoxDriver();
                break;
        }

        //Set the URL of the site under test
        driver.get(url);
        //Maximizing the browser
        driver.manage().window().maximize();
    }

    //To perform at the end of the test case
    public static void tearDown()
    {
        //Quit all the instances of the WebDriver (browser)
        driver.quit();
    }

    public static boolean prueba(boolean element){
        return element;
    }

    //To click a web element
    public static void click(String locator){
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.elementToBeClickable(By.id(locator)));
        driver.findElement(By.id(locator)).click();
    }

    //To click a web element by its href link
    public static void clickHref(String locator){
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href*=" + "'" + locator + "'" + "]")));
        driver.findElement(By.cssSelector("a[href*=" + "'" + locator + "'" + "]")).click();
    }

    //To type in a web element
    public static void type(String locator, String value){
        driver.findElement(By.id(locator)).sendKeys(value);
    }

    public static void clear(String locator) {
        driver.findElement(By.id(locator)).clear();
    }

    //To load a specific URL
    public static void goToUrl(String url) {
        driver.get(url);
    }

    //To go back in the browser
    public static void goBack(String locator) {
        driver.navigate().back();
    }

    //To go forward in the browser
    public static void goForward(String locator) {
        driver.navigate().forward();
    }

    //go to parent window
    public void switchParentWindow(){
        driver.switchTo().window((String) driver.getWindowHandles().toArray()[0]);
    }

    //go to latest opened window
    public void switchNewWindow(){
        for(String winHandle : driver.getWindowHandles()){
            driver.switchTo().window(winHandle);
        }
    }

    //To check whether a web element is Present or not (regardless if it is displayed or not)
    public static boolean isElementPresent(String locator) {
        try {
            driver.findElement(By.id(locator));
            return true;
        } catch (Exception e) {
            System.out.println("Locator-> "+locator+" not found");
            return false;
        }
    }

    //To check whether a web element is Displayed or not
    public static boolean isElementDisplayed(String locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 8);
            wait.until(ExpectedConditions. visibilityOfElementLocated(By.id(locator)));
            driver.findElement(By.id(locator)).isDisplayed();
            return true;
        } catch (Exception e) {
            System.out.println("Locator-> "+locator+" not found");
            return false;
        }
    }

    //To check whether a web element is Selected or not
    public static boolean isElementSelected(String locator) {
        try {
            driver.findElement(By.id(locator)).isSelected();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //To check whether a web element is Enabled or not
    public static boolean isElementEnabled(String locator) {
        try {
            driver.findElement(By.id(locator)).isEnabled();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //To get current URL
    public static String getUrl(String locator) {
           String url=  driver.getCurrentUrl();
        return url;
    }

    //To get current value of an element
    public static String getValue(String locator) {
            String value=  driver.findElement(By.id(locator)).getAttribute("value");
        return value;
    }

    //To get current value of an element
    public static String getElementText(String locator) {
        String value=  driver.findElement(By.id(locator)).getText();
        return value;
    }

    public static boolean isTextContained(String text) {
        try {
            driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*"+text+"[\\s\\S]*$");
            return true;
        }catch (Exception e) {
            return false;
        }
    }



}
