package christmas.view.constants;

public enum ResponseFormat {
    PRODUCT_QUANTITY_RESULT("%s %d개"),
    PRICE_RESULT("%,d원"),
    MINUS_PRICE_RESULT("-%,d원"),
    BENEFIT_PRICE_RESULT("%s: -%,d원"),
    GIFT_RESULT("증정 이벤트: -%,d원"),
    PROMOTION_PREVIEW("%d월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");

    private final String format;

    ResponseFormat(String format) {
        this.format = format;
    }

    //== Utility Method ==//
    public String generateFormat(Object... objects) {
        return String.format(format, objects);
    }
}
