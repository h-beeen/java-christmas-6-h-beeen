package christmas.controller.dto;

import christmas.domain.promotion.badge.BadgePromotion;

public record BadgeResponse(
        String badgeName
) {
    public static BadgeResponse from(BadgePromotion promotion) {
        return new BadgeResponse(promotion.getName());
    }
}
