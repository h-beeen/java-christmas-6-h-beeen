package christmas.domain.promotion;

import christmas.domain.order.Order;
import christmas.domain.order.VisitDay;
import christmas.domain.promotion.constants.Promotion;

import java.util.Map.Entry;

public interface PromotionStrategy {

    Entry<Promotion, Integer> apply(VisitDay visitDay, Order order);

    boolean canApplicable(VisitDay visitDay, Order order);
}
