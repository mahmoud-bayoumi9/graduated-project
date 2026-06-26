package demoPlaze.utiles.report;
import demoPlaze.FileUtiles;
import demoPlaze.utiles.logs.LogsManager;
import demoPlaze.utiles.logs.timeManager;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import static demoPlaze.utiles.report.AllureConstant.HISTORY_FOLDER;
import static demoPlaze.utiles.report.AllureConstant.RESULT_HISTORY_FOLDER;

public class AllureReportGenerator {

    public static void generateReport(boolean isSingleFile) {
        Path outputFolder = isSingleFile ? demoPlaze.utiles.report.AllureConstant.REPORT_PATH : demoPlaze.utiles.report.AllureConstant.FULL_REPORT_PATH;
        try {
            String allureExecutable = demoPlaze.utiles.report.AllureBinaryManager.getExecutable().toAbsolutePath().toString();

            List<String> command = new ArrayList<>(List.of(
                    "cmd.exe", "/c", allureExecutable, "generate",
                    demoPlaze.utiles.report.AllureConstant.RESULT_FOLDER.toAbsolutePath().toString(),
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
            String allureExecutable = demoPlaze.utiles.report.AllureBinaryManager.getExecutable().toAbsolutePath().toString();

            // فتح السيرفر في نافذة CMD مستقلة تماماً ومستمرة بالخيار /k
            ProcessBuilder builder = new ProcessBuilder(
                    "cmd.exe", "/c", "start", "cmd.exe", "/k",
                    allureExecutable, "serve", demoPlaze.utiles.report.AllureConstant.RESULT_FOLDER.toAbsolutePath().toString()
            );
            builder.start();
        } catch (Exception e) {
            System.err.println("Open Report Failed: " + e.getMessage());
        }
    }

    public static String renameReport() {
        String newFileName = demoPlaze.utiles.report.AllureConstant.REPORT_PREFIX + timeManager.getTimestamp() + demoPlaze.utiles.report.AllureConstant.REPORT_EXTENSION;

        // تم إصلاح نقطة السنتكس وربطها بـ demoPlaze بالكامل
        String sourcePath = demoPlaze.utiles.report.AllureConstant.REPORT_PATH.resolve(demoPlaze.utiles.report.AllureConstant.INDEX_HTML).toString();

        FileUtiles.renameFile(sourcePath, newFileName);
        return newFileName;
    }

    public static void copyHistory() {
        try {
            FileUtiles.copyDirectory(HISTORY_FOLDER.toString(), RESULT_HISTORY_FOLDER.toString());
        } catch (Exception e) {
            LogsManager.error("Failed to copy history: " + e.getMessage());
        }
    }
}