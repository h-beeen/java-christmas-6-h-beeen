package christmas.controller;

import christmas.domain.order.Order;
import christmas.domain.order.VisitingDate;
import christmas.domain.promotion.MultiplePromotionContext;
import christmas.domain.promotion.constants.Promotion;

import java.util.EnumMap;
import java.util.List;
import java.util.Map.Entry;

public class PromotionController {
    private PromotionController() {
    }

    public static EnumMap<Promotion, Integer> applyPromotions(
            VisitingDate visitingDate,
            Order order
    ) {
        MultiplePromotionContext promotionContext = new MultiplePromotionContext();
        List<Entry<Promotion, Integer>> entries = promotionContext.applyPromotion(visitingDate, order);

        int i = 0;
        
        EnumMap<Promotion, Integer> ret = new EnumMap<>(Promotion.class);
        entries.forEach(key -> {
            Promotion key1 = key.getKey();
            Integer value = key.getValue();
            ret.put(key1, value);
        });

        return ret;
    }
}
