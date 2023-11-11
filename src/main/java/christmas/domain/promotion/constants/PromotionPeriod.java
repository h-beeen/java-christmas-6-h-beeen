package christmas.domain.promotion.constants;

import christmas.domain.order.VisitDay;

import java.time.LocalDate;
import java.util.function.BiPredicate;

public enum PromotionPeriod {
    UNTIL_CHRISTMAS(
            LocalDate.of(2023, 12, 1),
            LocalDate.of(2023, 12, 25),
            (visitDay, period) -> period.isInPromotionPeriod(visitDay)
    );

    private final LocalDate startDate;
    private final LocalDate endDate;
    private final BiPredicate<VisitDay, PromotionPeriod> isPromotionPeriod;

    PromotionPeriod(
            LocalDate startDate,
            LocalDate endDate,
            BiPredicate<VisitDay, PromotionPeriod> isPromotionPeriod
    ) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.isPromotionPeriod = isPromotionPeriod;
    }

    public boolean isInPromotionPeriod(VisitDay visitDay) {
        return visitDay.isInPeriod(startDate, endDate);
    }
}
