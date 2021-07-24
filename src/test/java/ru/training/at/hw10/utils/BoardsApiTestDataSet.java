package ru.training.at.hw10.utils;

import java.util.HashMap;
import java.util.Map;
import ru.training.at.hw10.constants.BoardParameterName;

public class BoardsApiTestDataSet {

    public static Map<String, String> getNewBoardMinimalValidParameters() {
        Map<String, String> params = new HashMap<>();
        params.put(BoardParameterName.NAME, "NewAwesomeBoard");
        params.put(BoardParameterName.DESCRIPTION, "NewAwesomeBoardDescription");
        return params;
    }

    public static Map<String, String> getUpdatedBoardMinimalValidParameters() {
        Map<String, String> params = new HashMap<>();
        params.put(BoardParameterName.NAME, "UpdatedAwesomeBoard");
        params.put(BoardParameterName.DESCRIPTION, "UpdatedAwesomeBoardDescription");
        return params;
    }

}
