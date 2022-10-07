package lesson3;

import org.junit.jupiter.api.BeforeAll;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
// в AbstractTest выносятся вся основная логика
public abstract class AbstractTest {

    static Properties prop = new Properties();// класс проперти
    private static InputStream configFile;
    private static String apiKey;
    private static String baseUrl;

    @BeforeAll// перед всеми тестами
    static void initTest() throws IOException {
        // получаем объект типа InputStream из файла my.properties (путь к файлу)
        configFile = new FileInputStream("src/main/resources/my.properties");
        // загружаем значения файла в объект типа Properties
        prop.load(configFile);
    //определяем статические переменные - считываем в них соответствующие значения
        apiKey =  prop.getProperty("apiKey");// ключ авторизации
        baseUrl= prop.getProperty("base_url");// базовый url сервиса
    }

    public static String getApiKey() {
        return apiKey;
    }

    public static String getBaseUrl() {
        return baseUrl;
    }
}
