package Base;

import com.google.gson.JsonObject;
import com.tngtech.java.junit.dataprovider.DataProvider;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class LTCapability {

    @DataProvider
    public static Object[] getTestCapability(){
        JsonObject capabilities= new JsonObject();
        JsonObject ltOptions= new JsonObject();

        String user="sainadhpvnl";
        String accessKey="LT_zUtXB44POAC2Kow2bsl8g2B5f84qdvnBJzQp4Q4o27GMFsa";

        // Getting time stamp to print test name of HyperExecute platform
        SimpleDateFormat sdf= new SimpleDateFormat("HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
        String timeValue=sdf.format(new Date());

        capabilities.addProperty("browserName", "Chrome");
        capabilities.addProperty("browserVersion", "latest");
        ltOptions.addProperty("platform", "Windows 10");
        ltOptions.addProperty("name", "Sainadh Lambda Test" +timeValue);
        ltOptions.addProperty("build", "Sainadh's Playwright 102 Cert run1");
        ltOptions.addProperty("console", true);
        ltOptions.addProperty("network", true);
        ltOptions.addProperty("visual", true);
        ltOptions.addProperty("video", true);
        ltOptions.addProperty("accessibility", true);
        ltOptions.addProperty("geoLocation", "IN");
        ltOptions.addProperty("timezone", "Kolkata");
        ltOptions.addProperty("user", user);
        ltOptions.addProperty("accessKey", accessKey);
        
        capabilities.add("LT:Options", ltOptions);

        return new Object[]{capabilities};
        


    }
    
}
