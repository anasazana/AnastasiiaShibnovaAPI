package ru.training.at.hw10.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ru.training.at.hw10.beans.BoardDto;
import ru.training.at.hw10.constants.BoardParameterName;
import ru.training.at.hw10.constants.CommonValues;
import ru.training.at.hw10.constants.TrelloBoardEndpoint;

public class BoardsApi {

    private final Map<String, String> parameters;
    private final String id;

    private BoardsApi(Map<String, String> parameters, String id) {
        this.parameters = parameters;
        this.id = id;
    }

    public Response createBoard() {
        return RestAssured.given(requestSpecification())
                .log().all()
                .contentType(ContentType.JSON)
                .queryParams(parameters)
                .post(TrelloBoardEndpoint.CREATE_BOARD.getUrl())
                .prettyPeek();
    }

    public Response updateBoard() {
        return RestAssured.given(requestSpecification())
                .log().all()
                .contentType(ContentType.JSON)
                .queryParams(parameters)
                .put(TrelloBoardEndpoint.UPDATE_BOARD.getUrl(), id)
                .prettyPeek();
    }

    public Response getBoard() {
        return RestAssured.given(requestSpecification())
                .log().all()
                .contentType(ContentType.JSON)
                .queryParams(parameters)
                .get(TrelloBoardEndpoint.GET_BOARD.getUrl(), id)
                .prettyPeek();
    }

    public Response deleteBoard() {
        return RestAssured.given(requestSpecification())
                .log().all()
                .contentType(ContentType.JSON)
                .queryParams(parameters)
                .delete(TrelloBoardEndpoint.DELETE_BOARD.getUrl(), id)
                .prettyPeek();
    }

    public Response getAllBoards() {
        return RestAssured.given(requestSpecification())
                .log().all()
                .contentType(ContentType.JSON)
                .queryParams(parameters)
                .get(TrelloBoardEndpoint.GET_ALL_BOARDS.getUrl())
                .prettyPeek();
    }

    public Response getFieldOnBoard(String fieldName) {
        return RestAssured.given(requestSpecification())
                .log().all()
                .contentType(ContentType.JSON)
                .queryParams(parameters)
                .get(TrelloBoardEndpoint.GET_FIELD_ON_BOARD.getUrl(), id, fieldName)
                .prettyPeek();
    }

    public static List<BoardDto> getAllBoardsFromResponse(Response response) {
        return new Gson().fromJson(response.asString(), new TypeToken<List<BoardDto>>(){}.getType());
    }

    public static BoardDto getBoardFromResponse(Response response) {
        return new Gson().fromJson(response.asString(), BoardDto.class);
    }

    private RequestSpecification requestSpecification() {
        return new RequestSpecBuilder()
                .setAccept(ContentType.JSON)
                .setBaseUri(CommonValues.BASE_URL)
                .build();
    }

    public static BoardsApiRequestBuilder requestBuilder() {
        return new BoardsApiRequestBuilder();
    }

    public static class BoardsApiRequestBuilder {

        private final Map<String, String> parameters = new HashMap<>();
        private String id;

        public BoardsApiRequestBuilder setId(String id) {
            this.id = id;
            return this;
        }

        public BoardsApiRequestBuilder addParameter(String parameter, String value) {
            parameters.put(parameter, value);
            return this;
        }

        public BoardsApiRequestBuilder addParameters(Map<String, String> parameters) {
            this.parameters.putAll(parameters);
            return this;
        }

        public BoardsApi buildRequest() {
            parameters.put(BoardParameterName.KEY, CommonValues.KEY);
            parameters.put(BoardParameterName.TOKEN, CommonValues.TOKEN);
            return new BoardsApi(new HashMap<>(parameters), id);
        }
    }
}
