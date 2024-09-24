package dev.matheuscruz;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class ApiResourceTest {

    @Test
    void testGgg() {
        given()
        .when().get("/api/clients/villains")
        .then()
        .statusCode(200)
        .body(is("special"));
    }

    @Test
    void testDdd() {
        given()
        .when().get("/api/clients/heroes")
        .then()
        .statusCode(200)
        .body(is("heroes"));
    }
}