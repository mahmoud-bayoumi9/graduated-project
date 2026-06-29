package demoPlaze;

import demoPlaze.utiles.logs.LogsManager;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.logging.LogManager;

public class FileUtiles {
    private static  final String USER_DIR=System.getProperty("user.dir")+File.separator;
    public static void renameFile(String oldPath, String newPath) {

        File oldFile = new File(USER_DIR
                +oldPath);

        File newFile = new File(USER_DIR+newPath);
        try {
            if (!oldFile.exists()) {

                return;
            }

            if (oldFile.renameTo(newFile)) {
                LogsManager.info("file renaming");
            } else {
                LogsManager.error("can not rename file");

            }
        } catch (Exception e) {
            LogsManager.error(e.getMessage());
        }
    }
    public static void createDirectory(String Path){
        try {
            File file=new File(USER_DIR+Path);
            if(!file.exists()){
                file.mkdir();
                LogsManager.info("file created at"+Path);
            }
        } catch (Exception e) {
            LogsManager.error(e.getMessage());
        }

    }
    public static void cleanDirectory(File file){
        try {
            FileUtils.deleteQuietly(file);
        } catch (Exception e) {
            LogsManager.error(e.getMessage());
        }
    }
    public static void copyDirectory(String sourcePath, String destinationPath) {
        Path source = Paths.get(USER_DIR + sourcePath);
        Path destination = Paths.get(USER_DIR + destinationPath);
        try {
            if (!Files.exists(source)) {
                LogsManager.error("Source directory does not exist at: " + sourcePath);
                return;
            }
            Files.walkFileTree(source, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    Path targetDir = destination.resolve(source.relativize(dir));
                    if (!Files.exists(targetDir)) {
                        Files.createDirectories(targetDir);
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Path targetFile = destination.resolve(source.relativize(file));
                    Files.copy(file, targetFile, StandardCopyOption.REPLACE_EXISTING);
                    return FileVisitResult.CONTINUE;
                }
            });

            LogsManager.info("Directory copied successfully from " + sourcePath + " to " + destinationPath);

        } catch (Exception e) {
            LogsManager.error("Failed to copy directory: " + e.getMessage());}
}}


