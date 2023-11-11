package christmas.domain.promotion.discount;

import christmas.domain.order.Order;
import christmas.domain.order.VisitingDate;

import java.util.List;

public class MultipleDiscountContext {
    private final List<DiscountStrategy> discountStrategies;

    private MultipleDiscountContext(List<DiscountStrategy> discountStrategies) {
        this.discountStrategies = discountStrategies;
    }

    public static int applyDiscount(
            VisitingDate visitingDate,
            Order order
    ) {

    }
}
