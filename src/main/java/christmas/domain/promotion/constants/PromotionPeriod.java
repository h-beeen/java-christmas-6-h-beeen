package christmas.domain.promotion.constants;

import christmas.domain.consumer.VisitDay;

import java.time.LocalDate;

public enum PromotionPeriod {
    ALWAYS(
            LocalDate.of(1900, 1, 1),
            LocalDate.of(2099, 12, 31)
    ),
    UNTIL_CHRISTMAS(
            LocalDate.of(2023, 12, 1),
            LocalDate.of(2023, 12, 25)
    ),
    MONTHLY_DECEMBER(
            LocalDate.of(2023, 12, 1),
            LocalDate.of(2023, 12, 31)
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
