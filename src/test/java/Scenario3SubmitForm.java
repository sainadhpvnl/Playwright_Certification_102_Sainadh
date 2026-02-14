import Base.BaseTest;
import Base.Driver;
import Base.LTCapability;
import com.google.gson.JsonObject;
import com.microsoft.playwright.*;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;


//@RunWith(DataProviderRunner.class)
public class Scenario3SubmitForm extends BaseTest {
    public static void main(String[] args) {
//    @Test
//    @UseDataProvider(value = "getTestCapability", location = LTCapability.class)
//    public void TestScenario3(JsonObject capability) {
        Driver driver = null;
        Page page = null;
        try {
//            driver = super.createConnection(capability);
//            page = driver.getPage();
            Playwright playwright = Playwright.create();
            // Launch Chromium in headed mode (visible UI)
            Browser browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));


            // Create a new isolated browser context
            BrowserContext context = browser.newContext();

            // Create a new page within the context
            page = context.newPage();

//                / ////////////////////////
            page.navigate(TestConfig.TEST_URL); // Navigate to the URL
            page.setViewportSize(1900, 1050);

            page.locator("//a[text()='Input Form Submit']").click(); // click “Input Form Submit” link.

            page.locator("//button[text()='Submit']").click(); // click “Submit” button.

            Locator validationMessage = page.locator("//div[contains(@class,'w-4/12 smtablet')]/input[@type='text']");

            if (validationMessage.isVisible()) {
                System.out.println("Validation message is displayed: 'Please fill out this field'");
//                super.setTestStatus("Passed", "Validation message is displayed: 'Please fill out this field'", page);
            } else {
                System.out.println("Validation message is not displayed");
//                super.setTestStatus("Failed", "Validation message is not displayed", page);
            }

            // Entering the form details
            page.locator("//input[@placeholder='Name']").fill("Sainadh");
            page.locator("//input[@placeholder='Email']").fill("sainadhpvnl@gmail.com");
            page.locator("//input[@placeholder='Password']").fill("Test@12345");
            page.locator("//input[@placeholder='Company']").fill("Persistent Systems");
            page.locator("//input[@placeholder='Website']").fill("https://www.persistent.com/");

            page.locator("//select[@name='country']").selectOption("United States");
            page.locator("//input[@placeholder='City']").fill("Los Angeles");
            page.locator("//input[@placeholder='Address 1']").fill("Test_address 1");
            page.locator("//input[@placeholder='Address 2']").fill("Test_address 2");
            page.locator("//input[@placeholder='State']").fill("California");
            page.locator("//input[@placeholder='Zip code']").fill("90001");

            page.locator("//button[text()='Submit']").click(); // click “Submit” button.
            page.waitForTimeout(3000);
            String ExpectedConfimationMessage = " Thanks for contacting us, we will get back to you shortly.";
            assertThat(page.locator("//p[@class='success-msg hidden']")).containsText(ExpectedConfimationMessage);
            System.out.println("Success message is displaying correctly i.e."+ExpectedConfimationMessage);

            //Validating the page title
            if(page.title().equalsIgnoreCase("Selenium Grid Online | Run Selenium Test On Cloud")){
                System.out.println("Test Passed");
//                super.setTestStatus("Passed", "Title matched", page);
            }else{
                System.out.println("Test Failed");
//                super.setTestStatus("Failed", "Title not matched", page);
            }
            page.close();
            browser.close();
//            super.closeConnection(driver);
        } catch (Exception e) {
            e.printStackTrace();
            if (driver != null) {
//                super.setTestStatus("Failed", e.getMessage(), page);
            }
        } finally {
            if (driver != null) {
//                super.closeConnection(driver);
            }
        }
    }
}
