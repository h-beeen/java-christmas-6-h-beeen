package christmas.utility;

import christmas.domain.constants.Menu;
import christmas.exception.BusinessException;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.function.BooleanSupplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static christmas.exception.ExceptionCode.INVALID_DATE;
import static christmas.exception.ExceptionCode.INVALID_ORDER;

public class Parser {
    private static final String DELIMITER = ",";
    private static final String REGEX = "^[가-힣]+-\\d{1,20}$";

    private Parser() {
    }

    //== Business Logic ==//
    public static int parseDateInput(String dateInput) {
        try {
            return Integer.parseInt(dateInput);
        } catch (NumberFormatException exception) {
            throw BusinessException.of(INVALID_DATE, exception);
        }
    }

    public static EnumMap<Menu, Integer> parseMenuOrdersInput(String menusOrderInput) {
        INVALID_ORDER.validate(isEndsWithDelimiter(menusOrderInput));
        INVALID_ORDER.validate(hasWhitespace(menusOrderInput));

        List<String> parsedByDelimiterMenusOrder = Arrays.asList(splitByDelimiter(menusOrderInput));

        INVALID_ORDER.validate(areInvalidPattern(parsedByDelimiterMenusOrder));

        return new EnumMap<>(Menu.class);
    }

    //== Utility Method ==//
    private static String[] splitByDelimiter(String menusOrderInput) {
        return menusOrderInput.split(",");
    }

    //== Validation Method ==//
    private static BooleanSupplier isEndsWithDelimiter(String input) {
        return () -> input.endsWith(DELIMITER);
    }

    private static BooleanSupplier hasWhitespace(String input) {
        return () -> input.chars().anyMatch(Character::isWhitespace);
    }

    private static BooleanSupplier areInvalidPattern(List<String> input) {
        return () -> input.stream()
                .anyMatch(Parser::isInvalidPattern);
    }
    
    /**
     * == 정규표현식 제약 조건
     * 1, 정상적인 자음과 모음으로 조합된 한글 문자열 입력
     * 2. 하이픈 입력
     * 3. 하이픈 다음에 하나 이상의 숫자 입력
     */
    private static boolean isInvalidPattern(String input) {
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(input);
        return !matcher.matches();
    }
}
