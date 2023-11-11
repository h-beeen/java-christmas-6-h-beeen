package christmas.domain.promotion.constants;

import christmas.domain.order.VisitingDate;

import static christmas.domain.promotion.constants.PromotionPeriod.UNTIL_CHRISTMAS;
import static christmas.domain.promotion.constants.PromotionType.DISCOUNT;

public enum Promotion {
    CHRISTMAS_D_DAY_DISCOUNT(
            UNTIL_CHRISTMAS,
            DISCOUNT
    );

    //    private final PromotionStrategy discountStrategy;
    private final PromotionPeriod promotionPeriod;
    private final PromotionType promotionType;

    Promotion(
//            PromotionStrategy discountStrategy,
            PromotionPeriod promotionPeriod,
            PromotionType promotionType
    ) {
//        this.discountStrategy = discountStrategy;
        this.promotionPeriod = promotionPeriod;
        this.promotionType = promotionType;
    }

    public boolean isApplicablePromotion(VisitingDate visitingDate) {
        return promotionPeriod.isInPromotionPeriod(visitingDate);
    }
}
