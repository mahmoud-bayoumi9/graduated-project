package demoPlaze.utiles.logs;

import java.util.logging.Level;
import java.util.logging.Logger;
public class LogsManager {
    private static Logger getLoggerInstance() {
        String className = Thread.currentThread().getStackTrace()[3].getClassName();
        return Logger.getLogger(className);
    }
    public static void info(String... message) {
        getLoggerInstance().info(String.join(" ",message));
    }
    public static void warn(String... message) {
        getLoggerInstance().warning(String.join(" ",message));
    }
    public static void error(String... message) {
        getLoggerInstance().severe(String.join(" ",message));
    }
    public static void fatal(String... message) {

        getLoggerInstance().log(Level.SEVERE, "FATAL: " + message);
    }
    public static void debug(String... message) {
        getLoggerInstance().config(String.join(" ",message));
    }
}