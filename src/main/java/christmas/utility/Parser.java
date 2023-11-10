package christmas.utility;

import christmas.domain.menu.Menu;
import christmas.exception.BusinessException;
import christmas.exception.ExceptionHandler;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.function.BooleanSupplier;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static christmas.exception.ErrorCode.INVALID_ORDER;

public class Parser {
    private static final String DELIMITER = ",";
    private static final String HYPHEN = "-";
    private static final String REGEX = "^[가-힣]+-\\d{1,20}$";

    private Parser() {
    }

    //== Business Logic ==//
    public static int parseDateInput(String dateInput) {
        return ExceptionHandler.tryOnSpecificException(() -> Integer.parseInt(dateInput));
    }

    public static EnumMap<Menu, Integer> parseMenuOrdersInputByDelimiter(String menuOrderInput) {
        INVALID_ORDER.validate(isEndsWithDelimiter(menuOrderInput));
        INVALID_ORDER.validate(hasWhitespace(menuOrderInput));

        List<String> parsedByDelimiterMenuOrders = Arrays.asList(splitByDelimiter(menuOrderInput));

        return parseMenuOrdersInputByHyphen(parsedByDelimiterMenuOrders);
    }

    private static String[] splitByDelimiter(String menuOrdersInput) {
        return menuOrdersInput.split(DELIMITER);
    }

    private static EnumMap<Menu, Integer> parseMenuOrdersInputByHyphen(List<String> parsedByDelimiterMenuOrders) {
        INVALID_ORDER.validate(areInvalidPattern(parsedByDelimiterMenuOrders));

        return parsedByDelimiterMenuOrders.stream()
                .map(splitMenuOrdersInputByHyphen())
                .collect(Collectors.toMap(
                        Parser::extractMenu,
                        Parser::extractQuantity,
                        Parser::validateDuplicate,
                        Parser::createEnumMap)
                );
    }

    private static Function<String, String[]> splitMenuOrdersInputByHyphen() {
        return menu -> menu.split(HYPHEN);
    }

    private static Menu extractMenu(String[] parts) {
        return Menu.findMenuByName(parts[0]);
    }

    private static int extractQuantity(String[] parts) {
        return ExceptionHandler.tryOnSpecificException(() -> Integer.parseInt(parts[1]));
    }

    private static EnumMap<Menu, Integer> createEnumMap() {
        return new EnumMap<>(Menu.class);
    }

    //== Validation Method ==//
    private static BooleanSupplier isEndsWithDelimiter(String input) {
        return () -> input.endsWith(DELIMITER);
    }

    private static BooleanSupplier hasWhitespace(String input) {
        return () -> input.chars().anyMatch(Character::isWhitespace);
    }

    private static BooleanSupplier areInvalidPattern(List<String> inputs) {
        return () -> inputs.stream()
                .anyMatch(Parser::isInvalidPattern);
    }

    private static Integer validateDuplicate(Integer existing, Integer replacement) {
        throw BusinessException.from(INVALID_ORDER);
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
