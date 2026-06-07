package demoPlaze.utiles;

public class osutiles {
    public enum OPERATING{WINDOWS,MAC,LINUX,OTHER}
    public  static  OPERATING getCurrentOs(){
        String os=System.getProperty("os.name").toLowerCase();
        if(os.contains("win")) return OPERATING.WINDOWS;
        if (os.contains("mac")) return OPERATING.MAC;
        if (os.contains("nux")) return OPERATING.LINUX;
        return OPERATING.OTHER;
    }
}
