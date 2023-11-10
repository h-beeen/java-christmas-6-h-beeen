package christmas.domain;

import christmas.exception.ExceptionHandler;

import java.time.LocalDate;

import static christmas.domain.constants.EventConstraint.EVENT_MONTH;
import static christmas.domain.constants.EventConstraint.EVENT_YEAR;

public class VisitingDate {
    private final LocalDate visitingDate;

    //== Constructor ==//
    private VisitingDate(final int visitingDate) {
        ExceptionHandler.tryOnDateTimeException(() -> convertLocalDate(visitingDate));
        this.visitingDate = convertLocalDate(visitingDate);
    }

    //== Static Factory Method ==//
    public static VisitingDate create(final int visitingDate) {
        return new VisitingDate(visitingDate);
    }

    //== Validation Method ==//
    private LocalDate convertLocalDate(final int visitingDate) {
        return LocalDate.of(EVENT_YEAR.getValue(), EVENT_MONTH.getValue(), visitingDate);
    }
}
