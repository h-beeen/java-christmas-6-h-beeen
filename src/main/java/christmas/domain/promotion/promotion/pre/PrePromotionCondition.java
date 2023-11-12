package christmas.domain.promotion.promotion.pre;

import christmas.domain.order.Orders;
import christmas.domain.order.VisitDay;
import christmas.domain.promotion.constants.PromotionPeriod;
import christmas.domain.promotion.promotion.constants.PromotionType;

import java.util.function.BiPredicate;

import static christmas.domain.order.constants.PlannerConstraint.MINIMUM_APPLICABLE_PURCHASE_TOTAL_PRICE;
import static christmas.domain.promotion.constants.PromotionPeriod.MONTHLY_DECEMBER;
import static christmas.domain.promotion.constants.PromotionPeriod.UNTIL_CHRISTMAS;
import static christmas.domain.promotion.promotion.constants.PromotionType.DISCOUNT;
import static christmas.domain.promotion.promotion.constants.PromotionType.GIFT;

public enum PrePromotionCondition {
    CHRISTMAS_D_DAY_PROMOTION_CONDITION(
            UNTIL_CHRISTMAS,
            DISCOUNT,
            (visitDay, orders) -> true
    ),
    WEEKDAY_PROMOTION_CONDITION(
            MONTHLY_DECEMBER,
            DISCOUNT,
            (visitDay, orders) -> visitDay.isWeekday()
    ),
    WEEKEND_PROMOTION_CONDITION(
            MONTHLY_DECEMBER,
            DISCOUNT,
            (visitDay, orders) -> visitDay.isWeekend()
    ),
    SPECIAL_PROMOTION_CONDITION(
            MONTHLY_DECEMBER,
            DISCOUNT,
            (visitDay, orders) -> visitDay.isSpecialDay()
    ),
    CHAMPAGNE_GIFT_CONDITION(
            MONTHLY_DECEMBER,
            GIFT,
            (visitDay, orders) -> orders.calculateTotalOriginPrice() >= 120_000
    );

    private final PromotionPeriod promotionPeriod;
    private final PromotionType promotionType;
    private final BiPredicate<VisitDay, Orders> isApplicable;

    PrePromotionCondition(
            PromotionPeriod promotionPeriod,
            PromotionType promotionType,
            BiPredicate<VisitDay, Orders> isApplicable
    ) {
        this.promotionPeriod = promotionPeriod;
        this.isApplicable = isApplicable;
        this.promotionType = promotionType;
    }

    public boolean isApplicable(
            VisitDay visitDay,
            Orders orders
    ) {
        return hasApplicableTotalOriginPrice(orders)
                && isApplicable.test(visitDay, orders);
    }

    public boolean isPromotionPeriod(VisitDay visitDay) {
        return promotionPeriod.isPromotionPeriod(visitDay);
    }

    private boolean hasApplicableTotalOriginPrice(Orders orders) {
        return orders.calculateTotalOriginPrice() >= MINIMUM_APPLICABLE_PURCHASE_TOTAL_PRICE.getValue();
    }

    public boolean isSameType(PromotionType comparablePromotionType) {
        return promotionType.equals(comparablePromotionType);
    }
}
