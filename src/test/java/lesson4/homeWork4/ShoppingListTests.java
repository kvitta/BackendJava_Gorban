package lesson4.homeWork4;

import io.qameta.allure.Story;
import lesson4.homeWork4.ResponseHW;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class ShoppingListTests extends AbstractTest{

    @Test
    @Story("Working with Shopping List")
    @DisplayName("Add to Shopping List")
    void addToShoppingList() {
        ResponseHW response = given().spec(getRequestSpecification())
                .body("{\n"
                        + " \"item\": 1 package baking powder,\n"
                        + " \"aisle\":Baking,\n"
                        + " \"parse\": true,\n"
                        + "}")
                .when()
                .post("https://api.spoonacular.com/mealplanner/kvitta24/shopping-list/items").prettyPeek()
                .then()
                .extract()
                .response()
                .body()
                .as(ResponseHW.class);//сохраняем body в класс
        assertThat(response.getAisle(), containsString("Baking"));// проверяем что ответ содержит
    }

    @Test
    @Story("Working with Shopping List")
    @DisplayName("Get Shopping List")
        void getShoppingList () {
        given().spec(getRequestSpecification())
                .when()
                .get("https://api.spoonacular.com/mealplanner/kvitta24/shopping-list")
                .then()
                .spec(responseSpecification);

    }
        @Test
        @Story("Working with Shopping List")
        @DisplayName("Delete from Shopping List")
        void deleteShoppingList () {
            given().spec(getRequestSpecification())
                    .pathParam("id",1306123)// значение id d ответе addToShoppingList
                .when()
                    .delete("https://api.spoonacular.com/mealplanner/kvitta24/shopping-list/items/{id}")
                    .then()
                    .spec(responseSpecification);
        }
    }



