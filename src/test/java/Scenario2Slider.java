import Base.BaseTest;
import Base.Driver;
import Base.LTCapability;
import com.google.gson.JsonObject;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.BoundingBox;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(DataProviderRunner.class)
public class Scenario2Slider extends BaseTest{

    @Test
    @UseDataProvider(value="getTestCapability",location = LTCapability.class)
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
            double slidemove=0;
            boolean Slidevalue=false;
            while(!Slidevalue){
                BoundingBox boundingBox=sliderValue.boundingBox();
                page.mouse().move(boundingBox.x+slidemove, boundingBox.y);
                page.mouse().down();
                slidemove +=10; //15 19 15
                page.mouse().move(boundingBox.x+slidemove, boundingBox.y);

                page.mouse().up();
                String updateOutputValue=outputValue.textContent();
                System.out.println("==="+slidemove +"--"+updateOutputValue);
                if(updateOutputValue.equals("95")) {
                    Slidevalue=true;
                }
            }

            String updateOutputValue=outputValue.textContent();
            System.out.println("Updated Output Value :"+updateOutputValue);

            if(updateOutputValue.equals("95")){
                System.out.println("Test Passed: Output value is correctly updated to 95");
            }else{
                System.out.println("Test Failed: Output value is "+updateOutputValue +" , but expected is 95");
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
