package christmas.domain.order;

import christmas.exception.ExceptionHandler;

import java.time.LocalDate;

import static christmas.domain.order.constants.PlannerConstraint.PROMOTION_MONTH;
import static christmas.domain.order.constants.PlannerConstraint.PROMOTION_YEAR;

public class VisitDay {
    private final LocalDate visitDay;

    //== Constructor ==//
    private VisitDay(final int visitDay) {
        this.visitDay = ExceptionHandler.tryOnFormatException(() -> convertLocalDate(visitDay));
    }

    //== Static Factory Method ==//
    public static VisitDay create(final int visitDay) {
        return new VisitDay(visitDay);
    }

    //== Utility Method ==//
    private LocalDate convertLocalDate(final int visit) {
        return LocalDate.of(PROMOTION_YEAR.getValue(), PROMOTION_MONTH.getValue(), visit);
    }

    public boolean isInPromotionPeriod(
            LocalDate startDate,
            LocalDate endDate
    ) {
        return !visitDay.isBefore(startDate) && !visitDay.isAfter(endDate);
    }

    public Integer multiplyDate(final int valueToMultiply) {
        return visitDay.getDayOfMonth() * valueToMultiply;
    }

    //== Getter (Only permit to use Dto/ResponseMapper) ==//
    public LocalDate getVisitDay() {
        return visitDay;
    }
}
