package christmas.domain.constants;

public enum EventConstraint {
    EVENT_YEAR(2023),
    EVENT_MONTH(12);

    private final int value;

    EventConstraint(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
