package lesson3.homeWork3.newYorkTimes;

import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.lessThan;

public class Tests extends AbstractTest {

    @Test
    @Story("Most Popular Articles")
    void getMostEmailedArticle(){
        given()
                .queryParam("api-key", getApiKey())
                .pathParam("period", getPeriod())// pathParam таже можно передать отдельно
                .when()
                .get(getBaseUrl()+"mostpopular/v2/emailed/{period}.json")//в этом случае мы прописываем только url
                .then()
                .assertThat()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .statusLine(containsString("OK"))
                .contentType(ContentType.JSON)
                .time(lessThan(3000L));
    }

    @Test
    @Story("Most Popular Articles")
    void getMostViewedArticle(){
        given()
                .queryParam("api-key", getApiKey())
                .pathParam("period", getPeriod())// pathParam таже можно передать отдельно
                .when()
                .get(getBaseUrl()+"mostpopular/v2/viewed/{period}.json")//в этом случае мы прописываем только url
                .then()
                .assertThat()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .statusLine(containsString("OK"))
                .contentType(ContentType.JSON)
                .time(lessThan(3000L));
    }

    @Test
    @Story("Search")
    void search(){
        given()
                .queryParam("api-key", getApiKey())
                .queryParam("query", getValueForSearch())
                .queryParam("offset", "10")
                .queryParam("fields", "all")
                .when()
                .get(getBaseUrl()+"semantic/v2/concept/search.json")
                .then()
                .assertThat()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .statusLine(containsString("OK"))
                .contentType(ContentType.JSON);
    }

    @Test
    @Story("Search")
    void articleSearch(){
        given()
                .queryParam("api-key", getApiKey())
                .queryParam("facet", "true")
                .queryParam("facet_fields", "source")
                .queryParam("facet_filter", "true")
                .queryParam("offset", "10")
                .queryParam("page", "1")
                .queryParam("sort", "newest")
                .when()
                .get(getBaseUrl()+"search/v2/articlesearch.json")//в этом случае мы прописываем только url
                .then()
                .assertThat()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .statusLine(containsString("OK"))
                .contentType(ContentType.JSON)
                .time(lessThan(3000L));
    }

    @Test
    @Story("TopStories")
    void getTopStories(){
        given()
                .queryParam("api-key", getApiKey())
                .pathParam("section", "books")
                .when()
                .get(getBaseUrl()+"topstories/v2/{section}.json")//в этом случае мы прописываем только url
                .then()
                .assertThat()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .statusLine(containsString("OK"))
                .contentType(ContentType.JSON)
                .time(lessThan(3000L));
    }
}
