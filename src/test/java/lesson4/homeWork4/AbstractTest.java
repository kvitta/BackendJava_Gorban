package lesson4.homeWork4;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
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
    private static String email;
    private static String firstname;
    private static String lastname;
    private static String username;
    private static String password;
    private static String hash;

    protected static ResponseSpecification responseSpecification;// создали переменную для спецификации ответа
    protected static RequestSpecification requestSpecification;// создали переменную для спецификации запроса

    @BeforeAll// перед всеми тестами
    static void initTest() throws IOException {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        // получаем объект типа InputStream из файла propertiesForHW3 (путь к файлу)
        configFile = new FileInputStream("src/main/resources/HomeWork_3/propertiesForHW3");
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
        username = prop.getProperty("username");
        email = prop.getProperty("email");
        firstname = prop.getProperty("firstname");
        lastname = prop.getProperty("lastname");
        password = prop.getProperty("password");
        hash = prop.getProperty("hash");

        responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectStatusLine("HTTP/1.1 200 OK")
                .expectContentType(ContentType.JSON)
                .expectResponseTime(Matchers.lessThan(6000L))
                //.expectHeader("Access-Control-Allow-Credentials", "true")
                .build();

        //requestSpecification содержит общие значения для всех тестов
        requestSpecification = new RequestSpecBuilder()
                .addQueryParam("apiKey", apiKey)
                .addQueryParam("hash", hash)
                //.addQueryParam("includeNutrition", "false")
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
// можно объявить их в глобальные переменные restassured( но тогда они будут для вообще всего проекта)

        RestAssured.responseSpecification = responseSpecification;
        RestAssured.requestSpecification = requestSpecification;
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

    public static String getEmail() {
        return email;
    }

    public static String getFirstname() {
        return firstname;
    }

    public static String getLastname() {
        return lastname;
    }

    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }

    public static String getHash() {return hash;}

    public RequestSpecification getRequestSpecification(){
        return requestSpecification;
    }
}
