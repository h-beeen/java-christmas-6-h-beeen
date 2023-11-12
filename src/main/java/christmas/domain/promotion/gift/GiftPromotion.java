package christmas.domain.promotion.gift;

import christmas.domain.consumer.Orders;
import christmas.domain.consumer.VisitDay;
import christmas.domain.consumer.constants.Menu;
import christmas.domain.promotion.badge.BadgePromotion;
import christmas.domain.promotion.constants.PromotionCondition;

import static christmas.domain.consumer.constants.Menu.CHAMPAGNE;
import static christmas.domain.promotion.constants.PromotionCondition.CHAMPAGNE_GIFT_CONDITION;

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

    public boolean isApplicable(
            VisitDay visitDay,
            Orders orders,
            BadgePromotion badge
    ) {
        return promotionCondition.isApplicable(visitDay, orders, badge);
    }

    public String getMenuName() {
        return menu.getName();
    }

    public int calculateGiftPrice() {
        return menu.getPrice() * quantity;
    }

    public int getGiftPrice() {
        return menu.getPrice();
    }

    public int getQuantity() {
        return quantity;
    }
}
