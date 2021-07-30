package ru.training.at.hw10.tests;

import beans.BoardDto;
import constants.CommonValues;
import io.restassured.http.Method;
import java.util.List;
import org.testng.annotations.BeforeMethod;
import ru.training.at.hw10.steps.BoardsServiceSteps;
import ru.training.at.hw10.utils.ResponseValidator;
import service.BoardsApi;


public abstract class AbstractTest {

    @BeforeMethod
    protected void setUp() {
        deleteAllBoards();
    }

    protected BoardDto createTestBoard() {
        return BoardsServiceSteps.createBoard();
    }

    private void deleteAllBoards() {
        List<BoardDto> boards = BoardsApi.requestBuilder()
                .buildRequest()
                .getAllBoards();
        for (BoardDto board : boards) {
            if (board.getIdMemberCreator().equals(CommonValues.MEMBER_CREATOR_ID)) {
                BoardsApi.requestBuilder()
                        .setMethod(Method.DELETE)
                        .setId(board.getId())
                        .setUrl(CommonValues.ID_PATH_PARAM)
                        .buildRequest()
                        .sendRequest()
                        .then()
                        .spec(ResponseValidator.success());
            }
        }
    }
}
