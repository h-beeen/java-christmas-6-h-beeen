package christmas.utility;

import christmas.domain.constants.Menu;
import christmas.exception.BusinessException;

import java.util.EnumMap;

import static christmas.exception.ExceptionCode.INVALID_DATE;
import static christmas.exception.ExceptionCode.INVALID_ORDER;
import static christmas.utility.Validator.*;

public class Parser {
    private Parser() {
    }

    public static int parseDateInput(String dateInput) {
        try {
            return Integer.parseInt(dateInput);
        } catch (NumberFormatException exception) {
            throw BusinessException.of(INVALID_DATE, exception);
        }
    }

    public static EnumMap<Menu, Integer> parseMenuOrdersInput(String input) {
        try {
            INVALID_ORDER.validate(isEndsWithDelimiter(input));
            INVALID_ORDER.validate(hasWhiteSpace(input));
            INVALID_ORDER.validate(hasEnglish(input));

            return
        } catch (BusinessException exception) {
            throw BusinessException.of(INVALID_DATE, exception);
        }
    }
}
