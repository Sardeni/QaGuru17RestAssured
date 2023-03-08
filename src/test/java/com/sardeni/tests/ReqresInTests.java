package com.sardeni.tests;

import com.sardeni.models.LoginCredentialsBodyModel;
import com.sardeni.models.UpdateUserInfoBodyModel;
import com.sardeni.models.UpdateUserInfoResponseTimeModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.sardeni.specs.ProjectSpecs.REQUEST_SPEC;
import static com.sardeni.specs.ProjectSpecs.RESPONSE_SPEC;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ReqresInTests {
    @Test
    @DisplayName("Checking user email")
    void userEmailTest() {

        given(REQUEST_SPEC)
                .when()
                .get("/users/2")
                .then()
                .spec(RESPONSE_SPEC)
                .statusCode(200)
                .body("data.email", is("janet.weaver@reqres.in"));
    }

    @Test
    @DisplayName("Resource not found, HTTP 404")
    void resourceNotFoundTest() {

        given(REQUEST_SPEC)
                .when()
                .get("/unknown/23")
                .then()
                .spec(RESPONSE_SPEC)
                .statusCode(404);
    }

    @Test
    @DisplayName("Successful login and getting token")
    void successfulLoginAndGetTokenTest() {
        LoginCredentialsBodyModel data = new LoginCredentialsBodyModel();
        data.setEmail("eve.holt@reqres.in");
        data.setPassword("cityslicka");

        given(REQUEST_SPEC)
                .body(data)
                .when()
                .post("/login")
                .then()
                .spec(RESPONSE_SPEC)
                .statusCode(200)
                .body("token", not(empty()));
    }

    @Test
    @DisplayName("Deleting user, expecting HTTP 204")
    void deletingUser() {

        given(REQUEST_SPEC)
                .when()
                .delete("/users/2")
                .then()
                .spec(RESPONSE_SPEC)
                .log().all()
                .statusCode(204);
    }

    @Test
    @DisplayName("checking avatar through the groovy")
    public void checkAvatarGroovy() {
        given(REQUEST_SPEC)
                .when()
                .get("/users/")
                .then().log().all()
                .spec(RESPONSE_SPEC)
                .body("data.findAll{it.avatar.startsWith('https://reqres.in')}.avatar.flatten()",
                        hasItem("https://reqres.in/img/faces/3-image.jpg"));
    }

    @Test
    @DisplayName("Checking date and data in response after update")
    void updatingUserInformationTest() {
        String dateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now());
        UpdateUserInfoBodyModel data = new UpdateUserInfoBodyModel();
        UpdateUserInfoResponseTimeModel date = new UpdateUserInfoResponseTimeModel();
        data.setName("Neo");
        data.setJob("the chosen one");

        given(REQUEST_SPEC)
                .body(data)
                .when()
                .patch("/users/2")
                .then()
                .spec(RESPONSE_SPEC)
                .statusCode(200)
                .body("name", is("Neo"))
                .body("job", is("the chosen one"))
                .body("updatedAt", containsString(dateTime));
    }
}