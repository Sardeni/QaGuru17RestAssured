package com.sardeni.tests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

import static com.sardeni.helpers.CustomApiListener.withCustomTemplates;

public class TestBase {
    @BeforeAll
    public static void setUp() {
        RestAssured.filters(withCustomTemplates());
        RestAssured.baseURI = "https://reqres.in";
        RestAssured.basePath = "/api";
    }
}