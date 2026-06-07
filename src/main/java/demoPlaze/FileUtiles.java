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
//<?xml version="1.0" encoding="UTF-8"?>
//<project xmlns="http://maven.apache.org/POM/4.0.0"
//xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
//xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
//    <modelVersion>4.0.0</modelVersion>
//
//    <groupId>org.example</groupId>
//    <artifactId>Firstautomation</artifactId>
//    <version>1.0-SNAPSHOT</version>
//
//    <properties>
//        <maven.compiler.source>21</maven.compiler.source>
//        <maven.compiler.target>21</maven.compiler.target>
//        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
//        <allure.version>2.34.0</allure.version>
//        <aspectj.version>1.9.20.1</aspectj.version>
//    </properties>
//    <build>
//        <directory>test-output/target</directory>
//    </build>
//
//    <build>
//        <plugins>
//            <plugin>
//                <groupId>org.apache.maven.plugins</groupId>
//                <artifactId>maven-surefire-plugin</artifactId>
//                <version>3.2.5</version>
//                <configuration>
//                    <argLine>
//                        -javaagent:"${settings.localRepository}/org/aspectj/aspectjweaver/${aspectj.version}/aspectjweaver-${aspectj.version}.jar"
//                    </argLine>
//                </configuration>
//            </plugin>
//        </plugins>
//    </build>
//    <repositories>
//        <repository>
//            <id>maven-central-mirror</id>
//<name>Maven Central Mirror</name>
//            <url>https://repository.jboss.org/nexus/content/repositories/central/</url>
//        </repository>
//    </repositories>
//
//    <dependencyManagement>
//
//        <dependencies>
//            <dependency>
//                <groupId>io.qameta.allure</groupId>
//                <artifactId>allure-bom</artifactId>
//<version>${allure.version}</version>
//                <type>pom</type>
//                <scope>import</scope>
//            </dependency>
//        </dependencies>
//    </dependencyManagement>
//
//    <dependencies>
//<!--        &lt;!&ndash; Selenium Java (تم تصحيح الـ GroupId) &ndash;&gt;-->
//<!--        <dependency>-->
//<!--            <groupId>org.openqa.seleniumhq.selenium</groupId>-->
//<!--            <artifactId>selenium-java</artifactId>-->
//<!--            <version>4.21.0</version>-->
//<!--        </dependency>-->
//        <!-- Source: https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java -->
//        <dependency>
//            <groupId>org.seleniumhq.selenium</groupId>
//            <artifactId>selenium-java</artifactId>
//            <version>4.44.0</version>
//            <scope>compile</scope>
//        </dependency>
//        <!-- TestNG (تم حذف الـ scope لتفادي مشاكل المجلدات) -->
//        <dependency>
//            <groupId>org.testng</groupId>
//            <artifactId>testng</artifactId>
//            <version>7.10.2</version>
//        </dependency>
//
//        <dependency>
//            <groupId>commons-io</groupId>
//            <artifactId>commons-io</artifactId>
//            <version>2.11.0</version>
//        </dependency>
//
//        <dependency>
//            <groupId>com.jayway.jsonpath</groupId>
//            <artifactId>json-path</artifactId>
//            <version>2.9.0</version>
//        </dependency>
//
//        <dependency>
//            <groupId>io.qameta.allure</groupId>
//            <artifactId>allure-testng</artifactId>
//            <version>2.34.0</version>
//        </dependency>
//
//        <dependency>
//            <groupId>org.aspectj</groupId>
//            <artifactId>aspectjweaver</artifactId>
//<version>${aspectj.version}</version>
//        </dependency>
//        <!-- Source: https://mvnrepository.com/artifact/com.automation-remarks/video-recorder-testng -->
//        <dependency>
//            <groupId>com.github.automatedowl</groupId>
//            <artifactId>allure-environment-writer</artifactId>
//            <version>1.0.0</version>
//        </dependency>
//        <!-- Source: https://mvnrepository.com/artifact/org.fusesource.jansi/jansi -->
//        <dependency>
//            <groupId>org.fusesource.jansi</groupId>
//            <artifactId>jansi</artifactId>
//            <version>2.4.2</version>
//            <scope>compile</scope>
//        </dependency>
//        <dependency>
//            <groupId>org.jsoup</groupId>
//            <artifactId>jsoup</artifactId>
//            <version>1.18.1</version>
//        </dependency>
//    </dependencies>
//</project>
