package com.sardeni.specs;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static com.sardeni.helpers.CustomApiListener.withCustomTemplates;
import static io.restassured.RestAssured.*;
import static io.restassured.filter.log.LogDetail.*;

    public class ProjectSpecs {

        public static RequestSpecification RequestSpec = with()
                .log().all()
                .filter(withCustomTemplates())
                .contentType(ContentType.JSON);


        public static ResponseSpecification ResponseSpec = new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .build();
    }


