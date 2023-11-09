package christmas.utility;

import java.util.function.BooleanSupplier;

class Validator {
    protected static final String DELIMITER = ",";

    private Validator() {
    }

    public static BooleanSupplier isEndsWithDelimiter(String input) {
        return () -> input.endsWith(DELIMITER);
    }

    public static BooleanSupplier hasWhiteSpace(String input) {
        return () -> input.chars().anyMatch(Character::isWhitespace);
    }

    public static BooleanSupplier hasEnglish(String input) {
        return () -> input.chars().anyMatch(Character::isAlphabetic);
    }
}
