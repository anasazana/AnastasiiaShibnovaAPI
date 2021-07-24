package ru.training.at.hw10.steps;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import java.util.List;
import java.util.Map;
import org.hamcrest.Matchers;
import ru.training.at.hw10.beans.BoardDto;
import ru.training.at.hw10.constants.CommonValues;
import ru.training.at.hw10.service.BoardsApi;
import ru.training.at.hw10.utils.BoardsResponseValidator;

public class BoardsServiceSteps {

    public static Response createBoard(Map<String, String> parameters) {
        return BoardsApi.requestBuilder()
                .addParameters(parameters)
                .buildRequest()
                .createBoard();
    }

    public static Response getBoard(String id) {
        return BoardsApi.requestBuilder()
                .setId(id)
                .buildRequest()
                .getBoard();
    }

    public static Response deleteBoard(String id) {
        return BoardsApi.requestBuilder()
                .setId(id)
                .buildRequest()
                .deleteBoard();
    }

    public static Response updateBoard(Map<String, String> parameters, String id) {
        return BoardsApi.requestBuilder()
                .addParameters(parameters)
                .setId(id)
                .buildRequest()
                .updateBoard();
    }

    public static Response getAllBoards() {
        return BoardsApi.requestBuilder()
                .buildRequest()
                .getAllBoards();
    }

    public static void deleteAllMyBoards(List<BoardDto> boards) {
        for (BoardDto board : boards) {
            if (board.getIdMemberCreator().equals(CommonValues.MEMBER_CREATOR_ID)) {
                BoardsResponseValidator.checkResponseIsOK(deleteBoard(board.getId()));
            }
        }
    }

    public static Response getFieldOnBoard(String id, String fieldName) {
        return BoardsApi.requestBuilder()
                .setId(id)
                .buildRequest()
                .getFieldOnBoard(fieldName);
    }

    public static void checkResponseContainsExpectedParameters(Response response, Map<String, String> parameters) {
        ValidatableResponse updatedResponse = response.then().assertThat();
        for (Map.Entry<String, String> parameter : parameters.entrySet()) {
            updatedResponse.body(parameter.getKey(), Matchers.containsString(parameter.getValue()));
        }
    }
}
