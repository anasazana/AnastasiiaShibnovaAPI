package ru.training.at.hw10.tests;

import static org.hamcrest.MatcherAssert.assertThat;
import static service.BoardsApi.getBoardFromResponse;
import static service.BoardsApi.requestBuilder;

import beans.BoardDto;
import constants.BoardParameter;
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
        String boardId = BoardsApi.getBoardFromResponse(requestBuilder()
                .withName(parameters.get(BoardParameter.NAME))
                .withDescription(parameters.get(BoardParameter.DESC))
                .withMethod(Method.POST)
                .buildRequest()
                .sendRequest()).getId();
        BoardDto receivedBoard = BoardsServiceSteps.getBoard(boardId);
        assertThat(
                "Response should contain proper board",
                receivedBoard,
                Matchers.hasProperty(
                        BoardParameter.ID,
                        Matchers.is(boardId)
                )
        );
    }

    //2
    @Test
    public void createBoardWithNoParameters() {
        requestBuilder()
                .withMethod(Method.POST)
                .buildRequest()
                .sendRequest()
                .then()
                .spec(ResponseValidator.badRequestPlainText());
    }

    //3
    @Test
    public void createMaxAvailableNumberOfBoardsForNonPremiumUser() {
        for (int i = 0; i < CommonValues.MAX_BOARDS_AVAILABLE_NON_PREMIUM; i++) {
            BoardsServiceSteps.createBoard();
        }
        requestBuilder()
                .withMethod(Method.POST)
                .withName("failed query")
                .buildRequest()
                .sendRequest()
                .then()
                .spec(ResponseValidator.badRequest());
    }

    //4
    @Test
    public void getExistingBoard() {
        String boardId = createTestBoard().getId();
        BoardDto receivedBoard = BoardsServiceSteps.getBoard(boardId);
        assertThat(
                "Response should contain proper board",
                receivedBoard,
                Matchers.hasProperty(
                        BoardParameter.ID,
                        Matchers.is(boardId)
                )
        );
    }

    //5
    @Test
    public void getNonExistingBoard() {
        String boardId = createTestBoard().getId();
        BoardsServiceSteps.deleteBoard(boardId);
        checkThatBoardNotFound(boardId);
    }

    //6
    @Test(dataProvider = "updateBoardDataProvider",
            dataProviderClass = DataProvidersForBoardsApi.class)
    public void updateBoardTest(Map<String, Object> parameters) {
        String boardId = createTestBoard().getId();

        String name = (String) parameters.get(BoardParameter.NAME);
        String desc = (String) parameters.get(BoardParameter.DESC);
        boolean closed = (boolean) parameters.get(BoardParameter.CLOSED);

        BoardDto updatedBoard = getBoardFromResponse(
                requestBuilder()
                        .withMethod(Method.PUT)
                        .withId(boardId)
                        .withUrl(CommonValues.ID_PATH_PARAM)
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
                        Matchers.hasProperty(BoardParameter.NAME, Matchers.is(name)),
                        Matchers.hasProperty(BoardParameter.DESC, Matchers.is(desc)),
                        Matchers.hasProperty(BoardParameter.CLOSED, Matchers.is(closed))
                )
        );
    }

    //7
    @Test
    public void updateNonExistingBoardTest() {
        String boardId = createTestBoard().getId();
        BoardsServiceSteps.deleteBoard(boardId);
        BoardsApi.requestBuilder()
                .withMethod(Method.PUT)
                .withUrl(CommonValues.ID_PATH_PARAM)
                .withId(boardId)
                .withName("failed query")
                .buildRequest()
                .sendRequest()
                .then()
                .spec(ResponseValidator.notFound());
    }

    //8
    @Test
    public void deleteBoardTest() {
        String boardId = createTestBoard().getId();
        BoardsServiceSteps.deleteBoard(boardId);
        checkThatBoardNotFound(boardId);
    }

    //9
    @Test
    public void deleteNonExistingBoardTest() {
        String boardId = createTestBoard().getId();
        BoardsServiceSteps.deleteBoard(boardId);
        BoardsApi.requestBuilder()
                .withId(boardId)
                .withMethod(Method.DELETE)
                .withUrl(CommonValues.ID_PATH_PARAM)
                .buildRequest()
                .sendRequest()
                .then()
                .spec(ResponseValidator.notFound());
    }

    //10
    @Test
    public void getFieldOnBoardTest() {
        BoardDto testBoard = createTestBoard();
        Response getFieldResponse = BoardsApi
                .requestBuilder()
                .withField(BoardParameter.NAME)
                .withId(testBoard.getId())
                .withUrl(CommonValues.ID_PATH_PARAM + CommonValues.FIELD_PATH_PARAM)
                .buildRequest()
                .sendRequest();
        getFieldResponse.then().assertThat().body("_value",
                Matchers.containsString(testBoard.getName()));
    }

    private void checkThatBoardNotFound(String boardId) {
        BoardsApi.requestBuilder()
                .withId(boardId)
                .withUrl(CommonValues.ID_PATH_PARAM)
                .buildRequest()
                .sendRequest()
                .then()
                .spec(ResponseValidator.notFound());
    }
}
