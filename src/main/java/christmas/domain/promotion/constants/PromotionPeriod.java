package christmas.domain.promotion.constants;

import christmas.domain.order.VisitingDate;

import java.time.LocalDate;

public enum PromotionPeriod {
    UNTIL_CHRISTMAS(
            LocalDate.of(2023, 12, 1),
            LocalDate.of(2023, 12, 25)
    ),
    DECEMBER_MONTHLY(
            LocalDate.of(2023, 12, 1),
            LocalDate.of(2023, 12, 31)
    );

    private final LocalDate startDate;
    private final LocalDate endDate;

    PromotionPeriod(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public boolean isInPromotionPeriod(VisitingDate visitingDate) {
        return visitingDate.isInPromotionPeriod(startDate, endDate);
    }
}
