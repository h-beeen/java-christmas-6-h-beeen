package christmas.domain.promotion.promotion.pre;

import christmas.domain.order.Orders;
import christmas.domain.order.VisitDay;

import java.util.function.BiFunction;

import static christmas.domain.order.constants.MenuCategory.DESSERT;
import static christmas.domain.order.constants.MenuCategory.MAIN_DISH;
import static christmas.domain.promotion.promotion.constants.PromotionType.DISCOUNT;
import static christmas.domain.promotion.promotion.constants.PromotionType.GIFT;
import static christmas.domain.promotion.promotion.pre.PrePromotionCondition.*;

public enum PrePromotion {
    CHRISTMAS_D_DAY_DISCOUNT(
            CHRISTMAS_D_DAY_PROMOTION_CONDITION,
            (visitDay, orders) -> visitDay.multiplyDate(100) + 900
    ),
    WEEKDAY_DISCOUNT(
            WEEKDAY_PROMOTION_CONDITION,
            (visitDay, orders) -> orders.countOrdersByMenuType(DESSERT) * 2023
    ),
    WEEKEND_DISCOUNT(
            WEEKEND_PROMOTION_CONDITION,
            (visitDay, orders) -> orders.countOrdersByMenuType(MAIN_DISH) * 2023
    ),
    SPECIAL_DISCOUNT(
            SPECIAL_PROMOTION_CONDITION,
            (visitDay, orders) -> 1000
    ),
    CHAMPAGNE_GIFT(
            CHAMPAGNE_GIFT_CONDITION,
            (visitDay, orders) -> 1
    );
    private final PrePromotionCondition promotionCondition;
    private final BiFunction<VisitDay, Orders, Integer> promotionFunction;

    PrePromotion(
            PrePromotionCondition promotionCondition,
            BiFunction<VisitDay, Orders, Integer> promotionFunction
    ) {
        this.promotionCondition = promotionCondition;
        this.promotionFunction = promotionFunction;
    }

    public Integer applyPromotion(
            VisitDay visitDay,
            Orders orders
    ) {
        return promotionFunction.apply(visitDay, orders);
    }

    //== Validation Method ==//
    public boolean isPromotionPeriod(VisitDay visitDay) {
        return promotionCondition.isPromotionPeriod(visitDay);
    }

    public boolean isApplicable(
            VisitDay visitDay,
            Orders orders
    ) {
        return promotionCondition.isApplicable(visitDay, orders);
    }

    public boolean isDiscountType() {
        return promotionCondition.isSameType(DISCOUNT);
    }

    public boolean isGiftType() {
        return promotionCondition.isSameType(GIFT);
    }
}
