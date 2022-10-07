package lesson3.homeWork3.spoonacular;

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
    private static String apiKey;
    private static String baseUrl;
    private static String cuisine;
    private static String sort;
    private static String query;
    private static String maxCalories;
    private static String minCalories;
    private static String diet;
    private  static String excludeIngredients;
    private static String titleMatch;

    @BeforeAll// перед всеми тестами
    static void initTest() throws IOException {
        // получаем объект типа InputStream из файла propertiesForHW3 (путь к файлу)
        configFile = new FileInputStream("src/main/resources/HomeWork_3/properties_spoonacular");
        // загружаем значения файла в объект типа Properties
        prop.load(configFile);
    //определяем статические переменные - считываем в них соответствующие значения
        apiKey =  prop.getProperty("apiKey");
        baseUrl= prop.getProperty("base_url");
        cuisine= prop.getProperty("cuisine");
        sort= prop.getProperty("sort");
        query= prop.getProperty("query");
        maxCalories = prop.getProperty("maxCalories");
        minCalories= prop.getProperty("minCalories");
        diet = prop.getProperty("diet");
        excludeIngredients= prop.getProperty("excludeIngredients");
        titleMatch = prop.getProperty("titleMatch");
    }
    @BeforeAll
    static void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    public static String getApiKey() {return apiKey;}

    public static String getBaseUrl() {return baseUrl;}

    public static String getCuisine() { return cuisine;}

    public static String getSort() { return sort;}

    public static String getQuery() {return query;}

    public static String getMaxCalories() {return maxCalories;}

    public static String getMinCalories() {return minCalories;}

    public static String getDiet() {return diet;}

    public static String getExcludeIngredients() {return excludeIngredients;}

    public static String getTitleMatch() {return titleMatch;}
}
