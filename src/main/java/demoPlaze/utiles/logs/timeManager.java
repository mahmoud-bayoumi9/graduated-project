package demoPlaze.utiles.logs;

public class timeManager {
    public static String getTimestamp(){
        // "yyyyMMdd_HHmmss" بتطلعلك الوقت بالشكل ده مثلاً: 20260530_015812
        return new java.text.SimpleDateFormat("yyyyMMdd_HHmmss").format(new java.util.Date());
    }
}
