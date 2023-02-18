package com.sardeni.tests;

import com.sardeni.models.LoginCredentialsBodyModel;
import com.sardeni.models.UpdateUserInfoBodyModel;
import com.sardeni.models.UpdateUserInfoResponseTimeModel;
import org.junit.jupiter.api.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.sardeni.specs.ProjectSpecs.RequestSpec;
import static com.sardeni.specs.ProjectSpecs.ResponseSpec;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ReqresInTests extends TestBase {
    @Test
    @DisplayName("Checking user email")
    void userEmailTest() {

        given(RequestSpec)
                .when()
                .get("/users/2")
                .then()
                .spec(ResponseSpec)
                .statusCode(200)
                .body("data.email", is("janet.weaver@reqres.in"));
    }

    @Test
    @DisplayName("Resource not found")
    void resourceNotFoundTest() {

        given(RequestSpec)
                .when()
                .get("/unknown/23")
                .then()
                .spec(ResponseSpec)
                .statusCode(404);
    }

    @Test
    @DisplayName("Successful login and getting token")
    void successfulLoginAndGetTokenTest() {
        LoginCredentialsBodyModel data = new LoginCredentialsBodyModel();
        data.setEmail("eve.holt@reqres.in");
        data.setPassword("cityslicka");

        given(RequestSpec)
                .body(data)
                .when()
                .post("/login")
                .then()
                .spec(ResponseSpec)
                .statusCode(200)
                .body("token", not(empty()));
    }

    @Test
    @DisplayName("Deleting user")
    void deletingUser() {

        given(RequestSpec)
                .when()
                .delete("/users/2")
                .then()
                .spec(ResponseSpec)
                .log().all()
                .statusCode(204);
    }

    @Test
    @DisplayName("checking avatar through the groovy")
    public void checkAvatarGroovy() {
        given(RequestSpec)
                .when()
                .get("/users/")
                .then().log().all()
                .spec(ResponseSpec)
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
        //     date.setUpdatedAt(DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now()));

        given(RequestSpec)
                .body(data)
                .when()
                .patch("/users/2")
                .then()
                .spec(ResponseSpec)
                .statusCode(200)
                .body("name", is("Neo"))
                .body("job", is("the chosen one"))
                .body("updatedAt", containsString(dateTime));
        // Спросить на занятии у Стаса
        //   .body("updatedAt", containsString(date.toString()));
    }
}