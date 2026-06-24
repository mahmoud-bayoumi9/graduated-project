    package demoPlaze.utiles.report;

    import java.nio.file.Path;
    import java.nio.file.Paths;

<<<<<<< HEAD
    public class AllureConstant {
        public static final Path USER_DIR = Paths.get(System.getProperty("user.dir"));
        public static final Path USER_HOME = Paths.get(System.getProperty("user.home"));
        // تعديل المسار ليتوافق مع المكان الحقيقي لملفات الـ JSON
        public static final Path RESULT_FOLDER = USER_DIR.resolve("test-output").resolve("allure-results");
        public static final Path REPORT_PATH = USER_DIR.resolve("test-output").resolve("reports");
        public static final Path FULL_REPORT_PATH = USER_DIR.resolve("test-output").resolve("full-reports");
        public static final Path HISTORY_FOLDER = FULL_REPORT_PATH.resolve("history");
        public static final Path RESULT_HISTORY_FOLDER = RESULT_FOLDER.resolve("history");

        public static final String INDEX_HTML = "index.html";
        public static final String REPORT_PREFIX = "AllureReport_";
        public static final String REPORT_EXTENSION = ".html";

        public static final String ALLURE_ZIP_BASE_URL = "https://repo.maven.apache.org/maven2/io/qameta/allure/allure-commandline/";
        public static final Path EXTRACTION_DIR = USER_HOME.resolve(".m2").resolve("repository").resolve("allure");
    }
=======
public class AllureConstant {
    public static final Path USER_DIR = Paths.get(System.getProperty("user.dir"));
    public static final Path USER_HOME = Paths.get(System.getProperty("user.home"));

    public static final Path RESULT_FOLDER = USER_DIR.resolve("test-output").resolve("allure-results");
    public static final Path REPORT_PATH = USER_DIR.resolve("test-output").resolve("reports");
    public static final Path FULL_REPORT_PATH = USER_DIR.resolve("test-output").resolve("full-reports");

    public static final Path HISTORY_FOLDER = FULL_REPORT_PATH.resolve("history");
    public static final Path RESULT_HISTORY_FOLDER = RESULT_FOLDER.resolve("history");

    public static final String INDEX_HTML = "index.html";
    public static final String REPORT_PREFIX = "AllureReport_";
    public static final String REPORT_EXTENSION = ".html";

    public static final String ALLURE_ZIP_BASE_URL = "https://repo.maven.apache.org/maven2/io/qameta/allure/allure-commandline/";
    public static final Path EXTRACTION_DIR = USER_HOME.resolve(".m2").resolve("repository").resolve("allure");
}
>>>>>>> 5d6cdf008ee55d5d87a8e2f21ca53cb1101770e9
