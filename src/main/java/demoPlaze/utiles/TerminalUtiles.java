//package demoPlaze.utiles;
//
//import demoPlaze.utiles.logs.LogsManager;
//
//public class TerminalUtiles {
//    public static void executeTerminalCommand(String...commandParts){
//        try {
//            Process process=Runtime.getRuntime().exec(commandParts);
//            int exitCode=process.waitFor();
//            if(exitCode!=0){
//                LogsManager.error("command failed with exitCode "+exitCode);
//            }
//        } catch (Exception e) {
//            LogsManager.error(e.getMessage());
//        }
//    }
//}
package demoPlaze.utiles;

import demoPlaze.utiles.logs.LogsManager;
import java.io.IOException;

public class TerminalUtiles {
    public static void executeTerminalCommand(String... commandParts) {
        try {
            // استخدام ProcessBuilder بدلاً من Runtime.getRuntime().exec()
            ProcessBuilder processBuilder = new ProcessBuilder(commandParts);

            // هامة جداً: تقوم بتوجيه المخرجات والأخطاء إلى الـ Console لتجنب تجمّد البرنامج
            processBuilder.inheritIO();

            Process process = processBuilder.start();

            // الانتظار حتى ينتهي الأمر تماماً
            int exitCode = process.waitFor();

            if (exitCode != 0) {
                LogsManager.error("Command failed with exitCode " + exitCode);
            } else {
                LogsManager.info("Command executed successfully!");
            }
        } catch (IOException | InterruptedException e) {
            LogsManager.error("Exception during command execution: " + e.getMessage());
            Thread.currentThread().interrupt(); // إعادة تعيين حالة الـ interrupt في حال المقاطعة
        }
    }
}