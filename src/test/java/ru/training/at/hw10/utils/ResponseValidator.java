package ru.training.at.hw10.utils;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import org.apache.hc.core5.http.HttpStatus;

public class ResponseValidator {

    public static ResponseSpecification success() {
        return new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectStatusCode(HttpStatus.SC_SUCCESS)
                .build();
    }

    public static ResponseSpecification notFound() {
        return new ResponseSpecBuilder()
                .expectContentType(ContentType.TEXT)
                .expectStatusCode(HttpStatus.SC_NOT_FOUND)
                .build();
    }

    public static ResponseSpecification badRequest() {
        return new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectStatusCode(HttpStatus.SC_BAD_REQUEST)
                .build();
    }

    public static ResponseSpecification badRequestPlainText() {
        return new ResponseSpecBuilder()
                .expectContentType(ContentType.TEXT)
                .expectStatusCode(HttpStatus.SC_BAD_REQUEST)
                .build();
    }
}
