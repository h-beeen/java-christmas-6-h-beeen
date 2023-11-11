package christmas.domain.promotion.constants;

import christmas.domain.order.VisitDay;
import christmas.domain.promotion.PromotionStrategy;
import christmas.domain.promotion.discount.ChristmasDiscountStrategy;

import static christmas.domain.promotion.constants.PromotionPeriod.UNTIL_CHRISTMAS;
import static christmas.domain.promotion.constants.PromotionType.DISCOUNT;

public enum Promotion {
    CHRISTMAS_D_DAY_DISCOUNT(
            ChristmasDiscountStrategy.getInstance(),
            UNTIL_CHRISTMAS,
            DISCOUNT
    );

    private final PromotionStrategy discountStrategy;
    private final PromotionPeriod promotionPeriod;
    private final PromotionType promotionType;

    Promotion(
            PromotionStrategy discountStrategy,
            PromotionPeriod promotionPeriod,
            PromotionType promotionType
    ) {
        this.discountStrategy = discountStrategy;
        this.promotionPeriod = promotionPeriod;
        this.promotionType = promotionType;
    }

    public boolean isApplicablePromotion(VisitDay visitDay) {
        return promotionPeriod.isInPromotionPeriod(visitDay);
    }

    public PromotionStrategy getDiscountStrategy() {
        return discountStrategy;
    }
}
