package christmas.domain.promotion.promotion.discount;

import christmas.domain.consumer.Orders;
import christmas.domain.consumer.VisitDay;
import christmas.domain.promotion.promotion.constants.Badge;

import java.util.EnumMap;

public class AppliedDiscountPromotions {
    private final EnumMap<DiscountPromotion, Integer> promotions;

    //== Constructor ==//
    private AppliedDiscountPromotions(
            VisitDay visitDay,
            Orders orders,
            Badge badge
    ) {
        DiscountContext promotionContext = DiscountContext.create(visitDay, orders, badge);
        this.promotions = promotionContext.applyPromotions(visitDay, orders);
    }

    //== Static Factory Method ==//
    public static AppliedDiscountPromotions create(
            VisitDay visitDay,
            Orders order,
            Badge badge
    ) {
        return new AppliedDiscountPromotions(visitDay, order, badge);
    }

    //== Utility Method ==//
    public int getTotalDiscountAmount() {
        return promotions.values()
                .stream()
                .mapToInt(discountAmount -> discountAmount)
                .sum();
    }

    public int getExpectedPayment(Orders orders) {
        final int totalOriginPrice = orders.calculateTotalOriginPrice();
        return totalOriginPrice - getTotalDiscountAmount();
    }

    public EnumMap<DiscountPromotion, Integer> getPromotions() {
        return promotions;
    }
}
