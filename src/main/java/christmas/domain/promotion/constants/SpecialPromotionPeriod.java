package christmas.domain.promotion.constants;

import java.time.LocalDate;
import java.util.List;

public enum SpecialPromotionPeriod {
    STAR(List.of(
            LocalDate.of(2023, 12, 3),
            LocalDate.of(2023, 12, 10),
            LocalDate.of(2023, 12, 17),
            LocalDate.of(2023, 12, 24),
            LocalDate.of(2023, 12, 25),
            LocalDate.of(2023, 12, 31))
    );

    private final List<LocalDate> specialPromotionDates;

    SpecialPromotionPeriod(List<LocalDate> specialPromotionDates) {
        this.specialPromotionDates = specialPromotionDates;
    }

    //== Validation Method ==//
    public boolean hasSpecialDay(LocalDate visitDay) {
        return specialPromotionDates.stream()
                .anyMatch(specialDate -> specialDate.isEqual(visitDay));
    }
}
