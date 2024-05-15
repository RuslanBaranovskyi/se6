package accuweather;

import io.qameta.allure.*;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.example.accuweather.weather.DailyForecast;
import org.example.accuweather.weather.Weather;

import java.util.List;

import static io.restassured.RestAssured.given;

public class GetWeatherOneDayTest extends AccuweatherAbstractTest {

    @Test
    @DisplayName("GetWeatherOneDayTest")
    @Description("GET WeatherOneDayTest")
    @Link("")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Черемных Екатерина")
    @Story(value = "Тестирование WeatherOneDay")
    void getWeatherOneDay_shouldReturn() {

        Weather response = given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl()+"/forecasts/v1/daily/1day/306729")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000l))
                .extract()
                .response()
                .body().as(Weather.class);

        Assertions.assertEquals(1,response.getDailyForecasts().size());
        Assertions.assertNotNull(response.getHeadline());
    }

    @Test
    @DisplayName("getDailyForecastsList")
    @Description("GET DailyForecastsList")
    @Link("")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Черемных Екатерина")
    @Story(value = "Тестирование DailyForecastsList")
    void getDailyForecastsList() {

        List<DailyForecast> response = given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl()+"/forecasts/v1/daily/1day/306729")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000l))
                .extract()
                .response()
                .body().jsonPath().getList("DailyForecasts", DailyForecast.class);
        Assertions.assertEquals(1, response.size());
    }

    @Test
    @DisplayName("getString")
    @Description("GET String")
    @Link("")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Черемных Екатерина")
    @Story(value = "Тестирование String")
    void getString() {
        String response = given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl()+"/forecasts/v1/daily/1day/306729")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000l))
                .extract().asString();
        Assertions.assertTrue(response.contains("Headline"));
        Assertions.assertTrue(response.contains("DailyForecasts"));
    }
}
