package com.sardeni.specs;

import io.restassured.builder.ResponseBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static com.sardeni.helpers.CustomApiListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.*;

public class ProjectSpecs {

    public static RequestSpecification RequestSpec = with()
            .log().all()
            .filter(withCustomTemplates())
            .contentType(ContentType.JSON);


    public static ResponseSpecification ResponseSpec = new ResponseSpecBuilder()
            .log(STATUS)
            .log(HEADERS)
            .log(BODY)
            .build();


}


