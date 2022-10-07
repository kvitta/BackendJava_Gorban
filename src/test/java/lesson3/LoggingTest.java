package lesson3;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class LoggingTest extends AbstractTest {
// ЛОГИРОВНИЕ ЗАПРОСА
    @BeforeAll
    static void setUp(){
    // можно настроить логирование ГЛОБАЛЬНО через добавление к нашему restussserd пременную, которая говторит,что
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        // ответ и запрос будут логироваться к случае неудачного обращения

    }
// а можно ЛОКАЛЬНО в самом тесте, вызвав метод log
    @Test
    void getRequestLogTest() {
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("includeNutrition", "false")
                .log().method()// можно залогировать метод
                .log().params()// можно залогировать парметры
                .when()
                .get("https://api.spoonacular.com/recipes/716429/information");

        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++=");

        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("includeNutrition", "false")
                .log().all()// а можно залогинить все
                .when()
                .get("https://api.spoonacular.com/recipes/716429/information");
    }
// ЛОГИРОВАНИЕ ОТВЕТА
    @Test
    void getResponseLogTest(){
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("includeNutrition", "false")
                .log().all()// логируем весь ответ
                .when()
                .get("https://api.spoonacular.com/recipes/716429/information")
                .prettyPeek();// для этого в блоке when вызываем .prettyPeek()
    }

    @Test
    void getErrorTest(){
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("includeNutrition", "false")
                .when()
                .get("https://api.spoonacular.com/recipes/716429/information")
                .then().statusCode(201);
    }
}
