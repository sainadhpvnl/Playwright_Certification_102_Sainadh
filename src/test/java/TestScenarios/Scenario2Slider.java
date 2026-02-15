package TestScenarios;

import Util.BaseTest;
import Util.Driver;
import Util.TestConfig;
import com.google.gson.JsonObject;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.BoundingBox;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;




public class Scenario2Slider extends BaseTest {

@ParameterizedTest
@MethodSource("Util.LTCapability#getTestCapability")
    public void TestScenario2(JsonObject capability){
        Driver driver=null;
        Page page=null;
        try {
            driver=super.createConnection(capability);
            page=driver.getPage();

            page.navigate(TestConfig.TEST_URL); //Navigate to the URL
            page.setViewportSize(1900, 1050);

            page.locator("//a[text()='Drag & Drop Sliders']").click(); //click “Drag & Drop Sliders” link

            Locator sliderValue=page.locator("//input[@type='range' and @value='15']");
            Locator outputValue=page.locator("//output[@id='rangeSuccess']");

            //Moving Sliders on the page
            double slidemove=500;
            boolean Slidevalue=false;
            while(!Slidevalue){
                BoundingBox boundingBox=sliderValue.boundingBox();
                page.mouse().move(boundingBox.x+slidemove, boundingBox.y);
                page.mouse().down();
                slidemove +=5;
                page.mouse().move(boundingBox.x+slidemove, boundingBox.y);
                page.mouse().up();
                String updateOutputValue=outputValue.textContent();
                System.out.println("==="+slidemove +"--"+updateOutputValue);
                if(updateOutputValue.equals("95")) {
                    System.out.println("Test Passed: Output value is correctly updated to 95");
                    Slidevalue=true;
                } else if (updateOutputValue.equals("100")) {
                    System.out.println("Test Failed: Output reached to "+updateOutputValue +" , but expected is 95");
                    break;
                }
            }

            //Validating the page title
            if(page.title().equalsIgnoreCase("Selenium Grid Online | Run Selenium Test On Cloud")){
                System.out.println("Test Passed");

            } else {
                System.out.println("Test Failed");
            }

            super.closeConnection(driver);

        }catch (Exception e) {
           e.printStackTrace();
           if(driver!=null){
            super.setTestStatus("Failed", e.getMessage(), page);
           }
        }finally{
            if(driver!=null){
                super.closeConnection(driver);
            }
        }
    }
}
