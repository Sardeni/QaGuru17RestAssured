package com.sardeni;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ReqresInTests {

    @Test
    @DisplayName("Checking user email")
    void userEmailTest() {

        given()
                .log().all()
                .when()
                .get("https://reqres.in/api/users/2")
                .then()
                .log().all()
                .statusCode(200)
                .body("data.email", is("janet.weaver@reqres.in"));

    }

    @Test
    @DisplayName("Resource not found")
    void resourceNotFoundTest() {

        given()
                .log().all()
                .when()
                .get("https://reqres.in/api/unknown/23")
                .then()
                .log().all()
                .statusCode(404);
    }

    @Test
    @DisplayName("Successful login and getting token")
    void successfulLoginAndGetTokenTest() {

        String credentials = "{\"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\"}";

        given()
                .log().all()
                .body(credentials)
                .contentType(ContentType.JSON)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().all()
                .statusCode(200)
                .body("token", not(empty()));
    }

    @Test
    @DisplayName("Deleting user")
    void deletingUser() {

        given()
                .log().all()
                .when()
                .delete("https://reqres.in/api/users/2")
                .then()
                .log().status()
                .statusCode(204);
    }

    @Test
    @DisplayName("Checking date and data in response after update")
    void updatingUserInformationTest() {
        String updateData = "{\"name\": \"Neo\", \"job\": \"the chosen one\"}";
        String dateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                .format(LocalDateTime.now());
        given()
                .log().all()
                .body(updateData)
                .contentType(ContentType.JSON)
                .when()
                .patch("https://reqres.in/api/users/2")
                .then()
                .log().all()
                .statusCode(200)
                .body("name", is("Neo"))
                .body("job", is("the chosen one"))
                .body("updatedAt", containsString(dateTime));
    }
}
