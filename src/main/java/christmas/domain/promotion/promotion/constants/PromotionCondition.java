package christmas.domain.promotion.promotion.constants;

import christmas.domain.customer.Orders;
import christmas.domain.customer.VisitDay;
import christmas.domain.promotion.constants.PromotionPeriod;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

import static christmas.domain.customer.constants.MenuCategory.DESSERT;
import static christmas.domain.customer.constants.MenuCategory.MAIN_DISH;
import static christmas.domain.customer.constants.PlannerConstraint.MINIMUM_APPLICABLE_PURCHASE_TOTAL_PRICE;
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
    private final Predicate<Badge> requireBadge;

    PromotionCondition(
            PromotionPeriod promotionPeriod,
            BiPredicate<VisitDay, Orders> applicableFunction,
            Predicate<Badge> requireBadge
    ) {
        this.promotionPeriod = promotionPeriod;
        this.applicableFunction = applicableFunction;
        this.requireBadge = requireBadge;
    }

    public boolean isApplicable(
            VisitDay visitDay,
            Orders orders
    ) {
        return hasApplicableTotalOriginPrice(orders) && applicableFunction.test(visitDay, orders);
    }

    public boolean isPromotionPeriod(VisitDay visitDay) {
        return promotionPeriod.isPromotionPeriod(visitDay);
    }

    private boolean hasApplicableTotalOriginPrice(Orders orders) {
        return orders.calculateTotalOriginPrice() >= MINIMUM_APPLICABLE_PURCHASE_TOTAL_PRICE.getValue();
    }
}
