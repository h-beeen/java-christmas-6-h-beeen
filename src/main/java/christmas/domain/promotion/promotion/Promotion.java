package christmas.domain.promotion.promotion;

import christmas.domain.order.Orders;
import christmas.domain.order.VisitDay;

import java.util.function.BiFunction;

import static christmas.domain.order.constants.MenuCategory.DESSERT;
import static christmas.domain.order.constants.MenuCategory.MAIN_DISH;
import static christmas.domain.promotion.promotion.PromotionCondition.*;
import static christmas.domain.promotion.promotion.constants.PromotionType.DISCOUNT;
import static christmas.domain.promotion.promotion.constants.PromotionType.GIFT;

public enum Promotion {
    CHRISTMAS_D_DAY_DISCOUNT(
            "크리스마스 디데이 할인",
            CHRISTMAS_D_DAY_PROMOTION_CONDITION,
            (visitDay, orders) -> visitDay.multiplyDate(100) + 900
    ),
    WEEKDAY_DISCOUNT(
            "평일 할인",
            WEEKDAY_PROMOTION_CONDITION,
            (visitDay, orders) -> orders.countOrdersByMenuType(DESSERT) * 2023
    ),
    WEEKEND_DISCOUNT(
            "주말 할인",
            WEEKEND_PROMOTION_CONDITION,
            (visitDay, orders) -> orders.countOrdersByMenuType(MAIN_DISH) * 2023
    ),
    SPECIAL_DISCOUNT(
            "특별 할인",
            SPECIAL_PROMOTION_CONDITION,
            (visitDay, orders) -> 1000
    ),
    CHAMPAGNE_GIFT(
            "증정 이벤트",
            CHAMPAGNE_GIFT_CONDITION,
            (visitDay, orders) -> 1
    );

    private final String promotionName;
    private final PromotionCondition promotionCondition;
    private final BiFunction<VisitDay, Orders, Integer> promotionFunction;

    Promotion(
            String promotionName,
            PromotionCondition promotionCondition,
            BiFunction<VisitDay, Orders, Integer> promotionFunction
    ) {
        this.promotionName = promotionName;
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

    public String getPromotionName() {
        return promotionName;
    }
}
