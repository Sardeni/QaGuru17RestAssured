package com.sardeni.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static com.sardeni.helpers.CustomApiListener.withCustomTemplates;
import static io.restassured.RestAssured.*;
import static io.restassured.filter.log.LogDetail.*;
import static io.restassured.http.ContentType.JSON;

public class ProjectSpecs {

    public static RequestSpecification REQUEST_SPEC = with()
            .log().all()
            .filter(withCustomTemplates())
            .baseUri("https://reqres.in")
            .basePath("/api")
            .contentType(JSON);


    public static ResponseSpecification RESPONSE_SPEC = new ResponseSpecBuilder()
            .log(ALL)
            .build();
}