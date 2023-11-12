package christmas.domain.promotion.constants;

import christmas.domain.order.Orders;
import christmas.domain.order.VisitDay;
import christmas.domain.promotion.strategy.*;

import static christmas.domain.promotion.constants.PromotionCondition.*;

public enum Promotion {
    CHRISTMAS_D_DAY_DISCOUNT(
            ChristmasDiscountStrategy.create(),
            CHRISTMAS_D_DAY_DISCOUNT_CONDITION
    ),
    WEEKDAY_DISCOUNT(
            WeekdayDiscountStrategy.create(),
            WEEKDAY_DISCOUNT_CONDITION
    ),
    WEEKEND_DISCOUNT(
            WeekendDiscountStrategy.create(),
            WEEKEND_DISCOUNT_CONDITION
    ),
    SPECIAL_DISCOUNT(
            SpecialDiscountStrategy.create(),
            SPECIAL_DISCOUNT_CONDITION
    );

    private final PromotionStrategy promotionStrategy;
    private final PromotionCondition promotionCondition;

    Promotion(
            PromotionStrategy promotionStrategy,
            PromotionCondition promotionCondition
    ) {
        this.promotionStrategy = promotionStrategy;
        this.promotionCondition = promotionCondition;
    }

    public boolean isPromotionPeriod(VisitDay visitDay) {
        return promotionCondition.isPromotionPeriod(visitDay);
    }

    public boolean isApplicable(
            VisitDay visitDay,
            Orders orders
    ) {
        return promotionCondition.isApplicable(visitDay, orders);
    }

    public PromotionStrategy getPromotionStrategy() {
        return promotionStrategy;
    }
}
