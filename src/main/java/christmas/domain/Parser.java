package christmas.domain;

import christmas.exception.BusinessException;

import static christmas.exception.ErrorMessage.INVALID_DATE;

public class Parser {
    private Parser() {
    }

    public static int parseDate(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            throw BusinessException.of(INVALID_DATE, exception);
        }
    }
}
