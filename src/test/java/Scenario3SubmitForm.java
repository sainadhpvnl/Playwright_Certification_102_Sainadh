import com.google.gson.JsonObject;
import com.microsoft.playwright.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;


import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Scenario3SubmitForm extends BaseTest {


@ParameterizedTest
@MethodSource("LTCapability#getTestCapability")
    public void TestScenario3(JsonObject capability) {
        Driver driver = null;
        Page page = null;
        try {
            driver = super.createConnection(capability);
            page = driver.getPage();

            page.navigate(TestConfig.TEST_URL); // Navigate to the URL
            page.setViewportSize(1900, 1050);

            page.locator("//a[text()='Input Form Submit']").click(); // click “Input Form Submit” link.

            page.locator("//button[text()='Submit']").click(); // click “Submit” button.

            Locator validationMessage = page.locator("//div[contains(@class,'w-4/12 smtablet')]/input[@type='text']");

            if (validationMessage.isVisible()) {
                System.out.println("Validation message is displayed: 'Please fill out this field'");
                super.setTestStatus("Passed", "Validation message is displayed: 'Please fill out this field'", page);
            } else {
                System.out.println("Validation message is not displayed");
                super.setTestStatus("Failed", "Validation message is not displayed", page);
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
                super.setTestStatus("Passed", "Title matched", page);
            }else{
                System.out.println("Test Failed");
                super.setTestStatus("Failed", "Title not matched", page);
            }

            super.closeConnection(driver);
        } catch (Exception e) {
            e.printStackTrace();
            if (driver != null) {
                super.setTestStatus("Failed", e.getMessage(), page);
            }
        } finally {
            if (driver != null) {
                super.closeConnection(driver);
            }
        }
    }
}
