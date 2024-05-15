package accuweather;

import io.qameta.allure.*;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.example.accuweather.location.Location;

import java.util.List;

import static io.restassured.RestAssured.given;

public class GetLocationTest extends AccuweatherAbstractTest {

    @Test
    @DisplayName("GetLocationTest")
    @Description("GET LocationTest")
    @Link("")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Черемных Екатерина")
    @Story(value = "Тестирование Location")
    void getLocation_autocomplete_returnGranada() {

        List<Location> response = given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl()+"/locations/v1/cities/autocomplete?q=Granada")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000l))
                .time(Matchers.greaterThan(200l))
                .extract()
                .body().jsonPath().getList(".", Location.class);

        Assertions.assertEquals(10,response.size());
        Assertions.assertEquals("Granada", response.get(0).getLocalizedName());
    }
}
