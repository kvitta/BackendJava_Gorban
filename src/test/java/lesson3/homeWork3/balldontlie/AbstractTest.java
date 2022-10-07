package lesson3.homeWork3.balldontlie;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

// в AbstractTest выносятся вся основная логика
public abstract class AbstractTest {

    static Properties prop = new Properties();// класс проперти
    private static InputStream configFile;
    private static String baseUrl;



    @BeforeAll// перед всеми тестами
    static void initTest() throws IOException {
        // получаем объект типа InputStream из файла propertiesForHW3 (путь к файлу)
        configFile = new FileInputStream("src/main/resources/HomeWork_3/properties_balldontlie");
        // загружаем значения файла в объект типа Properties
        prop.load(configFile);
    //определяем статические переменные - считываем в них соответствующие значения
        baseUrl= prop.getProperty("base_url");

    }
    @BeforeAll
    static void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    public static String getBaseUrl() {return baseUrl;}}


