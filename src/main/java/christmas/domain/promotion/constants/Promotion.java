package christmas.domain.promotion.constants;

import christmas.domain.order.Orders;
import christmas.domain.order.VisitDay;
import christmas.domain.promotion.strategy.ChristmasDiscountStrategy;
import christmas.domain.promotion.strategy.PromotionStrategy;
import christmas.domain.promotion.strategy.WeekdayDiscountStrategy;

import java.util.function.BiPredicate;

import static christmas.domain.order.constants.PlannerConstraint.MINIMUM_APPLICABLE_PURCHASE_TOTAL_PRICE;
import static christmas.domain.promotion.constants.PromotionPeriod.MONTHLY_DECEMBER;
import static christmas.domain.promotion.constants.PromotionPeriod.UNTIL_CHRISTMAS;

public enum Promotion {
    CHRISTMAS_D_DAY_DISCOUNT(
            ChristmasDiscountStrategy.getInstance(),
            UNTIL_CHRISTMAS,
            (visitDay, orders) -> true
    ),
    WEEKDAY_DISCOUNT(
            WeekdayDiscountStrategy.getInstance(),
            MONTHLY_DECEMBER,
            (visitDay, orders) -> visitDay.isWeekday()
    );

    private final PromotionStrategy promotionStrategy;
    private final PromotionPeriod promotionPeriod;
    private final BiPredicate<VisitDay, Orders> isApplicable;

    Promotion(
            PromotionStrategy promotionStrategy,
            PromotionPeriod promotionPeriod,
            BiPredicate<VisitDay, Orders> isApplicable
    ) {
        this.promotionStrategy = promotionStrategy;
        this.promotionPeriod = promotionPeriod;
        this.isApplicable = isApplicable;
    }

    private boolean hasApplicableTotalOriginPrice(Orders orders) {
        return orders.calculateTotalOriginPrice() > MINIMUM_APPLICABLE_PURCHASE_TOTAL_PRICE.getValue();
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

    public PromotionStrategy getPromotionStrategy() {
        return promotionStrategy;
    }
}
