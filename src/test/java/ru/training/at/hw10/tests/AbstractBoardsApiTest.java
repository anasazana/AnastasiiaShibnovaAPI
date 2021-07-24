package ru.training.at.hw10.tests;

import io.restassured.response.Response;
import java.util.List;
import org.testng.annotations.BeforeMethod;
import ru.training.at.hw10.beans.BoardDto;
import ru.training.at.hw10.service.BoardsApi;
import ru.training.at.hw10.steps.BoardsServiceSteps;
import ru.training.at.hw10.utils.BoardsApiTestDataSet;
import ru.training.at.hw10.utils.BoardsResponseValidator;

public abstract class AbstractBoardsApiTest {

    @BeforeMethod
    protected void setUp() {
        cleanUpBoards();
    }

    protected BoardDto createTestBoard() {
        Response createBoardResponse = BoardsServiceSteps
                .createBoard(BoardsApiTestDataSet.getNewBoardMinimalValidParameters());
        BoardsResponseValidator.checkResponseIsOK(createBoardResponse);
        return BoardsApi.getBoardFromResponse(createBoardResponse);
    }

    private void cleanUpBoards() {
        Response getAllBoardsResponse = BoardsServiceSteps.getAllBoards();
        BoardsResponseValidator.checkResponseIsOK(getAllBoardsResponse);

        List<BoardDto> allBoards = BoardsApi.getAllBoardsFromResponse(getAllBoardsResponse);
        BoardsServiceSteps.deleteAllMyBoards(allBoards);
    }
}
