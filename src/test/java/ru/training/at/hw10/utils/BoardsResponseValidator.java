package ru.training.at.hw10.utils;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import org.apache.hc.core5.http.HttpStatus;

public class BoardsResponseValidator {

    public static ResponseSpecification goodResponseSpecification() {
        return new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectStatusCode(HttpStatus.SC_SUCCESS)
                .build();
    }

    public static ResponseSpecification boardNotFoundResponseSpecification() {
        return new ResponseSpecBuilder()
                .expectContentType(ContentType.TEXT)
                .expectStatusCode(HttpStatus.SC_NOT_FOUND)
                .build();
    }

    public static ResponseSpecification badRequestResponseSpecification() {
        return new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectStatusCode(HttpStatus.SC_BAD_REQUEST)
                .build();
    }

    public static ResponseSpecification badRequestResponseSpecificationPlainText() {
        return new ResponseSpecBuilder()
                .expectContentType(ContentType.TEXT)
                .expectStatusCode(HttpStatus.SC_BAD_REQUEST)
                .build();
    }

    public static void checkResponseIsOK(Response response) {
        response.then().assertThat().spec(goodResponseSpecification());
    }

    public static void checkResponseIsNotFound(Response response) {
        response.then().assertThat().spec(boardNotFoundResponseSpecification());
    }

    public static void checkResponseHasBadRequestStatus(Response response) {
        response.then().assertThat().spec(badRequestResponseSpecification());
    }

    public static void checkResponseHasBadRequestStatusPlainText(Response response) {
        response.then().assertThat().spec(badRequestResponseSpecificationPlainText());
    }
}
