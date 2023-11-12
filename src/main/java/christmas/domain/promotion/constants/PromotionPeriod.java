package christmas.domain.promotion.constants;

import christmas.domain.customer.VisitDay;

import java.time.LocalDate;

public enum PromotionPeriod {
    UNTIL_CHRISTMAS(
            LocalDate.of(2023, 12, 1),
            LocalDate.of(2023, 12, 25)
    ),
    MONTHLY_DECEMBER(
            LocalDate.of(2023, 12, 1),
            LocalDate.of(2023, 12, 25)
    );

    private final LocalDate startDate;
    private final LocalDate endDate;

    PromotionPeriod(
            LocalDate startDate,
            LocalDate endDate
    ) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public boolean isPromotionPeriod(VisitDay visitDay) {
        return visitDay.containPeriod(startDate, endDate);
    }
}
