package ru.training.at.hw10.steps;

import beans.BoardDto;
import constants.CommonValues;
import io.restassured.http.Method;
import io.restassured.response.Response;
import ru.training.at.hw10.utils.ResponseValidator;
import service.BoardsApi;

public class BoardsServiceSteps {
    public static BoardDto createBoard() {
        Response response = BoardsApi.requestBuilder()
                .withName("Some board")
                .withDescription("Description of some board")
                .withMethod(Method.POST)
                .buildRequest()
                .sendRequest();
        response.then().spec(ResponseValidator.success());
        return BoardsApi.getBoardFromResponse(response);
    }

    public static BoardDto getBoard(String id) {
        Response response = BoardsApi.requestBuilder()
                .withId(id)
                .withUrl(CommonValues.ID_PATH_PARAM)
                .buildRequest()
                .sendRequest();
        response.then().spec(ResponseValidator.success());
        return BoardsApi.getBoardFromResponse(response);
    }

    public static void deleteBoard(String id) {
        BoardsApi.requestBuilder()
                .withId(id)
                .withMethod(Method.DELETE)
                .withUrl(CommonValues.ID_PATH_PARAM)
                .buildRequest()
                .sendRequest()
                .then()
                .spec(ResponseValidator.success());
    }
}
