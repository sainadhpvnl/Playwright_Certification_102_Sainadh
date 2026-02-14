import Base.BaseTest;
import Base.Driver;
import Base.LTCapability;
import com.google.gson.JsonObject;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.LoadState;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(DataProviderRunner.class)

    public class Scenario1SimpleFormDemo extends BaseTest {

        @Test
        @UseDataProvider(value = "getTestCapability", location = LTCapability.class)
        public void TestScenario1(JsonObject capability) {
            Driver driver = null;
            Page page = null;
            try {
            driver=super.createConnection(capability);
            page=driver.getPage();
                page.navigate(TestConfig.TEST_URL); //Navigate to the URL

//                page.setViewportSize(1900, 1050);

                page.locator("//a[text()='Simple Form Demo']").click(); //Click on 'Simple Form Demo '

                String currentURL = page.url();
                assertTrue(currentURL.contains("simple-form-demo"));  //Validate that URL contains "simple-form-demo"
                page.waitForLoadState(LoadState.DOMCONTENTLOADED);
                page.waitForLoadState(LoadState.LOAD);
                String value = "Welcome to TestMu AI";
                Locator text= page.locator("input#user-message");
                text.fill(value);
                page.waitForTimeout(3000);
                page.waitForLoadState(LoadState.DOMCONTENTLOADED);
                page.waitForLoadState(LoadState.LOAD);
                Locator clickbutton=page.locator("#showInput"); //click on 'Get Checked Value' button
                clickbutton.click();
                page.waitForLoadState(LoadState.DOMCONTENTLOADED);
                page.waitForLoadState(LoadState.LOAD);
                System.out.println("clicked on the button");


                assertThat(page.locator("//div[@id='user-message']//p[@id='message']")).containsText(value);
                //Validate whether the same text message is displayed in the right-hand panel under the “Your Message:” section.

                System.out.println("Message Displayed is same in the right hand panel under the “Your Message:” section");

//                Validating the page title
                if (page.title().equalsIgnoreCase("Selenium Grid Online | Run Selenium Test On Cloud")) {
                    super.setTestStatus("Passed", "Title matched", page);
                } else {
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
//        }

    }
}