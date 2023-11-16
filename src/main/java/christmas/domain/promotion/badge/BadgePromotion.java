package christmas.domain.promotion.badge;

import christmas.domain.consumer.VisitDay;
import christmas.domain.promotion.constants.PromotionPeriod;

import java.util.function.Predicate;

import static christmas.domain.promotion.constants.PromotionPeriod.ALWAYS;
import static christmas.domain.promotion.constants.PromotionPeriod.MONTHLY_DECEMBER;

public enum BadgePromotion {
    SANTA(
            "산타",
            MONTHLY_DECEMBER,
            totalBenefitAmount -> totalBenefitAmount >= 20_000
    ),
    TREE(
            "트리",
            MONTHLY_DECEMBER,
            totalBenefitAmount -> totalBenefitAmount >= 10_000
    ),
    STAR(
            "별",
            MONTHLY_DECEMBER,
            totalBenefitAmount -> totalBenefitAmount >= 5_000
    ),
    DEFAULT(
            "없음",
            ALWAYS,
            always -> true
    );

    private final String name;
    private final PromotionPeriod promotionPeriod;
    private final Predicate<Integer> applicableFunction;

    BadgePromotion(
            String name,
            PromotionPeriod promotionPeriod,
            Predicate<Integer> applicableFunction
    ) {
        this.name = name;
        this.promotionPeriod = promotionPeriod;
        this.applicableFunction = applicableFunction;
    }

    //== Validation Method ==//
    public boolean isApplicable(Integer totalBenefitAmount) {
        return applicableFunction.test(totalBenefitAmount);
    }

    public boolean isPromotionPeriod(VisitDay visitDay) {
        return promotionPeriod.isPromotionPeriod(visitDay);
    }

    //== Getter (Only permit to use Dto/ResponseMapper) ==//
    public String getName() {
        return name;
    }
}
