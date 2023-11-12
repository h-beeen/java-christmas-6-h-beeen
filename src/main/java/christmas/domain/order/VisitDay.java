package christmas.domain.order;

import christmas.domain.promotion.constants.SpecialPromotion;
import christmas.exception.ExceptionHandler;

import java.time.LocalDate;
import java.util.Arrays;

import static christmas.domain.order.constants.PlannerConstraint.PROMOTION_MONTH;
import static christmas.domain.order.constants.PlannerConstraint.PROMOTION_YEAR;

public class VisitDay {
    private static final int THURSDAY_VALUE = 4;
    private static final int SUNDAY_VALUE = 7;

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

    public Integer multiplyDate(final int valueToMultiply) {
        return visitDay.getDayOfMonth() * valueToMultiply;
    }

    //== Validation Method ==//
    public boolean containPeriod(
            LocalDate startDate,
            LocalDate endDate
    ) {
        return !visitDay.isBefore(startDate) && !visitDay.isAfter(endDate);
    }

    public boolean isWeekend() {
        return visitDay.getDayOfWeek().getValue() > THURSDAY_VALUE
                && visitDay.getDayOfWeek().getValue() < SUNDAY_VALUE;
    }

    public boolean isWeekday() {
        return !isWeekend();
    }

    public boolean isSpecialDay() {
        return Arrays.stream(SpecialPromotion.values())
                .anyMatch(value -> value.hasSpecialDay(visitDay));
    }

    //== Getter (Only permit to use Dto/ResponseMapper) ==//
    public LocalDate getVisitDay() {
        return visitDay;
    }
}
