package ru.training.at.hw10.tests;

import static org.hamcrest.MatcherAssert.assertThat;

import io.restassured.response.Response;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import ru.training.at.hw10.beans.BoardDto;
import ru.training.at.hw10.constants.AssertionFailedMessage;
import ru.training.at.hw10.constants.BoardFieldName;
import ru.training.at.hw10.constants.BoardParameterName;
import ru.training.at.hw10.constants.CommonValues;
import ru.training.at.hw10.service.BoardsApi;
import ru.training.at.hw10.steps.BoardsServiceSteps;
import ru.training.at.hw10.utils.BoardsApiTestDataSet;
import ru.training.at.hw10.utils.BoardsResponseValidator;
import ru.training.at.hw10.utils.DataProvidersForBoardsApi;

public class BoardsApiTests extends AbstractBoardsApiTest {

    //1
    @Test(dataProvider = "createBoardDataProvider",
            dataProviderClass = DataProvidersForBoardsApi.class)
    public void createBoardWithValidParameters(Map<String, String> parameters) {
        Response createBoardResponse = BoardsServiceSteps.createBoard(parameters);
        BoardsResponseValidator.checkResponseIsOK(createBoardResponse);
        BoardDto testBoard = BoardsApi.getBoardFromResponse(createBoardResponse);

        Response getBoardResponse = BoardsServiceSteps.getBoard(testBoard.getId());
        BoardsResponseValidator.checkResponseIsOK(getBoardResponse);
        BoardDto receivedBoard = BoardsApi.getBoardFromResponse(getBoardResponse);

        assertThat(
                AssertionFailedMessage.NO_PROPER_BOARD_IN_RESPONSE,
                receivedBoard.getId(),
                Matchers.equalTo(testBoard.getId())
        );
    }

    //2
    @Test
    public void createBoardWithNoParameters() {
        Response createBoardResponse = BoardsServiceSteps.createBoard(new HashMap<>());
        BoardsResponseValidator.checkResponseHasBadRequestStatusPlainText(createBoardResponse);
    }

    //3
    @Test
    public void createMaxAvailableNumberOfBoardsForNonPremiumUser() {
        int testNumberOfBoards = CommonValues.MAX_BOARDS_AVAILABLE_NON_PREMIUM + 1;
        for (int i = 1; i <= testNumberOfBoards; i++) {
            Response createNewBoardResponse = BoardsServiceSteps.createBoard(Collections.singletonMap(
                    BoardParameterName.NAME,
                    CommonValues.DEFAULT_TEST_BOARD_NAME + i
            ));
            if (i < testNumberOfBoards) {
                BoardsResponseValidator.checkResponseIsOK(createNewBoardResponse);
            } else {
                BoardsResponseValidator.checkResponseHasBadRequestStatus(createNewBoardResponse);
            }
        }
    }

    //4
    @Test
    public void getExistingBoard() {
        BoardDto testBoard = createTestBoard();
        Response getBoardResponse = BoardsServiceSteps.getBoard(testBoard.getId());
        BoardsResponseValidator.checkResponseIsOK(getBoardResponse);
        BoardDto receivedBoard = BoardsApi.getBoardFromResponse(getBoardResponse);

        assertThat(
                AssertionFailedMessage.NO_PROPER_BOARD_IN_RESPONSE,
                receivedBoard.getId(),
                Matchers.equalTo(testBoard.getId())
        );
    }

    //5
    @Test
    public void getNonExistingBoard() {
        BoardDto testBoard = createTestBoard();
        BoardsServiceSteps.deleteBoard(testBoard.getId());
        Response getBoardResponse = BoardsServiceSteps.getBoard(testBoard.getId());
        BoardsResponseValidator.checkResponseIsNotFound(getBoardResponse);
    }

    //6
    @Test(dataProvider = "updateBoardDataProvider",
            dataProviderClass = DataProvidersForBoardsApi.class)
    public void updateBoardTest(Map<String, String> parameters) {
        BoardDto testBoard = createTestBoard();
        Response updateBoardResponse = BoardsServiceSteps.updateBoard(parameters, testBoard.getId());
        BoardsResponseValidator.checkResponseIsOK(updateBoardResponse);

        Response getUpdatedBoardResponse = BoardsServiceSteps.getBoard(testBoard.getId());
        BoardsResponseValidator.checkResponseIsOK(getUpdatedBoardResponse);
        BoardsServiceSteps.checkResponseContainsExpectedParameters(getUpdatedBoardResponse, parameters);
    }

    //7
    @Test
    public void updateNonExistingBoardTest() {
        BoardDto testBoard = createTestBoard();
        BoardsServiceSteps.deleteBoard(testBoard.getId());
        Response updateBoardResponse = BoardsServiceSteps
                .updateBoard(BoardsApiTestDataSet.getUpdatedBoardMinimalValidParameters(), testBoard.getId());
        BoardsResponseValidator.checkResponseIsNotFound(updateBoardResponse);
    }

    //8
    @Test
    public void deleteBoardTest() {
        BoardDto testBoard = createTestBoard();
        Response deleteBoardResponse = BoardsServiceSteps.deleteBoard(testBoard.getId());
        BoardsResponseValidator.checkResponseIsOK(deleteBoardResponse);
    }

    //9
    @Test
    public void deleteNonExistingBoardTest() {
        BoardDto testBoard = createTestBoard();
        for (int i = 0; i < 2; i++) {
            Response deleteBoardResponse = BoardsServiceSteps.deleteBoard(testBoard.getId());
            if (i == 0) {
                BoardsResponseValidator.checkResponseIsOK(deleteBoardResponse);
            } else {
                BoardsResponseValidator.checkResponseIsNotFound(deleteBoardResponse);
            }
        }
    }

    //10
    @Test
    public void getFieldOnBoardTest() {
        BoardDto testBoard = createTestBoard();
        Response getFieldOnBoardResponse = BoardsServiceSteps.getFieldOnBoard(testBoard.getId(), BoardFieldName.NAME);
        BoardsResponseValidator.checkResponseIsOK(getFieldOnBoardResponse);

        getFieldOnBoardResponse.then().assertThat().body(Matchers.containsString(testBoard.getName()));
    }

}
