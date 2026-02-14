package Base;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;

public class Driver {

    private Browser browser;
    private Page page;

    public Driver(Browser browser, Page page) {
        this.browser = browser;
           this.page = page;
    }
    
    public Browser getBrowser() {
        return browser;
    }
   
    public Page getPage() {
        return page;
    }
   
    


    
    
}
