package christmas.exception;

import java.util.function.BooleanSupplier;

public enum ErrorCode {
    INVALID_DATE("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    INVALID_ORDER("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    ORDERS_ONLY_CONTAIN_BEVERAGES("음료만 주문할 수 없습니다. 다시 입력해 주세요."),
    EXCEED_ORDER_QUANTITY_LIMIT("최대 주문 갯수를 초과했습니다. 다시 입력해 주세요.");

    public static final String ERROR_PREFIX = "[ERROR] ";
    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public void validate(BooleanSupplier condition) {
        if (isSameCondition(condition)) {
            throw BusinessException.from(this);
        }
    }

    private boolean isSameCondition(BooleanSupplier condition) {
        return condition.getAsBoolean();
    }

    public String getMessage() {
        return ERROR_PREFIX + message;
    }
}
