package demoPlaze.utiles.logs;

public class timeManager {
    public static String getTimestamp(){
        return new java.text.SimpleDateFormat("yyyyMMdd_HHmmss").format(new java.util.Date());
    }
}
