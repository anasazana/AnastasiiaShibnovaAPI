package ru.training.at.hw10.steps;

import beans.BoardDto;
import constants.CommonValues;
import io.restassured.http.Method;
import io.restassured.response.Response;
import service.BoardsApi;

public class BoardsServiceSteps {
    public static BoardDto createBoard() {
        return BoardsApi.getBoardFromResponse(BoardsApi.requestBuilder()
                .withName("Some board")
                .withDescription("Description of some board")
                .setMethod(Method.POST)
                .buildRequest()
                .sendRequest());
    }

    public static BoardDto getBoard(String id) {
        return BoardsApi.getBoardFromResponse(BoardsApi.requestBuilder()
                .setId(id)
                .buildRequest()
                .sendRequest());
    }

    public static Response deleteBoard(String id) {
        return BoardsApi.requestBuilder()
                .setId(id)
                .setMethod(Method.DELETE)
                .setUrl(CommonValues.ID_PATH_PARAM)
                .buildRequest()
                .sendRequest();
    }
}
