package christmas.domain.promotion;

import christmas.domain.order.Order;
import christmas.domain.order.VisitingDate;
import christmas.domain.promotion.constants.Promotion;

import java.util.Map;

public interface PromotionStrategy {

    Map.Entry<Promotion, Integer> apply(VisitingDate visitingDate, Order order);

    boolean canApplicable(VisitingDate visitingDate, Order order);
}
