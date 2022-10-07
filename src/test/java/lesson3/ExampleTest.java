package lesson3;

import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.lessThan;


public class ExampleTest extends AbstractTest {// наследуемся от AbstractTest
    // все пишется по логике BDD
    // given- предусловие
    // when - сам тест
    // then - проверка

    @Test
    void getExampleTest() {
        given()//предусловие отсутствует
                .when()
                // вызывает get, указывая ресурс и добавляя к нему значение ApiKey
                .get(getBaseUrl()+"recipes/716429/information?" +
                        "includeNutrition=false&apiKey=" +getApiKey())
                                .then()
                //проверям ответ от сервера код 200
                                .statusCode(200);

        given()
                .when()
                // можно вызвать request, используюем enum- указываем метод,передаем ресурс, квери параметры в виде переменных  и следом их значение
                .request(Method.GET,getBaseUrl()+"recipes/716429/information?" +
                        "includeNutrition={Nutrition}&apiKey={apiKey}", false, getApiKey())
                .then()
                .statusCode(200);
    }

    @Test
    void getSpecifyingRequestDataTest() {
        // можно вынести  все квери параметры отдельно в блоке given
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("includeNutrition", "false")
                .pathParam("id", 716429)// pathParam таже можно передать отдельно
                .when()
                .get(getBaseUrl()+"recipes/{id}/information")//в этом случае мы прописываем только url
                .then()
                .statusCode(200);

        given()
                .when()
                //в этом случае pathParams тоже указываются в стринге через varams
                .get(getBaseUrl()+"recipes/{id}/information?" +
                        "includeNutrition={Nutrition}&apiKey={apiKey}",716429, false, getApiKey())
                .then()
                .statusCode(200);

        given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title","Pork roast with green beans")
                .when()
                .post(getBaseUrl()+"recipes/cuisine")
                .then()
                .statusCode(200);


        //куки можно вынести в отдельный объект
        Cookie someCookie = new Cookie
                .Builder("some_cookie", "some_value")
                .setSecured(true)
                .setComment("some comment")
                .build();

        //  а можно прописать внутри блока чрез ключ-значение
        given().cookie("username","max")
                .cookie(someCookie)
                .when()
                .get(getBaseUrl()+"recipes/716429/information?" +
                        "includeNutrition=false&apiKey=" +getApiKey())
                .then()
                .statusCode(200);

        // headers так же можно прописать через ключ-значение, либо серез оьбтельный объект
        given().headers("username","max")
                .when()
                .get(getBaseUrl()+"recipes/716429/information?" +
                        "includeNutrition=false&apiKey=" +getApiKey())
                .then()
                .statusCode(200);

        given()
                .queryParam("hash", "a3da66460bfb7e62ea1c96cfa0b7a634a346ccbf")
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
                .post(getBaseUrl()+"mealplanner/geekbrains/items")
                .then()
                .statusCode(200);

    }


    @Test
    void getResponseData(){
        // методы возвращают объект типа response
        Response response = given()
                            .when()
                            .get(getBaseUrl()+"recipes/716429/information?" +
                            "includeNutrition=false&apiKey=" +getApiKey());

        // можно получить все  headers
        Headers allHeaders = response.getHeaders();
        // можно обратиться к определенномц заголовку по его ключу
        System.out.println("Content-Encoding: " + response.getHeader("Content-Encoding"));

        // получить все куки
        Map<String, String> allCookies = response.getCookies();
        // обратиться к определнной куки
        System.out.println("CookieName: " + response.getCookie("cookieName"));

        // получить статуслайн
        System.out.println("StatusLine: " + response.getStatusLine());
        // получить код объекта
        System.out.println("Code: " + response.getStatusCode());

        // получение данные ответа
        String cuisine = given()
                .queryParam("apiKey", getApiKey())
                .when()
                .post(getBaseUrl()+"recipes/cuisine")
                .path("cuisine");// можно вернуть часть json прописав часть пути

        System.out.println("cuisine: " + cuisine);

        response = given()
                .queryParam("apiKey", getApiKey())
                .when()
                .post(getBaseUrl()+"recipes/cuisine")
                .then().extract().response();// можно получить response через метод extract в блоке then

        String confidence = given()
                .queryParam("apiKey", getApiKey())
                .when()
                .post(getBaseUrl()+"recipes/cuisine")
                .then().extract()
                .jsonPath()//обращаемся к json
                .get("confidence")// обращаемся к конкретному объекту
                .toString();//и получаем строку

        System.out.println("confidence: " + confidence);

    }

    @Test
    void getVerifyingResponseData(){
// для проверок модно использовать встроенный систаксис библиотеки
        JsonPath response = given()
                .queryParam("apiKey", getApiKey())
                .queryParam("includeNutrition", "false")
                .when()
                .get("https://api.spoonacular.com/recipes/716429/information")
                .body()
                .jsonPath();// получаем json и дальше работаем с ним
        assertThat(response.get("vegetarian"), is(false));
        assertThat(response.get("vegan"), is(false));
        assertThat(response.get("license"), equalTo("CC BY-SA 3.0"));
        assertThat(response.get("pricePerServing"), equalTo(163.15F));
        assertThat(response.get("extendedIngredients[0].aisle"), equalTo("Milk, Eggs, Other Dairy"));


        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("includeNutrition", "false")
                .when()
                .get("https://api.spoonacular.com/recipes/716429/information")
                // можно в блоке then вызвать .assertThat() и дальше уже проверки
                .then()
                .assertThat()
                 //.cookie("cookieName", "cookieValue")
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .statusLine(containsString("OK"))
                .header("Connection", "keep-alive")
                .header("Content-Length", Integer::parseInt, lessThan(3000))
                .contentType(ContentType.JSON)
                //  .body(equalTo("something"))
                .time(lessThan(2000L));

        given()// можно проверить чрез получение объекта response
                .queryParam("apiKey", getApiKey())
                .queryParam("includeNutrition", "false")
                .response()//проверки идет в ожидании
                .contentType(ContentType.JSON)
                .time(lessThan(2000L))
                .header("Connection", "keep-alive")
                .expect()
                .body("vegetarian", is(false))
                .body("vegan", is(false))
                .body("license", equalTo("CC BY-SA 3.0"))
                .body("pricePerServing", equalTo(163.15F))
                .body("extendedIngredients[0].aisle", equalTo("Milk, Eggs, Other Dairy"))
                .when()// а затем уже сам запрос
                .get("https://api.spoonacular.com/recipes/716429/information");

    }

    @Test
    void addMealTest() {
        String id = given()
                .queryParam("hash", "a3da66460bfb7e62ea1c96cfa0b7a634a346ccbf")
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
                .post("https://api.spoonacular.com/mealplanner/geekbrains/items")// делаем postзапрос
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()// он возвращает объект json
                .get("id")//берем id
                .toString();//сохраняем его в переменную

        given()// использует его в методе  delete
                .queryParam("hash", "a3da66460bfb7e62ea1c96cfa0b7a634a346ccbf")
                .queryParam("apiKey", getApiKey())
                .delete("https://api.spoonacular.com/mealplanner/geekbrains/items/" + id)
                .then()
                .statusCode(200);
    }



}
