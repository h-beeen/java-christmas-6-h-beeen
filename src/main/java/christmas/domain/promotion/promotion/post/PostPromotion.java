package christmas.domain.promotion.promotion.post;

import christmas.domain.promotion.promotion.pre.AppliedPrePromotions;

import java.util.function.Function;

import static christmas.domain.promotion.promotion.constants.PromotionType.GIFT;

public enum PostPromotion {
    ;

    private final PostPromotionCondition postPromotionCondition;
    private final Function<AppliedPrePromotions, Integer> promotionFunction;

    PostPromotion(
            PostPromotionCondition postPromotionCondition,
            Function<AppliedPrePromotions, Integer> promotionFunction
    ) {
        this.postPromotionCondition = postPromotionCondition;
        this.promotionFunction = promotionFunction;
    }

    public int applyPostPromotion(AppliedPrePromotions appliedPrePromotions) {
        return promotionFunction.apply(appliedPrePromotions);
    }

    public boolean isApplicable(AppliedPrePromotions appliedPrePromotions) {
        return postPromotionCondition.isApplicable(appliedPrePromotions);
    }

    public boolean isGiftType() {
        return postPromotionCondition.isSameType(GIFT);
    }

    public Integer applyPromotion(AppliedPrePromotions appliedPrePromotions) {
        return promotionFunction.apply(appliedPrePromotions);
    }
}
