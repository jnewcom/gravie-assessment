package assessment.model;

public enum Resource {
    GAME("game");

    private String value;

    Resource(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
