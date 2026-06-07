package demoPlaze.utiles.report;

import demoPlaze.utiles.logs.LogsManager;
import io.qameta.allure.Allure;

import java.nio.file.Files;
import java.nio.file.Path;

public class AllureAttachment {
    public static void attachmentScreenShots(String name,String path){
        try {
            Path screenshot= Path.of(path);
            if(Files.exists(screenshot)){
                Allure.addAttachment(name, Files.newInputStream(screenshot));

            }else {
                LogsManager.error("Screenshot not found");
            }
        } catch (Exception e) {
            LogsManager.error(e.getMessage());
        }
    }
}
