package christmas.domain.promotion.badge;

import java.util.List;

public class BadgeContext {
    private final List<BadgePromotion> promotions;

    public BadgeContext(List<BadgePromotion> promotions) {
        this.promotions = promotions;
    }
}
