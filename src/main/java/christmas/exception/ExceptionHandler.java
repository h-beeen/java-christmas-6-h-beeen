package christmas.exception;

import christmas.view.output.ErrorOutputWriter;

import java.time.DateTimeException;
import java.util.function.Supplier;

import static christmas.exception.ErrorCode.INVALID_DATE;

public class ExceptionHandler {
    private ExceptionHandler() {
    }

    public static <T> T retryOnBusinessException(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (BusinessException exception) {
                ErrorOutputWriter.println(exception.getMessage());
            }
        }
    }

    public static <T> T tryOnParseIntException(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (DateTimeException | NumberFormatException exception) {
            throw BusinessException.of(INVALID_DATE, exception);
        }
    }
}
