package com.FitPeoAssessment;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FitPeoAssignment{
public static void main(String[] args) throws InterruptedException {
    WebDriver driver = new ChromeDriver();
    try {
        // Step 1: navigate to the FitPeo homePage
        driver.navigate().to("https://www.fitpeo.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        
        WebDriverWait wwait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Step 2: navigate to the revenue Calculator page
        driver.findElement(By.xpath("//div[text()=\"Revenue Calculator\"]")).click();
        wwait.until(ExpectedConditions.visibilityOfElementLocated(By.id(":r0:"))); 

        Actions act = new Actions(driver);

        // Step 3: scroll down to the slider section
        act.scrollByAmount(0, 400).build().perform();						 
        Thread.sleep(3000); 									//sleepM

        // Step 4: adjust the slider to a specific value
        WebElement scrollbar = driver.findElement(By.xpath("//input[@type='range']")); 
        act.clickAndHold(scrollbar).moveByOffset(99, 0).build().perform(); 
        
        Thread.sleep(3000);
        
        // Step 5: update the text field value associated with the slider
        driver.findElement(By.id(":r0:")).click(); 
        act.keyDown(Keys.BACK_SPACE).build().perform(); 
        act.keyDown(Keys.BACK_SPACE).build().perform(); 
        act.keyDown(Keys.BACK_SPACE).build().perform();
        driver.findElement(By.id(":r0:")).sendKeys("560"); 

        // Step 6: validate that the slider reflects the updated value
        System.out.println(
                driver.findElement(By.xpath("//p[text()=\"560\"]")).getText().equals("560")
                        ? "Testcase pass For Checked Value"
                        : "TestCase Fail For Checked Value");

        // Step 7: select multiple CPT codes using checkBoxes.
        List<WebElement> list = driver.findElements(By.xpath("//div[@class='MuiBox-root css-1p19z09']/div"));
        
        // System.out.println("Total size: " + list.size());		

        driver.findElement(By.xpath("(//input[@class=\"PrivateSwitchBase-input css-1m9pwf3\"])[1]")).click();
        driver.findElement(By.xpath("(//input[@class=\"PrivateSwitchBase-input css-1m9pwf3\"])[2]")).click();
        driver.findElement(By.xpath("(//input[@class=\"PrivateSwitchBase-input css-1m9pwf3\"])[3]")).click();
        driver.findElement(By.xpath("(//input[@class=\"PrivateSwitchBase-input css-1m9pwf3\"])[8]")).click();

        Thread.sleep(3000);

        // Step 8: scroll back to the previous section
        act.scrollByAmount(0, -400).build().perform(); 

        // Update the text field again with a new value
        driver.findElement(By.id(":r0:")).click(); 
        act.keyDown(Keys.BACK_SPACE).build().perform(); 
        act.keyDown(Keys.BACK_SPACE).build().perform(); 
        act.keyDown(Keys.BACK_SPACE).build().perform(); 
        driver.findElement(By.id(":r0:")).sendKeys("820"); 

        // Step 9: validate total recurring reimbursement displayed on the page
        if (driver
                .findElement(By.xpath("(//p[@class=\'MuiTypography-root MuiTypography-body1 inter css-12bch19\'])[3]"))
                .getText().contains("110700")) {
            System.out.println("Total Recurring Reimbursement Verification Passed.");
        } else {
            System.out.println("Total Recurring Reimbursement Verification Failed.");
        }

    } catch (Exception e) {
        e.printStackTrace();
    } finally {
         driver.quit();
    }
}
}
