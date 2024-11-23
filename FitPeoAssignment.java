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
    // Initialize Chrome WebDriver
    WebDriver driver = new ChromeDriver();
    try {
        // Step 1: Navigate to the FitPeo homepage
        driver.navigate().to("https://www.fitpeo.com/");
        driver.manage().window().maximize(); // Maximize the browser window
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Set implicit wait for element loading

        // Initialize explicit wait
        WebDriverWait wwait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Step 2: Navigate to the Revenue Calculator page
        driver.findElement(By.xpath("//div[text()=\"Revenue Calculator\"]")).click();
        wwait.until(ExpectedConditions.visibilityOfElementLocated(By.id(":r0:"))); // Wait until the page element is visible

        // Initialize Actions class for performing complex interactions
        Actions act = new Actions(driver);

        // Step 3: Scroll down to the slider section
        act.scrollByAmount(0, 400).build().perform(); // Scroll vertically by 400 pixels
        Thread.sleep(3000); // Pause to allow UI to stabilize

        // Step 4: Adjust the slider to a specific value
        WebElement scrollbar = driver.findElement(By.xpath("//input[@type=\"range\"]")); // Locate the slider element
        act.clickAndHold(scrollbar).moveByOffset(99, 0).build().perform(); // Drag the slider by 99 pixels horizontally

        // Step 5: Update the text field value associated with the slider
        driver.findElement(By.id(":r0:")).click(); // Click on the text field to focus
        act.keyDown(Keys.BACK_SPACE).build().perform(); // Clear the existing value (first digit)
        act.keyDown(Keys.BACK_SPACE).build().perform(); // Clear the existing value (second digit)
        act.keyDown(Keys.BACK_SPACE).build().perform(); // Clear the existing value (third digit)
        driver.findElement(By.id(":r0:")).sendKeys("560"); // Input the new value into the text field

        // Step 6: Validate that the slider reflects the updated value
        System.out.println(
                driver.findElement(By.xpath("//p[text()=\"560\"]")).getText().equals("560")
                        ? "Testcase pass For Checked Value"
                        : "TestCase Fail For Checked Value");

        // Step 7: Select multiple CPT codes using checkboxes
        List<WebElement> list = driver.findElements(By.xpath("//div[@class='MuiBox-root css-1p19z09']/div"));
        // Uncomment below to print the total number of CPT code options
        // System.out.println("Total size: " + list.size());

        // Select specific CPT codes by clicking corresponding checkboxes
        driver.findElement(By.xpath("(//input[@class=\"PrivateSwitchBase-input css-1m9pwf3\"])[1]")).click();
        driver.findElement(By.xpath("(//input[@class=\"PrivateSwitchBase-input css-1m9pwf3\"])[2]")).click();
        driver.findElement(By.xpath("(//input[@class=\"PrivateSwitchBase-input css-1m9pwf3\"])[3]")).click();
        driver.findElement(By.xpath("(//input[@class=\"PrivateSwitchBase-input css-1m9pwf3\"])[8]")).click();

        Thread.sleep(3000); // Pause to ensure interactions are complete

        // Step 8: Scroll back to the previous section
        act.scrollByAmount(0, -400).build().perform(); // Scroll vertically upwards by 400 pixels

        // Update the text field again with a new value
        driver.findElement(By.id(":r0:")).click(); // Focus on the text field
        act.keyDown(Keys.BACK_SPACE).build().perform(); // Clear the value (first digit)
        act.keyDown(Keys.BACK_SPACE).build().perform(); // Clear the value (second digit)
        act.keyDown(Keys.BACK_SPACE).build().perform(); // Clear the value (third digit)
        driver.findElement(By.id(":r0:")).sendKeys("820"); // Input the new value

        // Step 9: Validate total recurring reimbursement displayed on the page
        if (driver
                .findElement(By.xpath("(//p[@class=\'MuiTypography-root MuiTypography-body1 inter css-12bch19\'])[3]"))
                .getText().contains("110700")) {
            System.out.println("Total Recurring Reimbursement Verification Passed.");
        } else {
            System.out.println("Total Recurring Reimbursement Verification Failed.");
        }

    } catch (Exception e) {
        // Handle and print any exceptions encountered during execution
        e.printStackTrace();
    } finally {
        // Ensure the browser is closed at the end of execution
        driver.quit();
    }
}
}
