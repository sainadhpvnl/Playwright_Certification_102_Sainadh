package Base;

import com.google.gson.JsonObject;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import java.net.URLEncoder;

public abstract class BaseTest {
    
    protected Driver createConnection(JsonObject cap) throws Exception{
        try {
            Playwright playwright = Playwright.create();
            String caps=URLEncoder.encode(cap.toString(), "utf-8");
            String cdurl="wss://cdp.lambdatest.com/playwright?capabilities="+caps;
            Browser browser=playwright.chromium().connect(cdurl);
            Page Page  = browser.newPage();
            return new Driver(browser,Page);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    protected void closeConnection(Driver driver){
        driver.getPage().close();
        driver.getBrowser().close();
    }

    protected void setTestStatus(String status, String remark, Page page){
        page.evaluate("() => {}","lambdatest_action: {\"action\": \"setTestStatus\" ,\"arguments\" : { \"status\" : \""+status + "\",\"remark\": \""+ remark+"\"}}");
    }
}
