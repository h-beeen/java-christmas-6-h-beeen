package christmas.domain.promotion.promotion.post;

import christmas.domain.promotion.promotion.constants.PromotionType;
import christmas.domain.promotion.promotion.pre.AppliedPrePromotions;

import java.util.function.Predicate;

public enum PostPromotionCondition {

    ;

    private final PromotionType promotionType;
    private final Predicate<AppliedPrePromotions> isApplicable;

    PostPromotionCondition(
            PromotionType promotionType,
            Predicate<AppliedPrePromotions> isApplicable
    ) {
        this.promotionType = promotionType;
        this.isApplicable = isApplicable;
    }

    public boolean isSameType(PromotionType comparablePromotionType) {
        return promotionType.equals(comparablePromotionType);
    }

    public boolean isApplicable(AppliedPrePromotions appliedPrePromotions) {
        return isApplicable.test(appliedPrePromotions);
    }
}
