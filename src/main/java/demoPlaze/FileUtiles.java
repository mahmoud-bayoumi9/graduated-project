package demoPlaze;

import demoPlaze.utiles.logs.LogsManager;
import org.apache.commons.io.FileUtils;

import java.io.File;
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
}
