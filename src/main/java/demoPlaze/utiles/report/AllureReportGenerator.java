//package demoPlaze.utiles.report;
//
//import demoPlaze.FileUtiles;
//import demoPlaze.utiles.TerminalUtiles;
//import demoPlaze.utiles.datareader.propertyReader;
//import demoPlaze.utiles.logs.LogsManager;
//import demoPlaze.utiles.logs.timeManager;
//import demoPlaze.utiles.osutiles;
//import org.apache.commons.io.FileUtils;
//
//import java.io.IOException;
//import java.nio.file.Path;
//import java.util.ArrayList;
//import java.util.List;
//
//import static demoPlaze.utiles.report.AllureConstant.HISTORY_FOLDER;
//import static demoPlaze.utiles.report.AllureConstant.RESULT_HISTORY_FOLDER;
//
//public class AllureReportGenerator {
////    public static void generateReport(boolean isSingleFile) {
////        Path outputFolder = isSingleFile ? AllureConstant.REPORT_PATH : AllureConstant.FULL_REPORT_PATH;
////
////        System.out.println("📂 Result Folder: " + AllureConstant.RESULT_FOLDER.toAbsolutePath());
////        System.out.println("📂 Output Folder: " + outputFolder.toAbsolutePath());
////
////        // بناء الأمر بالترتيب المتوافق مع نسخة Allure الحديثة لديك
////        List<String> commandList = new ArrayList<>();
////        commandList.add("cmd.exe");
////        commandList.add("/c");
////        commandList.add("allure");
////        commandList.add("generate");
////        commandList.add("-o");
////        commandList.add(outputFolder.toString());
////        commandList.add("test-output/allure-results");
////
////        if (isSingleFile) {
////            commandList.add("--single-file");
////        }
////
////        System.out.println("🤖 جاري تنفيذ أمر Allure تلقائياً لإنشاء الـ HTML...");
////
////        try {
////            ProcessBuilder processBuilder = new ProcessBuilder(commandList);
////
////            // 🌟 السطر السحري: إجبار الـ ProcessBuilder على رؤية مسار الجافا الصحيح حتى لو لم يكن معرفاً في الويندوز
////            processBuilder.environment().put("JAVA_HOME", "D:\\Java\\jdk-26");
////
////            processBuilder.inheritIO(); // طباعة المخرجات في Console الـ IntelliJ مباشرة
////            Process process = processBuilder.start();
////            int exitCode = process.waitFor();
////
////            if (exitCode != 0) {
////                System.err.println("❌ فشل توليد التقرير التلقائي. كود الخطأ: " + exitCode);
////            } else {
////                System.out.println("🟢 تم توليد تقرير الـ HTML تلقائياً بنجاح!");
////            }
////        } catch (Exception e) {
////            System.err.println("❌ حدث خطأ أثناء تشغيل أمر Allure: " + e.getMessage());
////        }
////    }
////    public static void generateReport(boolean isSingleFile) {
////        Path outputFolder = isSingleFile ? AllureConstant.REPORT_PATH : AllureConstant.FULL_REPORT_PATH;
////
////        // طباعة المسارات للتأكد منها في الـ Console قبل التنفيذ
////        System.out.println("📂 Result Folder: " + AllureConstant.RESULT_FOLDER.toAbsolutePath());
////        System.out.println("📂 Output Folder: " + outputFolder.toAbsolutePath());
////
////        // بناء الأمر بالصيغة القياسية للويندوز لضمان التشغيل الفوري دون الاعتماد على باينري معطوب
////        List<String> commandList = new ArrayList<>();
////
////        // استدعاء موجه الأوامر cmd لتنفيذ الأمر بأمان في بيئة الويندوز
////        commandList.add("cmd.exe");
////        commandList.add("/c");
////        commandList.add("allure"); // أو ضع هنا المسار المطلق إذا كنت تستخدم باينري محلي
////        commandList.add("generate");
////        commandList.add(AllureConstant.RESULT_FOLDER.toString());
////        commandList.add("-o");
////        commandList.add(outputFolder.toString());
////        commandList.add("--clean");
////
////        if (isSingleFile) {
////            commandList.add("--single-file");
////        }
////
////        System.out.println("🤖 جاري تنفيذ أمر Allure في الـ Terminal لإنشاء الـ HTML...");
////
////        // إرسال المصفوفة لكلاس TerminalUtiles المعدل
////        TerminalUtiles.executeTerminalCommand(commandList.toArray(new String[0]));
////    }
//
////    public static void generateReport(boolean isSingleFile){
////        Path outputFolder = isSingleFile ? AllureConstant.REPORT_PATH : AllureConstant.FULL_REPORT_PATH;
////
////        // تصحيح الأندرسكور إلى داش "-o"
////        List<String> command = new ArrayList<>(List.of(
////                AllureBinaryManager.getExecutable().toString(),
////                "generate",
////                AllureConstant.RESULT_FOLDER.toString(),
////                "-o", outputFolder.toString(),
////                "--clean"
////        ));
////
////        if(isSingleFile){
////            command.add("--single-file");
////        }
////
////        // نقل سطر التنفيذ هنا ليعمل في الحالتين (سواء عادي أو Single File)
////        System.out.println("🤖 جاري تنفيذ أمر Allure في الـ Terminal لإنشاء الـ HTML...");
////        TerminalUtiles.executeTerminalCommand(command.toArray(new String[0]));
////    }
//public static void generateReport(boolean isSingleFile) {
//    // استخدام المسار المطلق المعرف في AllureConstant لضمان الوصول للمجلد الصحيح تلقائياً
//    Path outputFolder = isSingleFile ? AllureConstant.REPORT_PATH : AllureConstant.FULL_REPORT_PATH;
//
//    String absoluteResultsPath = AllureConstant.RESULT_FOLDER.toAbsolutePath().toString();
//    String absoluteOutputPath = outputFolder.toAbsolutePath().toString();
//
//    System.out.println("📂 Result Folder (Absolute): " + absoluteResultsPath);
//    System.out.println("📂 Output Folder (Absolute): " + absoluteOutputPath);
//
//    List<String> commandList = new ArrayList<>();
//    commandList.add("cmd.exe");
//    commandList.add("/c");
//    commandList.add("allure");
//    commandList.add("generate");
//    commandList.add("-o");
//    commandList.add(absoluteOutputPath); // المخرج أولاً
//    commandList.add(absoluteResultsPath); // المدخلات (JSON) ثانياً
//
//    if (isSingleFile) {
//        commandList.add("--single-file");
//    }
//
//    System.out.println("🤖 جاري تنفيذ أمر Allure تلقائياً...");
//
//    try {
//        ProcessBuilder processBuilder = new ProcessBuilder(commandList);
//        // تمرير الجافا للبيئة
//        processBuilder.environment().put("JAVA_HOME", "D:\\Java\\jdk-26");
//        processBuilder.inheritIO();
//
//        Process process = processBuilder.start();
//        int exitCode = process.waitFor();
//
//        if (exitCode != 0) {
//            System.err.println("❌ فشل توليد التقرير التلقائي. كود الخطأ: " + exitCode);
//        } else {
//            System.out.println("🟢 تم توليد تقرير الـ HTML تلقائياً بنجاح!");
//        }
//    } catch (Exception e) {
//        System.err.println("❌ حدث خطأ أثناء تشغيل أمر Allure: " + e.getMessage());
//    }
//}
//    public static void openReport(String reportFileName){
//        // تصحيح الشرط بعلامة التعجب (!) ليفتح التقرير عندما تكون القيمة true
//        if(!propertyReader.getProperty("openAllureReportAfterExecution").equalsIgnoreCase("true")) {
//            System.out.println("ℹ️ تم إلغاء فتح التقرير تلقائياً بناءً على إعدادات الـ Properties.");
//            return;
//        }
//
//        Path reportPath = AllureConstant.REPORT_PATH.resolve(reportFileName);
//        System.out.println("🌐 جاري فتح صفحة الـ HTML التفاعلية من المسار: " + reportPath.toAbsolutePath());
//
//        switch (osutiles.getCurrentOs()){
//            case WINDOWS -> TerminalUtiles.executeTerminalCommand("cmd.exe", "/c", "start", "", reportPath.toString());
//            case MAC, LINUX -> TerminalUtiles.executeTerminalCommand("open", reportPath.toString());
//            default -> LogsManager.error("Opening Allure Report Is Not supported");
//        }
//    }
//
//    public static String renameReport(){
//        String newFileName = AllureConstant.REPORT_PREFIX + timeManager.getTimestamp() + AllureConstant.REPORT_EXTENSION;
//
//        // تأكد أن الميثود تعمل رينام لملف index.html المولد داخل الـ REPORT_PATH
//        String sourceFile = AllureConstant.REPORT_PATH.resolve(AllureConstant.INDEX_HTML).toString();
//        System.out.println("📝 جاري إعادة تسمية الملف من index.html إلى: " + newFileName);
//
//        FileUtiles.renameFile(sourceFile, newFileName);
//        return newFileName;
//    }
//
//    public static void copyHistor(){
//        try{
//            if (HISTORY_FOLDER.toFile().exists()) {
//                FileUtils.copyDirectory(HISTORY_FOLDER.toFile(), RESULT_HISTORY_FOLDER.toFile());
//                System.out.println("📊 تم نسخ الـ History بنجاح لتوليد الـ Trend Chart.");
//            }
//        } catch (IOException e) {
//            LogsManager.error("خطأ أثناء نسخ الـ History: " + e.getMessage());
//        }
//    }
//}

package demoPlaze.utiles.report;

import demoPlaze.FileUtiles;
import demoPlaze.utiles.TerminalUtiles;
import demoPlaze.utiles.datareader.propertyReader;
import demoPlaze.utiles.logs.LogsManager;
import demoPlaze.utiles.logs.timeManager;
import demoPlaze.utiles.osutiles;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static demoPlaze.utiles.report.AllureConstant.HISTORY_FOLDER;
import static demoPlaze.utiles.report.AllureConstant.RESULT_HISTORY_FOLDER;

public class AllureReportGenerator {

    public static void generateReport(boolean isSingleFile) {
        Path outputFolder = isSingleFile ? AllureConstant.REPORT_PATH : AllureConstant.FULL_REPORT_PATH;

        // 🌟 تحويل المسارات إلى مسارات مطلقة آمنة 100% للويندوز
        String absoluteResultsPath = AllureConstant.RESULT_FOLDER.toAbsolutePath().toString();
        String absoluteOutputPath = outputFolder.toAbsolutePath().toString();

        System.out.println("📂 Result Folder (Absolute): " + absoluteResultsPath);
        System.out.println("📂 Output Folder (Absolute): " + absoluteOutputPath);

        // بناء الأمر بالترتيب الصحيح المتوافق مع نسختك الحديثة
        List<String> commandList = new ArrayList<>();
        commandList.add("cmd.exe");
        commandList.add("/c");
        commandList.add("allure");
        commandList.add("serve"); // استخدام serve بدلاً من generate
        commandList.add(absoluteResultsPath);    // المدخلات (مسار الـ JSON المطلق) ثانياً

        if (isSingleFile) {
            commandList.add("--single-file");
        }

        System.out.println("🤖 جاري تنفيذ أمر Allure تلقائياً لإنشاء الـ HTML...");

        try {
            ProcessBuilder processBuilder = new ProcessBuilder(commandList);

            // تمرير مسار الجافا لبيئة التشغيل الفرعية ليتعرف على أمر allure
            processBuilder.environment().put("JAVA_HOME", "D:\\Java\\jdk-26");
            processBuilder.inheritIO(); // طباعة المخرجات والحلول مباشرة داخل Console الـ IntelliJ

            Process process = processBuilder.start();
            int exitCode = process.waitFor();

            if (exitCode != 0) {
                System.err.println("❌ فشل توليد التقرير التلقائي. كود الخطأ: " + exitCode);
            } else {
                System.out.println("🟢 تم توليد تقرير الـ HTML تلقائياً بنجاح وبدون أخطاء تضارب!");
            }
        } catch (Exception e) {
            System.err.println("❌ حدث خطأ أثناء تشغيل أمر Allure: " + e.getMessage());
        }
    }

    public static void openReport(String reportFileName) {
        if (!propertyReader.getProperty("openAllureReportAfterExecution").equalsIgnoreCase("true")) {
            System.out.println("ℹ️ تم إلغاء فتح التقرير تلقائياً بناءً على إعدادات الـ Properties.");
            return;
        }

        Path reportPath = AllureConstant.REPORT_PATH.resolve(reportFileName);
        System.out.println("🌐 جاري فتح صفحة الـ HTML من المسار: " + reportPath.toAbsolutePath());

        switch (osutiles.getCurrentOs()) {
            case WINDOWS -> TerminalUtiles.executeTerminalCommand("cmd.exe", "/c", "start", "", reportPath.toString());
            case MAC, LINUX -> TerminalUtiles.executeTerminalCommand("open", reportPath.toString());
            default -> LogsManager.error("Opening Allure Report Is Not supported");
        }
    }

    public static String renameReport() {
        String newFileName = AllureConstant.REPORT_PREFIX + timeManager.getTimestamp() + AllureConstant.REPORT_EXTENSION;
        String sourceFile = AllureConstant.REPORT_PATH.resolve(AllureConstant.INDEX_HTML).toString();
        System.out.println("📝 جاري إعادة تسمية الملف من index.html إلى: " + newFileName);

        FileUtiles.renameFile(sourceFile, newFileName);
        return newFileName;
    }

    public static void copyHistor() {
        try {
            if (HISTORY_FOLDER.toFile().exists()) {
                FileUtils.copyDirectory(HISTORY_FOLDER.toFile(), RESULT_HISTORY_FOLDER.toFile());
                System.out.println("📊 تم نسخ الـ History بنجاح لتوليد الـ Trend Chart.");
            }
        } catch (IOException e) {
            LogsManager.error("خطأ أثناء نسخ الـ History: " + e.getMessage());
        }
    }
}