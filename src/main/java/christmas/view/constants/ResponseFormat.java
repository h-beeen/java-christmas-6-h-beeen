package christmas.view.constants;

public enum ResponseFormat {
    ORDERS_RESULT("%s %d개"),
    TOTAL_ORIGIN_PRICE_RESULT("%,d원"),
    PROMOTION_PREVIEW("%d월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"),
    BENEFIT_RESULT("%s: %,d원");

    private final String format;

    ResponseFormat(String format) {
        this.format = format;
    }

    //== Utility Method ==//
    public String generateFormat(Object... objects) {
        return String.format(format, objects);
    }
}
