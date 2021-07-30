package ru.training.at.hw10.utils;

import constants.BoardParameterName;
import java.util.Map;
import org.testng.annotations.DataProvider;

public class DataProvidersForBoardsApi {

    @DataProvider
    public static Object[][] createBoardDataProvider() {
        return new Object[][]{
                {
                        Map.of(
                                BoardParameterName.NAME, "NewAwesomeBoard",
                                BoardParameterName.DESCRIPTION, "NewAwesomeBoardDescription"
                        )
                }
        };
    }

    @DataProvider
    public static Object[][] updateBoardDataProvider() {
        return new Object[][]{
                {
                        Map.of(
                                BoardParameterName.NAME, "UpdatedAwesomeBoard",
                                BoardParameterName.DESCRIPTION, "UpdatedAwesomeBoardDescription",
                                BoardParameterName.CLOSED, true
                        )
                },
                {
                        Map.of(
                                BoardParameterName.NAME, "UpdatedAwesomeBoard2",
                                BoardParameterName.DESCRIPTION, "UpdatedAwesomeBoardDescription2",
                                BoardParameterName.CLOSED, true
                        )
                }
        };
    }
}
