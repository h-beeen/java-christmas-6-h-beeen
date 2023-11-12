package christmas.domain.promotion.promotion.gift;

import christmas.domain.order.Orders;
import christmas.domain.order.VisitDay;
import christmas.domain.order.constants.Menu;
import christmas.domain.promotion.promotion.constants.PromotionCondition;

import static christmas.domain.order.constants.Menu.CHAMPAGNE;
import static christmas.domain.promotion.promotion.constants.PromotionCondition.CHAMPAGNE_GIFT_CONDITION;

public enum GiftPromotion {
    GIFT_CHAMPAGNE(
            CHAMPAGNE,
            CHAMPAGNE_GIFT_CONDITION,
            1
    );

    private final Menu menu;
    private final PromotionCondition promotionCondition;
    private final int quantity;

    GiftPromotion(
            Menu menu,
            PromotionCondition promotionCondition,
            final int quantity
    ) {
        this.menu = menu;
        this.promotionCondition = promotionCondition;
        this.quantity = quantity;
    }

    public boolean isPromotionPeriod(VisitDay visitDay) {
        return promotionCondition.isPromotionPeriod(visitDay);
    }

    public boolean isApplicable(VisitDay visitDay, Orders orders) {
        return promotionCondition.isApplicable(visitDay, orders);
    }

    public int getGiftPrice() {
        return menu.getPrice();
    }

    public int getQuantity() {
        return quantity;
    }
}
