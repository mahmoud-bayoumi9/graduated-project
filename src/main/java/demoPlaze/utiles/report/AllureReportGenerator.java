<<<<<<< HEAD
=======

>>>>>>> 5d6cdf008ee55d5d87a8e2f21ca53cb1101770e9
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
<<<<<<< HEAD
=======

        String absoluteResultsPath = AllureConstant.RESULT_FOLDER.toAbsolutePath().toString();
        String absoluteOutputPath = outputFolder.toAbsolutePath().toString();

        System.out.println("📂 Result Folder (Absolute): " + absoluteResultsPath);
        System.out.println("📂 Output Folder (Absolute): " + absoluteOutputPath);

        List<String> commandList = new ArrayList<>();
        commandList.add("cmd.exe");
        commandList.add("/c");
        commandList.add("allure");
        commandList.add("serve");
        commandList.add(absoluteResultsPath);
        if (isSingleFile) {
            commandList.add("--single-file");
        }

>>>>>>> 5d6cdf008ee55d5d87a8e2f21ca53cb1101770e9
        try {
            String allureExecutable = AllureBinaryManager.getExecutable().toAbsolutePath().toString();

<<<<<<< HEAD
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
=======
            processBuilder.environment().put("JAVA_HOME", "D:\\Java\\jdk-26");
            processBuilder.inheritIO();

            Process process = processBuilder.start();
            int exitCode = process.waitFor();

        } catch (Exception e) {
            System.err.println("Error is happened " + e.getMessage());
        }
    }

    public static void openReport(String reportFileName) {

        Path reportPath = AllureConstant.REPORT_PATH.resolve(reportFileName);
        switch (osutiles.getCurrentOs()) {
            case WINDOWS -> TerminalUtiles.executeTerminalCommand("cmd.exe", "/c", "start", "", reportPath.toString());
            case MAC, LINUX -> TerminalUtiles.executeTerminalCommand("open", reportPath.toString());
            default -> LogsManager.error("Opening Allure Report Is Not supported");
>>>>>>> 5d6cdf008ee55d5d87a8e2f21ca53cb1101770e9
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

<<<<<<< HEAD
    public static String renameReport(){
        String newFileName=AllureConstant.REPORT_PREFIX+ timeManager.getTimestamp()+AllureConstant.REPORT_EXTENSION;
        FileUtiles.renameFile(AllureConstant.REPORT_PATH.resolve(AllureConstant.INDEX_HTML).toString(),newFileName);
=======
    public static String renameReport() {
        String newFileName = AllureConstant.REPORT_PREFIX + timeManager.getTimestamp() + AllureConstant.REPORT_EXTENSION;
        String sourceFile = AllureConstant.REPORT_PATH.resolve(AllureConstant.INDEX_HTML).toString();
        FileUtiles.renameFile(sourceFile, newFileName);
>>>>>>> 5d6cdf008ee55d5d87a8e2f21ca53cb1101770e9
        return newFileName;
    }
    public static void copyHistory(){
        try {
<<<<<<< HEAD
            FileUtiles.copyDirectory(HISTORY_FOLDER.toString(),RESULT_HISTORY_FOLDER.toString());
        } catch (Exception e) {
            LogsManager.error(e.getMessage());
=======
            if (HISTORY_FOLDER.toFile().exists()) {
                FileUtils.copyDirectory(HISTORY_FOLDER.toFile(), RESULT_HISTORY_FOLDER.toFile());
            }
        } catch (IOException e) {
            LogsManager.error("Error " + e.getMessage());
>>>>>>> 5d6cdf008ee55d5d87a8e2f21ca53cb1101770e9
        }
    }
}