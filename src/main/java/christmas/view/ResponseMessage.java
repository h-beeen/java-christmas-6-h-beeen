package christmas.view;

import static christmas.domain.constants.EventConstraint.EVENT_MONTH;

public enum ResponseMessage {
    WELCOME_MESSAGE(
            String.format(
                    "안녕하세요! 우테코 식당 %d월 이벤트 플래너입니다.",
                    EVENT_MONTH.getValue())),
    REQUEST_VISITING_DATE(
            String.format(
                    "%d월 중 식당 예상 방문 날짜는 언제인가요?",
                    EVENT_MONTH.getValue())),
    REQUEST_ORDER_MENU("주문하실 메뉴를 메뉴와 개수를 알려 주세요. " +
            "(e.g 해산물파스타-2,레드와인-1,초코케이크-1)");

    private final String message;

    ResponseMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
