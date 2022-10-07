package lesson3.homeWork3.balldontlie;

import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.lessThan;

public class Tests extends AbstractTest {

    @Test
    @Story("Players")
    void getAllPlayers(){
        given()
                .queryParam("page", "1")
                .queryParam("per_page", "10")
                .queryParam("search", "davis")
                .when()
                .get(getBaseUrl()+"v1/players")
                .then()
                .assertThat()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .statusLine(containsString("OK"))
                .contentType(ContentType.JSON)
                .time(lessThan(3000L));
    }
    @Test
    @Story("Players")
    void getSpecificPlayer(){
        given()
                .pathParam("playerID", "237")
                .when()
                .get(getBaseUrl()+"v1/players/{playerID}")
                .then()
                .assertThat()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .statusLine(containsString("OK"))
                .contentType(ContentType.JSON)
                .time(lessThan(3000L));
    }

    @Test
    @Story("Teams")
    void getAllTeams(){
        given()
                .queryParam("page", "2")
                .queryParam("per_page", "10")
                .when()
                .get(getBaseUrl()+"v1/teams")
                .then()
                .assertThat()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .statusLine(containsString("OK"))
                .contentType(ContentType.JSON)
                .time(lessThan(3000L));
    }

    @Test
    @Story("Teams")
    void getSpecificTeam(){
        given()
                .pathParam("teamID", "14")
                .when()
                .get(getBaseUrl()+"v1/teams/{teamID}")
                .then()
                .assertThat()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .statusLine(containsString("OK"))
                .contentType(ContentType.JSON)
                .time(lessThan(3000L));
    }

    @Test
    @Story("`games`")
    void getAllGames(){
        given()
                .queryParam("season", "2022")
                .when()
                .get(getBaseUrl()+"v1/games")
                .then()
                .assertThat()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .statusLine(containsString("OK"))
                .contentType(ContentType.JSON)
                .time(lessThan(3000L));
    }

}
