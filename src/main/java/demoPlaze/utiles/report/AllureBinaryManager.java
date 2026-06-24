package demoPlaze.utiles.report;

import demoPlaze.utiles.TerminalUtiles;
import demoPlaze.utiles.logs.LogsManager;
import demoPlaze.utiles.osutiles;
import org.jsoup.Jsoup;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class AllureBinaryManager {

    public static void downloadAndExtract() {
        try {
            String version = lazyLoader.version;
            Path extractionDir = Paths.get(AllureConstant.EXTRACTION_DIR.toString(), "allure-" + version);
            if (Files.exists(extractionDir) && Files.exists(getExecutable())) {
                LogsManager.info("Allure binaries already exist at: " + extractionDir);
                return;
            }
            if (!osutiles.getCurrentOs().equals(osutiles.OPERATING.WINDOWS)) {
                TerminalUtiles.executeTerminalCommand("chmod", "u+x", AllureConstant.USER_DIR.toString());
            }

            LogsManager.info("⏳ Starting Allure binaries download for version: " + version);
            Path zipPath = lazyLoader.downloadZip(version);

            LogsManager.info("📦 Extracting Allure binaries...");
            lazyLoader.extractZip(zipPath);
            LogsManager.info("✨ Allure Binaries downloaded and extracted successfully.");
            if (!osutiles.getCurrentOs().equals(osutiles.OPERATING.WINDOWS)) {
                Path executable = getExecutable();
                if (Files.exists(executable)) {
                    TerminalUtiles.executeTerminalCommand("chmod", "u+x", executable.toString());
                }
            }
            try {
                Optional<Path> zipFile = Files.list(AllureConstant.EXTRACTION_DIR)
                        .filter(p -> p.toString().endsWith(".zip"))
                        .findFirst();
                if (zipFile.isPresent()) {
                    Files.deleteIfExists(zipFile.get());
                    LogsManager.info("Cleaned up temporary Allure zip file.");
                }
            } catch (IOException e) {
                LogsManager.error("Failed to delete temporary zip file: " + e.getMessage());
            }

        } catch (Exception e) {
            LogsManager.error("Critical error in Allure download/extract process: " + e.getMessage());
            e.printStackTrace();
        }
    }
//    public static Path getExecutable() {
//        String version = lazyLoader.version;
//        Path binaryPath = Paths.get(AllureConstant.EXTRACTION_DIR.toString(), "allure-" + version, "bin", "allure");
//
//        if (osutiles.getCurrentOs() == osutiles.OPERATING.WINDOWS) {
//            return binaryPath.resolveSibling(binaryPath.getFileName().toString() + ".bat");
//        }
//        return binaryPath;
//    }
public static Path getExecutable() {
    String version = lazyLoader.version;

    // بناء اسم الملف التنفيذي بناءً على نظام التشغيل
    String executableName = (osutiles.getCurrentOs() == osutiles.OPERATING.WINDOWS) ? "allure.bat" : "allure";

    // إرجاع المسار المطلق الصحيح والدقيق بنسبة 100%
    return Paths.get(AllureConstant.EXTRACTION_DIR.toString(), "allure-" + version, "bin", executableName);
}
    private static class lazyLoader {
        private static final String version = resolveVersion();
        private static String resolveVersion() {
            // إرجاع إصدار ثابت مباشرة لمنع أي اتصال خارجي بالإنترنت يبطئ التيست
            return "2.24.0";
        }
//        private static String resolveVersion() {
//            try {
//                String url = Jsoup.connect("https://api.github.com/repos/allure-framework/allure2/releases/latest")
//                        .followRedirects(true).execute().url().toString();
//                return url.split("/tag/")[1];
//            } catch (Exception e) {
//                LogsManager.error("Unable to resolve latest Allure version from GitHub: " + e.getMessage());
//                throw new IllegalStateException("Unable to resolve Allure version", e);
//            }
//        }
//    }
    private static Path downloadZip(String version) {
        try {
            String url = AllureConstant.ALLURE_ZIP_BASE_URL + version + "/allure-commandline-" + version + ".zip";
            Path zipFile = Paths.get(AllureConstant.EXTRACTION_DIR.toString(), "allure-" + version + ".zip");

            if (!Files.exists(zipFile)) {
                Files.createDirectories(AllureConstant.EXTRACTION_DIR);
                try (BufferedInputStream in = new BufferedInputStream(new URI(url).toURL().openStream());
                     OutputStream out = Files.newOutputStream(zipFile)) {
                    in.transferTo(out);
                }
            }
            return zipFile;
        } catch (Exception e) {
            LogsManager.error("Error downloading Allure zip: " + e.getMessage());
            return Paths.get("");
        }
    }
    private static void extractZip(Path zipPath) {
        if (zipPath.toString().trim().isEmpty() || !Files.exists(zipPath)) {
            LogsManager.error("Invalid zip path provided for extraction.");
            return;
        }
        try (ZipInputStream zipInputStream = new ZipInputStream(Files.newInputStream(zipPath))) {
            ZipEntry entry;
            while ((entry = zipInputStream.getNextEntry()) != null) {
                Path filePath = Paths.get(AllureConstant.EXTRACTION_DIR.toString(), entry.getName());
                if (entry.isDirectory()) {
                    Files.createDirectories(filePath);
                } else {
                    Files.createDirectories(filePath.getParent());
                    Files.copy(zipInputStream, filePath, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
                }
                zipInputStream.closeEntry();
            }
        } catch (Exception e) {
            LogsManager.error("Error extracting Allure zip: " + e.getMessage());
        }
    }
}}