package christmas.view.constants;

import static christmas.domain.consumer.constants.PlannerConstraint.PROMOTION_MONTH;

public enum ResponseMessage {
    //== REQUEST MESSAGE ==//
    WELCOME_MESSAGE(
            String.format(
                    "안녕하세요! 우테코 식당 %d월 이벤트 플래너입니다.",
                    PROMOTION_MONTH.getValue())
    ),
    REQUEST_VISITING_DAY(
            String.format(
                    "%d월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)",
                    PROMOTION_MONTH.getValue())
    ),
    REQUEST_MENU_ORDERS("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g 해산물파스타-2,레드와인-1,초코케이크-1)"),

    //== RESPONSE MESSAGE ==//
    RESPONSE_NONEXISTENCE_RESPONSE("없음"),
    RESPONSE_MENU_ORDERS_RESULT("<주문 메뉴>"),
    RESPONSE_TOTAL_ORIGIN_PRICE_RESULT("<할인 전 총주문 금액>"),
    RESPONSE_GIFT_RESULT("<증정 메뉴>"),
    RESPONSE_BENEFIT_RESPONSE("<혜택 내역>"),
    RESPONSE_TOTAL_BENEFIT_RESULT("<총혜택 금액>"),
    RESPONSE_EXPECT_PAYMENT_RESULT("<할인 후 예상 결제 금액>"),
    RESPONSE_BADGE_RESULT(
            String.format(
                    "<%d월 이벤트 배지>",
                    PROMOTION_MONTH.getValue())
    );

    private final String message;

    ResponseMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
