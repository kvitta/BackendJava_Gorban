package lesson3.homeWork3.spoonacular;

import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.responseSpecification;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.lessThan;

public class MealPlanTest extends AbstractTest {

    @Test
    @Story("Working with the Meal Planner")
    @DisplayName("Создание и удаление в Meal Plan")
    void mealPlan(){

        //connect user
        String hash = given()
           // given()//connect user
                //.queryParam("hash", "a3da66460bfb7e62ea1c96cfa0b7a634a346ccbf")
                .queryParam("apiKey", getApiKey())
                .body("{\n"
                        + " \"username\":" + getUsername()+",\n"
                        + " \"firstname\":"+ getFirstname() +",\n"
                        + " \"lastname\": "+ getLastname() +",\n"
                        + " \"email\":"+ getEmail() +",\n"
                        + "}")
                .when()
                .post("https://api.spoonacular.com/users/connect")// делаем postзапрос
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()// он возвращает объект json
                .get("hash")//берем hash
               .toString();//сохраняем его в переменную



        //Get Meal Plan Templates

        given()
                .queryParam("hash", hash)
                .queryParam("apiKey", getApiKey())
                .queryParams("username",getUsername())
                .pathParams("username",getUsername())
                .when()
                .get("https://api.spoonacular.com/mealplanner/{username}/templates")
                .then()
                .assertThat()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .statusLine(containsString("OK"))
                .contentType(ContentType.JSON)
                .time(lessThan(2000L));

       //add meal
    String id = given()
            .queryParam("hash", hash)
            .queryParam("apiKey", getApiKey())
            .body("{\n"
                    + " \"date\": 1644881179,\n"
                    + " \"slot\": 1,\n"
                    + " \"position\": 0,\n"
                    + " \"type\": \"INGREDIENTS\",\n"
                    + " \"value\": {\n"
                    + " \"ingredients\": [\n"
                    + " {\n"
                    + " \"name\": \"1 banana\"\n"
                    + " }\n"
                    + " ]\n"
                    + " }\n"
                    + "}")
            .when()
            .post("https://api.spoonacular.com/mealplanner/Kvitta/items")// делаем postзапрос
            .then()
            .statusCode(200)
            .extract()
            .jsonPath()// он возвращает объект json
            .get("id")//берем id
            .toString();//сохраняем его в переменную

        //delete meal
    given()
                .queryParam("hash", hash)
                .queryParam("apiKey", getApiKey())
            .delete("https://api.spoonacular.com/mealplanner/Kvitta/items/" + id)
                .then()
                .statusCode(200);
}}




