package christmas.domain.promotion.constants;

import christmas.domain.order.Orders;
import christmas.domain.order.VisitDay;
import christmas.domain.promotion.strategy.ChristmasDiscountStrategy;
import christmas.domain.promotion.strategy.PromotionStrategy;

import static christmas.domain.order.constants.PlannerConstraint.MINIMUM_APPLICABLE_PURCHASE_TOTAL_PRICE;
import static christmas.domain.promotion.constants.PromotionPeriod.UNTIL_CHRISTMAS;
import static christmas.domain.promotion.constants.PromotionType.DISCOUNT;

public enum Promotion {
    CHRISTMAS_D_DAY_DISCOUNT(
            ChristmasDiscountStrategy.getInstance(),
            UNTIL_CHRISTMAS,
            DISCOUNT
    );

    private final PromotionStrategy promotionStrategy;
    private final PromotionPeriod promotionPeriod;
    private final PromotionType promotionType;

    Promotion(
            PromotionStrategy promotionStrategy,
            PromotionPeriod promotionPeriod,
            PromotionType promotionType
    ) {
        this.promotionStrategy = promotionStrategy;
        this.promotionPeriod = promotionPeriod;
        this.promotionType = promotionType;
    }

    private boolean hasApplicableTotalOriginPrice(Orders orders) {
        return orders.calculateTotalOriginPrice() > MINIMUM_APPLICABLE_PURCHASE_TOTAL_PRICE.getValue();
    }

    public boolean isinPromotionPeriod(VisitDay visitDay) {
        return promotionPeriod.isInPromotionPeriod(visitDay);
    }

    public boolean isApplicable(
            VisitDay visitDay,
            Orders orders
    ) {
        return hasApplicableTotalOriginPrice(orders)
                && promotionPeriod.isInPromotionPeriod(visitDay);
    }

    public PromotionStrategy getPromotionStrategy() {
        return promotionStrategy;
    }
}
