package christmas.domain.promotion.strategy.constants;

import christmas.domain.order.Orders;
import christmas.domain.order.VisitDay;
import christmas.domain.promotion.strategy.PromotionStrategy;
import christmas.domain.promotion.strategy.discount.ChristmasDiscountStrategy;
import christmas.domain.promotion.strategy.discount.SpecialDiscountStrategy;
import christmas.domain.promotion.strategy.discount.WeekdayDiscountStrategy;
import christmas.domain.promotion.strategy.discount.WeekendDiscountStrategy;

import static christmas.domain.promotion.strategy.constants.PromotionCondition.*;

public enum Promotion {
    CHRISTMAS_D_DAY_DISCOUNT(
            ChristmasDiscountStrategy.create(),
            CHRISTMAS_D_DAY_PROMOTION_CONDITION
    ),
    WEEKDAY_DISCOUNT(
            WeekdayDiscountStrategy.create(),
            WEEKDAY_PROMOTION_CONDITION
    ),
    WEEKEND_DISCOUNT(
            WeekendDiscountStrategy.create(),
            WEEKEND_PROMOTION_CONDITION
    ),
    SPECIAL_DISCOUNT(
            SpecialDiscountStrategy.create(),
            SPECIAL_PROMOTION_CONDITION
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
