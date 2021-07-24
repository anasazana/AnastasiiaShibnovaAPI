package ru.training.at.hw10.utils;

import org.testng.annotations.DataProvider;
import ru.training.at.hw10.constants.BoardFieldName;

public class DataProvidersForBoardsApi {

    @DataProvider
    public static Object[][] createBoardDataProvider() {
        return new Object[][]{
                {BoardsApiTestDataSet.getNewBoardMinimalValidParameters()}
        };
    }

    @DataProvider
    public Object[][] updateBoardDataProvider() {
        return new Object[][]{
                {BoardsApiTestDataSet.getUpdatedBoardMinimalValidParameters()}
        };
    }

    @DataProvider
    public Object[][] getFieldOnBoardDataProvider() {
        return new Object[][]{
                {BoardFieldName.ID_MEMBER_CREATOR},
                {BoardFieldName.DESC},
                {BoardFieldName.NAME}
        };
    }
}
