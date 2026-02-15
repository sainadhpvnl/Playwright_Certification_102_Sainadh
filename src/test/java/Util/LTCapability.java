package Util;

import com.google.gson.JsonObject;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.stream.Stream;

public class LTCapability {


    public static Stream<JsonObject> getTestCapability(){
        JsonObject capabilities= new JsonObject();
        JsonObject ltOptions= new JsonObject();

        String user="sainadhpvnl";
        String accessKey="LT_zUtXB44POAC2Kow2bsl8g2B5f84qdvnBJzQp4Q4o27GMFsa";

        // Getting time stamp to print test name of HyperExecute platform
        SimpleDateFormat sdf= new SimpleDateFormat("HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
        String timeValue=sdf.format(new Date());

        capabilities.addProperty("browserName", "Chrome");
        // Browsers allowed: `Chrome`, `MicrosoftEdge`, `pw-chromium`,
        // `pw-firefox` and `pw-webkit`
        capabilities.addProperty("browserVersion", "latest");
        ltOptions.addProperty("platform", "Windows 10");
        ltOptions.addProperty("name", "Sainadh's Lambda Test" +timeValue);
        ltOptions.addProperty("build", "Sainadh's Playwright 102 Cert run1_Hybrid_Check1");
        ltOptions.addProperty("user", user);
        ltOptions.addProperty("accessKey", accessKey);
        ltOptions.addProperty("console", true);
        ltOptions.addProperty("network", true);
        ltOptions.addProperty("visual", true);
        ltOptions.addProperty("video", true);
        ltOptions.addProperty("accessibility", true);
        ltOptions.addProperty("geoLocation", "IN");
        ltOptions.addProperty("timezone", "Kolkata");

        
        capabilities.add("LT:Options", ltOptions);

        return Stream.of(capabilities);
        


    }
    
}
