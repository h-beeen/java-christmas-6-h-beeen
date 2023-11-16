package christmas.domain.promotion.constants;

import christmas.domain.consumer.Orders;
import christmas.domain.consumer.VisitDay;
import christmas.domain.promotion.badge.BadgePromotion;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

import static christmas.domain.consumer.constants.MenuCategory.DESSERT;
import static christmas.domain.consumer.constants.MenuCategory.MAIN_DISH;
import static christmas.domain.consumer.constants.PlannerConstraint.MINIMUM_APPLICABLE_PURCHASE_TOTAL_PRICE;
import static christmas.domain.promotion.constants.PromotionPeriod.MONTHLY_DECEMBER;
import static christmas.domain.promotion.constants.PromotionPeriod.UNTIL_CHRISTMAS;

public enum PromotionCondition {
    CHRISTMAS_D_DAY_PROMOTION_CONDITION(
            UNTIL_CHRISTMAS,
            (visitDay, orders) -> true,
            always -> true
    ),
    WEEKDAY_PROMOTION_CONDITION(
            MONTHLY_DECEMBER,
            (visitDay, orders) -> visitDay.isWeekday() && orders.hasMenuCategory(DESSERT),
            always -> true
    ),
    WEEKEND_PROMOTION_CONDITION(
            MONTHLY_DECEMBER,
            (visitDay, orders) -> visitDay.isWeekend() && orders.hasMenuCategory(MAIN_DISH),
            always -> true
    ),
    SPECIAL_PROMOTION_CONDITION(
            MONTHLY_DECEMBER,
            (visitDay, orders) -> visitDay.isSpecialDay(),
            always -> true
    ),
    CHAMPAGNE_GIFT_CONDITION(
            MONTHLY_DECEMBER,
            (visitDay, orders) -> orders.calculateTotalOriginPrice() >= 120_000,
            always -> true
    );

    private final PromotionPeriod promotionPeriod;
    private final BiPredicate<VisitDay, Orders> applicableFunction;
    private final Predicate<BadgePromotion> badgeFunction;

    PromotionCondition(
            PromotionPeriod promotionPeriod,
            BiPredicate<VisitDay, Orders> applicableFunction,
            Predicate<BadgePromotion> badgeFunction
    ) {
        this.promotionPeriod = promotionPeriod;
        this.applicableFunction = applicableFunction;
        this.badgeFunction = badgeFunction;
    }

    //== Validation Method ==//
    public boolean isApplicable(
            VisitDay visitDay,
            Orders orders,
            BadgePromotion badge
    ) {
        return hasApplicableTotalOriginPrice(orders)
                && applicableFunction.test(visitDay, orders)
                && badgeFunction.test(badge);
    }

    public boolean isPromotionPeriod(VisitDay visitDay) {
        return promotionPeriod.isPromotionPeriod(visitDay);
    }

    private boolean hasApplicableTotalOriginPrice(Orders orders) {
        return orders.calculateTotalOriginPrice() >= MINIMUM_APPLICABLE_PURCHASE_TOTAL_PRICE.getValue();
    }
}
