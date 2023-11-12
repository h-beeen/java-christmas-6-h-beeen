package christmas.domain.promotion.strategy.constants;

import christmas.domain.order.Orders;
import christmas.domain.order.VisitDay;

import java.util.function.Predicate;

import static christmas.domain.order.constants.PlannerConstraint.MINIMUM_APPLICABLE_PURCHASE_TOTAL_PRICE;
import static christmas.domain.promotion.strategy.constants.PromotionPeriod.MONTHLY_DECEMBER;
import static christmas.domain.promotion.strategy.constants.PromotionPeriod.UNTIL_CHRISTMAS;

public enum PromotionCondition {
    CHRISTMAS_D_DAY_PROMOTION_CONDITION(
            UNTIL_CHRISTMAS,
            always -> true
    ),
    WEEKDAY_PROMOTION_CONDITION(
            MONTHLY_DECEMBER,
            VisitDay::isWeekday
    ),
    WEEKEND_PROMOTION_CONDITION(
            MONTHLY_DECEMBER,
            VisitDay::isWeekend
    ),
    SPECIAL_PROMOTION_CONDITION(
            MONTHLY_DECEMBER,
            VisitDay::isSpecialDay
    );

    private final PromotionPeriod promotionPeriod;
    private final Predicate<VisitDay> isApplicable;

    PromotionCondition(
            PromotionPeriod promotionPeriod,
            Predicate<VisitDay> isApplicable
    ) {
        this.promotionPeriod = promotionPeriod;
        this.isApplicable = isApplicable;
    }

    public boolean isApplicable(
            VisitDay visitDay,
            Orders orders
    ) {
        return hasApplicableTotalOriginPrice(orders) && isApplicable.test(visitDay);
    }

    public boolean isPromotionPeriod(VisitDay visitDay) {
        return promotionPeriod.isPromotionPeriod(visitDay);
    }

    private boolean hasApplicableTotalOriginPrice(Orders orders) {
        return orders.calculateTotalOriginPrice() >= MINIMUM_APPLICABLE_PURCHASE_TOTAL_PRICE.getValue();
    }
}
