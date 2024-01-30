package christmas.domain.promotion.discount;

import christmas.domain.consumer.Orders;
import christmas.domain.consumer.VisitDay;
import christmas.domain.promotion.badge.BadgePromotion;
import christmas.domain.promotion.constants.PromotionCondition;

import java.util.function.BiFunction;

import static christmas.domain.consumer.constants.MenuCategory.DESSERT;
import static christmas.domain.consumer.constants.MenuCategory.MAIN_DISH;
import static christmas.domain.promotion.constants.PromotionCondition.*;

public enum DiscountPromotion {
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
    );

    private final String promotionName;
    private final PromotionCondition promotionCondition;
    private final BiFunction<VisitDay, Orders, Integer> promotionFunction;

    DiscountPromotion(
            String promotionName,
            PromotionCondition promotionCondition,
            BiFunction<VisitDay, Orders, Integer> promotionFunction
    ) {
        this.promotionName = promotionName;
        this.promotionCondition = promotionCondition;
        this.promotionFunction = promotionFunction;
    }

    //== Utility Method ==//
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
            Orders orders,
            BadgePromotion badge
    ) {
        return promotionCondition.isApplicable(visitDay, orders, badge);
    }

    //== Getter (Only permit to use Dto/ResponseMapper) ==//
    public String getPromotionName() {
        return promotionName;
    }
}
