package ru.training.at.hw10.utils;

import constants.BoardParameter;
import java.util.Map;
import org.testng.annotations.DataProvider;

public class DataProvidersForBoardsApi {

    @DataProvider
    public static Object[][] createBoardDataProvider() {
        return new Object[][]{
                {
                        Map.of(
                                BoardParameter.NAME, "NewAwesomeBoard",
                                BoardParameter.DESC, "NewAwesomeBoardDescription"
                        )
                }
        };
    }

    @DataProvider
    public static Object[][] updateBoardDataProvider() {
        return new Object[][]{
                {
                        Map.of(
                                BoardParameter.NAME, "UpdatedAwesomeBoard",
                                BoardParameter.DESC, "UpdatedAwesomeBoardDescription",
                                BoardParameter.CLOSED, true
                        )
                },
                {
                        Map.of(
                                BoardParameter.NAME, "UpdatedAwesomeBoard2",
                                BoardParameter.DESC, "UpdatedAwesomeBoardDescription2",
                                BoardParameter.CLOSED, true
                        )
                }
        };
    }
}
