package demoPlaze.utiles.datareader;

import demoPlaze.utiles.logs.LogsManager;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Properties;

public class propertyReader {
    public  static Properties loadProperties(){
        try{
            Properties properties=new Properties();
            Collection<File> propertiesFiles;
            // البارامتر الثالث (true) يعني هيدور كمان جوة الفولدرات الفرعية Subdirectories
            File resourcesDir = new File("src/main/resources");
            propertiesFiles = FileUtils.listFiles(resourcesDir , new String[]{"properties"}, true);
            propertiesFiles.forEach(file ->{
                try {
                    properties.load(FileUtils.openInputStream(file));

                } catch (IOException e) {
                    LogsManager.error(e.getMessage());
                }
                properties.putAll(System.getProperties());
                System.getProperties().putAll(properties);
            });
          return properties;
        } catch (Exception e) {
            LogsManager.error(e.getMessage());
                return null;
        }
    }
    public static String getProperty(String key){
        try {
            return System.getProperty(key);
        } catch (Exception e) {
            LogsManager.error(e.getMessage());
            return "";
        }
    }
}
