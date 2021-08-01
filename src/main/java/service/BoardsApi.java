package service;

import beans.BoardDto;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import constants.BoardParameter;
import constants.CommonValues;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardsApi {
    public static final String BOARDS_BASE_URL = "/1/boards";
    public static final String ALL_BOARDS_URL = "/1/member/me/boards";

    private final Map<String, String> parameters;
    private final Map<String, String> pathParams;
    private final Method method;
    private final String url;

    private BoardsApi(Map<String, String> parameters, Map<String, String> pathParams, Method method, String url) {
        this.parameters = parameters;
        this.pathParams = pathParams;
        this.method = method;
        this.url = url;
    }

    public Response sendRequest() {
        return RestAssured
                .given(requestSpecification())
                .request(method, BOARDS_BASE_URL + url)
                .prettyPeek();
    }

    public List<BoardDto> getAllBoards() {
        return getAllBoardsFromResponse(
                RestAssured
                        .given(requestSpecification())
                        .get(ALL_BOARDS_URL)
                        .prettyPeek()
        );
    }

    public static List<BoardDto> getAllBoardsFromResponse(Response response) {
        return new Gson().fromJson(response.asString(), new TypeToken<List<BoardDto>>(){}.getType());
    }

    public static BoardDto getBoardFromResponse(Response response) {
        return new Gson().fromJson(response.asString(), BoardDto.class);
    }

    private RequestSpecification requestSpecification() {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .setBaseUri(CommonValues.BASE_URL)
                .build()
                .log().ifValidationFails()
                .queryParams(parameters)
                .pathParams(pathParams);
    }

    public static BoardsApiRequestBuilder requestBuilder() {
        return new BoardsApiRequestBuilder();
    }

    public static class BoardsApiRequestBuilder {
        private final Map<String, String> parameters = new HashMap<>();
        private final Map<String, String> pathParams = new HashMap<>();

        private Method method = Method.GET;
        private String url = "";

        public BoardsApiRequestBuilder withUrl(String url) {
            this.url = url;
            return this;
        }

        public BoardsApiRequestBuilder withMethod(Method method) {
            this.method = method;
            return this;
        }

        public BoardsApiRequestBuilder withField(String field) {
            this.pathParams.put(BoardParameter.FIELD, field);
            return this;
        }

        public BoardsApiRequestBuilder withId(String id) {
            this.pathParams.put(BoardParameter.ID, id);
            return this;
        }

        public BoardsApiRequestBuilder withDescription(String desc) {
            parameters.put(BoardParameter.DESC, desc);
            return this;
        }

        public BoardsApiRequestBuilder withName(String name) {
            parameters.put(BoardParameter.NAME, name);
            return this;
        }

        public BoardsApiRequestBuilder closed(boolean closed) {
            parameters.put(BoardParameter.CLOSED, Boolean.toString(closed));
            return this;
        }

        public BoardsApi buildRequest() {
            parameters.put(BoardParameter.KEY, CommonValues.KEY);
            parameters.put(BoardParameter.TOKEN, CommonValues.TOKEN);
            return new BoardsApi(new HashMap<>(parameters),  new HashMap<>(pathParams), method, url);
        }
    }
}
