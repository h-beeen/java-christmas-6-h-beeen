package christmas.domain.promotion.constants;

import christmas.domain.order.Orders;
import christmas.domain.order.VisitDay;

import java.util.function.BiPredicate;

import static christmas.domain.order.constants.PlannerConstraint.MINIMUM_APPLICABLE_PURCHASE_TOTAL_PRICE;
import static christmas.domain.promotion.constants.PromotionPeriod.MONTHLY_DECEMBER;
import static christmas.domain.promotion.constants.PromotionPeriod.UNTIL_CHRISTMAS;

public enum PromotionCondition {
    CHRISTMAS_D_DAY_DISCOUNT_CONDITION(
            UNTIL_CHRISTMAS,
            (visitDay, orders) -> true
    ),
    WEEKDAY_DISCOUNT_CONDITION(
            MONTHLY_DECEMBER,
            (visitDay, orders) -> visitDay.isWeekday()
    ),
    WEEKEND_DISCOUNT_CONDITION(
            MONTHLY_DECEMBER,
            (visitDay, orders) -> visitDay.isWeekend()
    ),
    SPECIAL_DISCOUNT_CONDITION(
            MONTHLY_DECEMBER,
            (visitDay, orders) -> visitDay.isSpecialDay()
    );

    private final PromotionPeriod promotionPeriod;
    private final BiPredicate<VisitDay, Orders> isApplicable;

    PromotionCondition(PromotionPeriod promotionPeriod, BiPredicate<VisitDay, Orders> isApplicable) {
        this.promotionPeriod = promotionPeriod;
        this.isApplicable = isApplicable;
    }

    private boolean hasApplicableTotalOriginPrice(Orders orders) {
        return orders.calculateTotalOriginPrice() >= MINIMUM_APPLICABLE_PURCHASE_TOTAL_PRICE.getValue();
    }

    public boolean isPromotionPeriod(VisitDay visitDay) {
        return promotionPeriod.isPromotionPeriod(visitDay);
    }

    public boolean isApplicable(
            VisitDay visitDay,
            Orders orders
    ) {
        return hasApplicableTotalOriginPrice(orders)
                && isApplicable.test(visitDay, orders);
    }
}
