package christmas.domain.promotion.strategy;

import christmas.domain.order.Orders;
import christmas.domain.order.VisitDay;
import christmas.domain.promotion.constants.Promotion;

import java.util.Map.Entry;

public interface PromotionStrategy {

    Entry<Promotion, Integer> apply(VisitDay visitDay, Orders order);
}
