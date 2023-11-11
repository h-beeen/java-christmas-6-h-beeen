package christmas.domain.order;

import christmas.exception.ExceptionHandler;

import java.time.LocalDate;

import static christmas.domain.order.constants.PlannerConstraint.PROMOTION_MONTH;
import static christmas.domain.order.constants.PlannerConstraint.PROMOTION_YEAR;

public class VisitingDate {
    private final LocalDate visitingDate;

    //== Constructor ==//
    private VisitingDate(final int visitingDate) {
        this.visitingDate = ExceptionHandler.tryOnFormatException(() -> convertLocalDate(visitingDate));
    }

    //== Static Factory Method ==//
    public static VisitingDate create(final int visitingDate) {
        return new VisitingDate(visitingDate);
    }

    //== Utility Method ==//
    private LocalDate convertLocalDate(final int visitingDate) {
        return LocalDate.of(PROMOTION_YEAR.getValue(), PROMOTION_MONTH.getValue(), visitingDate);
    }

    public boolean isInPromotionPeriod(
            LocalDate startDate,
            LocalDate endDate
    ) {
        return !visitingDate.isBefore(startDate) && !visitingDate.isAfter(endDate);
    }

    public Integer multiplyDate(final int valueToMultiply) {
        return visitingDate.getDayOfMonth() * valueToMultiply;
    }
}
