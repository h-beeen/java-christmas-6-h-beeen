package christmas.exception;

import christmas.view.OutputWriter;

import java.time.DateTimeException;
import java.util.function.Supplier;

import static christmas.exception.ExceptionCode.INVALID_DATE;

public class ExceptionHandler {
    private ExceptionHandler() {
    }

    public static <T> T retryOnBusinessException(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (BusinessException exception) {
                OutputWriter.println(exception.getMessage());
            }
        }
    }

    public static <T> void tryOnDateTimeException(Supplier<T> supplier) {
        try {
            supplier.get();
        } catch (DateTimeException exception) {
            throw BusinessException.of(INVALID_DATE, exception);
        }
    }
}
