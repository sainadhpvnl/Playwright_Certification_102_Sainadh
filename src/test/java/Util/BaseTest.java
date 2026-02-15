package Util;

import com.google.gson.JsonObject;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import java.net.URLEncoder;

public class BaseTest {
    
    public Driver createConnection(JsonObject cap) throws Exception{
        try {
            Playwright playwright = Playwright.create();
            String caps=URLEncoder.encode(cap.toString(), "utf-8");
            String cdurl="wss://cdp.lambdatest.com/playwright?capabilities="+caps;
            Browser browser;
            browser = playwright.chromium().connect(cdurl).browserType().launch(new BrowserType.LaunchOptions().setSlowMo(1000));
//            String browserName="firefox";
//            switch(browserName.toLowerCase()){
//
//                 case "firefox":
//                    browser = playwright.firefox().connect(cdurl).browserType().launch(new BrowserType.LaunchOptions().setSlowMo(1000));;
//                    break;
//                case "chrome":
//                case "chromium":
//                case "microsoftedge":
//                    browser = playwright.chromium().connect(cdurl).browserType().launch(new BrowserType.LaunchOptions().setSlowMo(1000));;
//                    break;
//                case "webkit":
//                    browser = playwright.webkit().connect(cdurl).browserType().launch(new BrowserType.LaunchOptions().setSlowMo(1000));;
//                    break;
//                default:
//                    throw new IllegalArgumentException("Unspported Browser "+browserName);

//            }

            Page Page  = browser.newPage();
            return new Driver(browser,Page);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void closeConnection(Driver driver){
        driver.getPage().close();
        driver.getBrowser().close();
    }

    public void setTestStatus(String status, String remark, Page page){
        page.evaluate("() => {}","lambdatest_action: {\"action\": \"setTestStatus\" ,\"arguments\" : { \"status\" : \""+status + "\",\"remark\": \""+ remark+"\"}}");
    }
}
