package christmas.domain.promotion.promotion.gift;

import christmas.domain.customer.Orders;
import christmas.domain.customer.VisitDay;

import java.util.EnumMap;

public class GiftQuantities {
    private final EnumMap<GiftPromotion, Integer> giftQuantities;

    private GiftQuantities(
            VisitDay visitDay,
            Orders orders
    ) {
        GiftContext giftContext = GiftContext.create(visitDay, orders);
        this.giftQuantities = giftContext.getResult();
    }

    public static GiftQuantities create(
            VisitDay visitDay,
            Orders orders
    ) {
        return new GiftQuantities(visitDay, orders);
    }
}
