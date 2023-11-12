package christmas.domain.promotion.promotion.constants;

import christmas.domain.order.Orders;
import christmas.domain.order.VisitDay;
import christmas.domain.promotion.constants.PromotionPeriod;

import java.util.function.BiPredicate;

import static christmas.domain.order.constants.MenuCategory.DESSERT;
import static christmas.domain.order.constants.MenuCategory.MAIN_DISH;
import static christmas.domain.order.constants.PlannerConstraint.MINIMUM_APPLICABLE_PURCHASE_TOTAL_PRICE;
import static christmas.domain.promotion.constants.PromotionPeriod.MONTHLY_DECEMBER;
import static christmas.domain.promotion.constants.PromotionPeriod.UNTIL_CHRISTMAS;

public enum PromotionCondition {
    CHRISTMAS_D_DAY_PROMOTION_CONDITION(
            UNTIL_CHRISTMAS,
            (visitDay, orders) -> true
    ),
    WEEKDAY_PROMOTION_CONDITION(
            MONTHLY_DECEMBER,
            (visitDay, orders) -> visitDay.isWeekday() && orders.hasMenuCategory(DESSERT)
    ),
    WEEKEND_PROMOTION_CONDITION(
            MONTHLY_DECEMBER,
            (visitDay, orders) -> visitDay.isWeekend() && orders.hasMenuCategory(MAIN_DISH)
    ),
    SPECIAL_PROMOTION_CONDITION(
            MONTHLY_DECEMBER,
            (visitDay, orders) -> visitDay.isSpecialDay()
    ),
    CHAMPAGNE_GIFT_CONDITION(
            MONTHLY_DECEMBER,
            (visitDay, orders) -> orders.calculateTotalOriginPrice() >= 120_000
    );

    private final PromotionPeriod promotionPeriod;
    private final BiPredicate<VisitDay, Orders> isApplicable;

    PromotionCondition(
            PromotionPeriod promotionPeriod,
            BiPredicate<VisitDay, Orders> isApplicable
    ) {
        this.promotionPeriod = promotionPeriod;
        this.isApplicable = isApplicable;
    }

    public boolean isApplicable(
            VisitDay visitDay,
            Orders orders
    ) {
        return hasApplicableTotalOriginPrice(orders) && isApplicable.test(visitDay, orders);
    }

    public boolean isPromotionPeriod(VisitDay visitDay) {
        return promotionPeriod.isPromotionPeriod(visitDay);
    }

    private boolean hasApplicableTotalOriginPrice(Orders orders) {
        return orders.calculateTotalOriginPrice() >= MINIMUM_APPLICABLE_PURCHASE_TOTAL_PRICE.getValue();
    }
}
