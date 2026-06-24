package demoPlaze.utiles.report;

import demoPlaze.FileUtiles;
import demoPlaze.utiles.TerminalUtiles;
import demoPlaze.utiles.logs.LogsManager;
import demoPlaze.utiles.logs.timeManager;
import demoPlaze.utiles.osutiles;
import org.apache.commons.compress.utils.OsgiUtils;
import org.apache.commons.compress.utils.TimeUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static demoPlaze.utiles.report.AllureConstant.HISTORY_FOLDER;
import static demoPlaze.utiles.report.AllureConstant.RESULT_HISTORY_FOLDER;

public class AllureReportGenerator {
    public static void generateReports(boolean isSingleFile) {
        Path outputFolder = isSingleFile ? AllureConstant.REPORT_PATH : AllureConstant.FULL_REPORT_PATH;
        try {
            String allureExecutable = AllureBinaryManager.getExecutable().toAbsolutePath().toString();

            List<String> command = new ArrayList<>(List.of(
                    "cmd.exe", "/c", allureExecutable, "generate",
                    AllureConstant.RESULT_FOLDER.toAbsolutePath().toString(),
                    "-o", outputFolder.toAbsolutePath().toString(),
                    "--clean"
            ));
            if (isSingleFile) command.add("--single-file");

            Process process = new ProcessBuilder(command).start();
            process.waitFor(); // الانتظار الإجباري حتى انتهاء التوليد
        } catch (Exception e) {
            System.err.println("Report Generation Failed: " + e.getMessage());
        }
    }

    public static void openReport() {
        try {
            String allureExecutable = AllureBinaryManager.getExecutable().toAbsolutePath().toString();

            // فتح السيرفر في نافذة CMD مستقلة تماماً ومستمرة بالخيار /k
            ProcessBuilder builder = new ProcessBuilder(
                    "cmd.exe", "/c", "start", "cmd.exe", "/k",
                    allureExecutable, "serve", AllureConstant.RESULT_FOLDER.toAbsolutePath().toString()
            );
            builder.start();
        } catch (Exception e) {
            System.err.println("Open Report Failed: " + e.getMessage());
        }
    }
//    public  static void generateReports(boolean isSingleFile){
//        Path outputFolder=isSingleFile?AllureConstant.REPORT_PATH:AllureConstant.FULL_REPORT_PATH;
//        List<String> command=new ArrayList<>(List.of(
//                AllureBinaryManager.getExecutable().toString(),"generate",AllureConstant.RESULT_FOLDER.toString(),
//                "-o",outputFolder.toString(),
//                "--clean"
//        ));
//        if(isSingleFile) command.add("--single-file");
//        TerminalUtiles.executeTerminalCommand(command.toArray(new String[0]));
//    }
//    public static void openReport(String reportFileName){
//        Path reportPath=AllureConstant.REPORT_PATH.resolve(reportFileName);
//        switch (osutiles.getCurrentOs()){
//            case WINDOWS -> TerminalUtiles.executeTerminalCommand("cmd.exe","/c","start",reportPath.toString());
//            case MAC,LINUX ->TerminalUtiles.executeTerminalCommand("open",reportPath.toString());
//            default -> LogsManager.error("open allure report is not support in this os");
//        }
//public static void openReport(boolean isSingleFile) {
//    Path reportFolder = isSingleFile ? AllureConstant.REPORT_PATH : AllureConstant.FULL_REPORT_PATH;
//
//    // تشغيل أمر allure open ليقوم بفتح التقرير عبر سيرفر محلي بأمان
//    List<String> command = new ArrayList<>(List.of(
//            AllureBinaryManager.getExecutable().toString(), "open", reportFolder.toString()
//    ));
//
//    TerminalUtiles.executeTerminalCommand(command.toArray(new String[0]));
//
//
//    }

    public static String renameReport(){
        String newFileName=AllureConstant.REPORT_PREFIX+ timeManager.getTimestamp()+AllureConstant.REPORT_EXTENSION;
        FileUtiles.renameFile(AllureConstant.REPORT_PATH.resolve(AllureConstant.INDEX_HTML).toString(),newFileName);
        return newFileName;
    }
    public static void copyHistory(){
        try {
            FileUtiles.copyDirectory(HISTORY_FOLDER.toString(),RESULT_HISTORY_FOLDER.toString());
        } catch (Exception e) {
            LogsManager.error(e.getMessage());
        }
    }
}