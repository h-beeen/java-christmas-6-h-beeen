package christmas.domain;

import christmas.exception.ExceptionHandler;

import java.time.LocalDate;

import static christmas.domain.constants.EventConstraint.EVENT_MONTH;
import static christmas.domain.constants.EventConstraint.EVENT_YEAR;

public class VisitingDate {
    private final LocalDate visitingDate;

    //== Constructor ==//
    private VisitingDate(final int visitingDate) {
        validateDate(visitingDate);

        this.visitingDate = eventDate(visitingDate);
    }

    //== Static Factory Method ==//
    public static VisitingDate create(final int visitingDate) {
        return new VisitingDate(visitingDate);
    }

    //== Utility Method ==//
    private static LocalDate eventDate(final int visitingDate) {
        return LocalDate.of(
                EVENT_YEAR.getValue(),
                EVENT_MONTH.getValue(),
                visitingDate
        );
    }

    //== Validation Method ==//
    private void validateDate(final int visitingDate) {
        ExceptionHandler.tryOnDateTimeException(() -> eventDate(visitingDate));
    }
}
