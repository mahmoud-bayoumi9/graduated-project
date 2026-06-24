
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

        try {
            ProcessBuilder processBuilder = new ProcessBuilder(commandList);

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
        }
    }

    public static String renameReport() {
        String newFileName = AllureConstant.REPORT_PREFIX + timeManager.getTimestamp() + AllureConstant.REPORT_EXTENSION;
        String sourceFile = AllureConstant.REPORT_PATH.resolve(AllureConstant.INDEX_HTML).toString();
        FileUtiles.renameFile(sourceFile, newFileName);
        return newFileName;
    }

    public static void copyHistor() {
        try {
            if (HISTORY_FOLDER.toFile().exists()) {
                FileUtils.copyDirectory(HISTORY_FOLDER.toFile(), RESULT_HISTORY_FOLDER.toFile());
            }
        } catch (IOException e) {
            LogsManager.error("Error " + e.getMessage());
        }
    }
}