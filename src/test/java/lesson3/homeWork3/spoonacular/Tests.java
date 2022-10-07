package lesson3.homeWork3.spoonacular;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.lessThan;

public class Tests extends AbstractTest {

    @Test
    @Story("GET /recepies/complexSearch")
    @DisplayName("  Поиск рецепта с параметрами cuisine и sort")
    void сomplexSearchTest1(){

    given()// Поиск рецепта с параметрами cuisine и sort
            .queryParam("apiKey", getApiKey())
            .queryParam("cuisine", getCuisine())
            .queryParam("sort", getSort())
            .when()
            .get("https://api.spoonacular.com/recipes/complexSearch")
            .then()
            .assertThat()
            .statusCode(200)
            .statusLine("HTTP/1.1 200 OK")
            .statusLine(containsString("OK"))
            .contentType(ContentType.JSON)
            .time(lessThan(2000L));
}

    @Test
    @Story("GET /recepies/complexSearch")
    @DisplayName(" Поиск рецепта по слову")
    void сomplexSearchTest2(){

    given()// Поиск рецепта по слову
            .queryParam("apiKey", getApiKey())
            .queryParam("query", getQuery())
            .when()
            .get("https://api.spoonacular.com/recipes/complexSearch")
            .then()
            .assertThat()
            .statusCode(200)
            .statusLine("HTTP/1.1 200 OK")
            .statusLine(containsString("OK"))
            .contentType(ContentType.JSON)
            .time(lessThan(2000L));


}

    @Test
    @Story("GET /recepies/complexSearch")
    @DisplayName(" Поиск рецептов по слову и каллорийности ")
    void сomplexSearchTest3(){
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("query", getQuery())
                .queryParam("minCalories", getMinCalories())
                .queryParam("maxCalories",getMaxCalories())
                .when()
                .get("https://api.spoonacular.com/recipes/complexSearch")
                .then()
                .assertThat()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .statusLine(containsString("OK"))
                .contentType(ContentType.JSON)
                .time(lessThan(2000L));


    }

    @Test
    @Story("GET /recepies/complexSearch")
    @DisplayName(" Поиск рецептов  с параметрами diet и excludeIngredients ")
    void сomplexSearchTest4(){
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("diet", getDiet())
                .queryParam("excludeIngredients",getExcludeIngredients())
                .when()
                .get("https://api.spoonacular.com/recipes/complexSearch")
                .then()
                .assertThat()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .statusLine(containsString("OK"))
                .contentType(ContentType.JSON)
                .time(lessThan(2000L));


    }

    @Test
    @Story("GET /recepies/complexSearch")
    @DisplayName(" Поиск рецептов по совпадению с закголовком ")
    void сomplexSearchTest5(){
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("titleMatch",getTitleMatch())
                .when()
                .get("https://api.spoonacular.com/recipes/complexSearch")
                .then()
                .assertThat()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .statusLine(containsString("OK"))
                .contentType(ContentType.JSON)
                .time(lessThan(2000L));


    }

    @Test
    @Story("POST /recipes/cuisine")
    @DisplayName("Тест_1: Классификация кухни по рецепту (заголовок) ")
    void сlassifyСuisineTest1(){
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("title","chili")
                .header("ContentType","application/x-www-form-urlencoded")
                .when()
                .post("https://api.spoonacular.com/recipes/cuisine")
                .then()
                .assertThat()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .statusLine(containsString("OK"))
                .contentType(ContentType.JSON)
                .time(lessThan(2000L));


    }
    @Test
    @Story("POST /recipes/cuisine")
    @DisplayName("Тест_2: Классификация кухни по рецепту (заголовок и ингридиенты) ")
    void сlassifyСuisineTest2(){
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("title","chili")
                .queryParam("ingredientList","hot chili")
                .header("ContentType","application/x-www-form-urlencoded")
                .when()
                .post("https://api.spoonacular.com/recipes/cuisine")
                .then()
                .assertThat()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .statusLine(containsString("OK"))
                .contentType(ContentType.JSON)
                .time(lessThan(2000L));


    }

    @Test
    @Story("POST /recipes/cuisine")
    @DisplayName("Тест_3: Классификация кухни по рецепту (заголовок, ингридиенты и язык) ")
    void сlassifyСuisineTest3(){
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("title","chili")
                .queryParam("ingredientList","hot chili")
                .queryParam("language","en")
                .header("ContentType","application/x-www-form-urlencoded")
                .when()
                .post("https://api.spoonacular.com/recipes/cuisine")
                .then()
                .assertThat()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .statusLine(containsString("OK"))
                .contentType(ContentType.JSON)
                .time(lessThan(2000L));
    }
    @Test
    @Story("POST /recipes/cuisine")
    @DisplayName("Тест_4: Классификация кухни по рецепту (ингридиенты и язык) ")
    void сlassifyСuisineTest4(){
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("ingredientList","hot chili")
                .queryParam("language","en")
                .header("ContentType","application/x-www-form-urlencoded")
                .when()
                .post("https://api.spoonacular.com/recipes/cuisine")
                .then()
                .assertThat()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .statusLine(containsString("OK"))
                .contentType(ContentType.JSON)
                .time(lessThan(2000L));
    }
    @Test
    @Story("POST /recipes/cuisine")
    @DisplayName("Тест_5: Классификация кухни по рецепту (заголовок и язык) ")
    void сlassifyСuisineTest5(){
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("title","chili")
                .queryParam("language","en")
                .header("ContentType","application/x-www-form-urlencoded")
                .when()
                .post("https://api.spoonacular.com/recipes/cuisine")
                .then()
                .assertThat()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .statusLine(containsString("OK"))
                .contentType(ContentType.JSON)
                .time(lessThan(2000L));
    }



}
