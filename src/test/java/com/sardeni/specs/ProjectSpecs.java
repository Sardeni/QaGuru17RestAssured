package com.sardeni.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static com.sardeni.helpers.CustomApiListener.withCustomTemplates;
import static io.restassured.RestAssured.*;
import static io.restassured.filter.log.LogDetail.*;
import static io.restassured.http.ContentType.JSON;

public class ProjectSpecs {

    public static RequestSpecification RequestSpec = with()
            .log().all()
            .filter(withCustomTemplates())
            .contentType(JSON);


    public static ResponseSpecification ResponseSpec = new ResponseSpecBuilder()
            .log(ALL)
            .build();
}