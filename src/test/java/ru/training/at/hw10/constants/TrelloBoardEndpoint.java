package ru.training.at.hw10.constants;

public enum TrelloBoardEndpoint {
    GET_ALL_BOARDS("1/member/me/boards"),
    CREATE_BOARD("1/boards"),
    GET_BOARD("1/boards/{id}"),
    UPDATE_BOARD("1/boards/{id}"),
    DELETE_BOARD("1/boards/{id}"),
    GET_FIELD_ON_BOARD("1/boards/{id}/{field}");

    private final String path;

    TrelloBoardEndpoint(String path) {
        this.path = CommonValues.BASE_URL + "/" + path;
    }

    public String getUrl() {
        return path;
    }
}
