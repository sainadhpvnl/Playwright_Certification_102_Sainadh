package Util;

import com.google.gson.JsonObject;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.stream.Stream;

public class LTCapability {


    public static Object[] getTestCapability(){
        JsonObject capabilities1= new JsonObject();
        JsonObject ltOptions1= new JsonObject();

        JsonObject capabilities2= new JsonObject();
        JsonObject ltOptions2= new JsonObject();

        String user="sainadhpvnl";
        String accessKey="LT_zUtXB44POAC2Kow2bsl8g2B5f84qdvnBJzQp4Q4o27GMFsa";

        // Getting time stamp to print test name of HyperExecute platform
        SimpleDateFormat sdf= new SimpleDateFormat("HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
        String timeValue=sdf.format(new Date());

//        //Windows 10 Chrome
        capabilities1.addProperty("browserName", "Chrome");
        capabilities1.addProperty("browserVersion", "latest");
        ltOptions1.addProperty("platform", "Windows 10");
        ltOptions1.addProperty("name", "Sainadh's Lambda Test" +timeValue);
        ltOptions1.addProperty("build", "Sainadh's Playwright 102 Cert run1_Hybrid_Chrome");
        ltOptions1.addProperty("user", user);
        ltOptions1.addProperty("accessKey", accessKey);
        ltOptions1.addProperty("console", true);
        ltOptions1.addProperty("network", true);
        ltOptions1.addProperty("visual", true);
        ltOptions1.addProperty("video", true);
        ltOptions1.addProperty("accessibility", true);
        ltOptions1.addProperty("terminal", true);
        ltOptions1.addProperty("geoLocation", "IN");
        ltOptions1.addProperty("timezone", "Kolkata");
        capabilities1.add("LT:Options", ltOptions1);


//        //Linux Firefox
//        capabilities2.addProperty("browserName", "Firefox");
//        capabilities2.addProperty("browserVersion", "latest");
//        ltOptions2.addProperty("platform", "Linux");
//        ltOptions2.addProperty("name", "Sainadh's Lambda Test" +timeValue);
//        ltOptions2.addProperty("build", "Sainadh's Playwright 102 Cert run1_Hybrid_Firefox");
//        ltOptions2.addProperty("user", user);
//        ltOptions2.addProperty("accessKey", accessKey);
//        ltOptions2.addProperty("console", true);
//        ltOptions2.addProperty("network", true);
//        ltOptions2.addProperty("visual", true);
//        ltOptions2.addProperty("video", true);
//        ltOptions2.addProperty("accessibility", true);
//        ltOptions2.addProperty("geoLocation", "IN");
//        ltOptions2.addProperty("timezone", "Kolkata");
//        capabilities2.add("LT:Options", ltOptions2);

        return new Object[]{capabilities1,capabilities2};
        


    }
    
}
