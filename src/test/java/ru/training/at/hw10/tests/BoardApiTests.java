package ru.training.at.hw10.tests;

import static org.hamcrest.MatcherAssert.assertThat;
import static service.BoardsApi.getBoardFromResponse;
import static service.BoardsApi.requestBuilder;

import beans.BoardDto;
import constants.BoardFieldName;
import constants.BoardParameterName;
import constants.CommonValues;
import io.restassured.http.Method;
import io.restassured.response.Response;
import java.util.Map;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import ru.training.at.hw10.steps.BoardsServiceSteps;
import ru.training.at.hw10.utils.DataProvidersForBoardsApi;
import ru.training.at.hw10.utils.ResponseValidator;
import service.BoardsApi;

public class BoardApiTests extends AbstractTest {

    //1
    @Test(dataProvider = "createBoardDataProvider",
            dataProviderClass = DataProvidersForBoardsApi.class)
    public void createBoardWithValidParameters(Map<String, String> parameters) {
        BoardDto testBoard = BoardsApi.getBoardFromResponse(requestBuilder()
                .withName(parameters.get(BoardParameterName.NAME))
                .withDescription(parameters.get(BoardParameterName.DESCRIPTION))
                .setMethod(Method.POST)
                .buildRequest()
                .sendRequest());
        BoardDto receivedBoard = BoardsApi.getBoardFromResponse(
                requestBuilder()
                        .setId(testBoard.getId())
                        .setUrl(CommonValues.ID_PATH_PARAM)
                        .buildRequest()
                        .sendRequest());
        assertThat(
                "Response should contain proper board",
                receivedBoard,
                Matchers.hasProperty(
                        BoardParameterName.ID,
                        Matchers.is(testBoard.getId())
                )
        );
    }

    //2
    @Test
    public void createBoardWithNoParameters() {
        requestBuilder()
                .setMethod(Method.POST)
                .buildRequest()
                .sendRequest()
                .then()
                .spec(ResponseValidator.badRequestPlainText());
    }

    //3
    @Test
    public void createMaxAvailableNumberOfBoardsForNonPremiumUser() {
        int testNumberOfBoards = CommonValues.MAX_BOARDS_AVAILABLE_NON_PREMIUM + 1;
        for (int i = 1; i <= testNumberOfBoards; i++) {
            Response createBoardResponse = requestBuilder()
                    .setMethod(Method.POST)
                    .withName("Board №" + i)
                    .buildRequest()
                    .sendRequest();
            if (i < testNumberOfBoards) {
                createBoardResponse.then().spec(ResponseValidator.success());
            } else {
                createBoardResponse.then().spec(ResponseValidator.badRequest());
            }
        }
    }

    //4
    @Test
    public void getExistingBoard() {
        String testBoardId = createTestBoard().getId();
        BoardDto receivedBoard = BoardsApi.getBoardFromResponse(requestBuilder()
                .setId(testBoardId)
                .setUrl(CommonValues.ID_PATH_PARAM)
                .buildRequest()
                .sendRequest());
        assertThat(
                "Response should contain proper board",
                receivedBoard,
                Matchers.hasProperty(
                        BoardParameterName.ID,
                        Matchers.is(testBoardId)
                )
        );
    }

    //5
    @Test
    public void getNonExistingBoard() {
        String testBoardId = createTestBoard().getId();
        BoardsServiceSteps.deleteBoard(testBoardId);
        requestBuilder()
                .setId(testBoardId)
                .setUrl(CommonValues.ID_PATH_PARAM)
                .buildRequest()
                .sendRequest()
                .then()
                .spec(ResponseValidator.notFound());
    }

    //6
    @Test(dataProvider = "updateBoardDataProvider",
            dataProviderClass = DataProvidersForBoardsApi.class)
    public void updateBoardTest(Map<String, Object> parameters) {
        BoardDto testBoard = createTestBoard();

        String name = (String) parameters.get(BoardParameterName.NAME);
        String desc = (String) parameters.get(BoardParameterName.DESCRIPTION);
        boolean closed = (boolean) parameters.get(BoardParameterName.CLOSED);

        BoardDto updatedBoard = getBoardFromResponse(
                requestBuilder()
                        .setMethod(Method.PUT)
                        .setId(testBoard.getId())
                        .setUrl(CommonValues.ID_PATH_PARAM)
                        .withName(name)
                        .withDescription(desc)
                        .closed(closed)
                        .buildRequest()
                        .sendRequest()
        );
        assertThat(
                "Board in response should be updated",
                updatedBoard,
                Matchers.allOf(
                        Matchers.hasProperty(BoardParameterName.NAME, Matchers.is(name)),
                        Matchers.hasProperty(BoardParameterName.DESCRIPTION, Matchers.is(desc)),
                        Matchers.hasProperty(BoardParameterName.CLOSED, Matchers.is(closed))
                )
        );
    }

    //7
    @Test
    public void updateNonExistingBoardTest() {
        BoardDto testBoard = createTestBoard();
        BoardsServiceSteps.deleteBoard(testBoard.getId());
        BoardsApi.requestBuilder()
                .setMethod(Method.PUT)
                .setUrl(CommonValues.ID_PATH_PARAM)
                .setId(testBoard.getId())
                .withName("Failed update")
                .buildRequest()
                .sendRequest()
                .then()
                .spec(ResponseValidator.notFound());
    }

    //8
    @Test
    public void deleteBoardTest() {
        BoardDto testBoard = createTestBoard();
        BoardsServiceSteps.deleteBoard(testBoard.getId());
        BoardsApi.requestBuilder()
                .setId(testBoard.getId())
                .setUrl(CommonValues.ID_PATH_PARAM)
                .buildRequest()
                .sendRequest()
                .then()
                .spec(ResponseValidator.notFound());
    }

    //9
    @Test
    public void deleteNonExistingBoardTest() {
        BoardDto testBoard = createTestBoard();
        for (int i = 0; i < 2; i++) {
            Response deleteBoardResponse = BoardsServiceSteps.deleteBoard(testBoard.getId());
            if (i == 0) {
                deleteBoardResponse.then().spec(ResponseValidator.success());
            } else {
                deleteBoardResponse.then().spec(ResponseValidator.notFound());
            }
        }
    }

    //10
    @Test
    public void getFieldOnBoardTest() {
        BoardDto testBoard = createTestBoard();
        Response getFieldResponse = BoardsApi
                .requestBuilder()
                .setField(BoardFieldName.NAME)
                .setId(testBoard.getId())
                .setUrl("/{id}/{field}")
                .buildRequest()
                .sendRequest();
        getFieldResponse.then().assertThat().body("_value", Matchers.containsString(testBoard.getName()));
    }
}
