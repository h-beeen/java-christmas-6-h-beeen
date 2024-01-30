package christmas.domain.promotion.badge;

import christmas.domain.consumer.VisitDay;
import christmas.domain.promotion.discount.AppliedDiscountPromotions;
import christmas.domain.promotion.gift.AppliedGiftPromotions;

import java.util.Arrays;

import static christmas.domain.promotion.badge.BadgePromotion.DEFAULT;

public class BadgeContext {
    private BadgeContext() {
    }

    //== Utility Method ==//
    public static BadgePromotion applyPromotions(
            VisitDay visitDay,
            AppliedDiscountPromotions discountPromotions,
            AppliedGiftPromotions giftPromotions
    ) {
        final int totalBenefit = giftPromotions.getTotalBenefit(discountPromotions);

        return Arrays.stream(BadgePromotion.values())
                .filter(promotion -> promotion.isPromotionPeriod(visitDay))
                .filter(promotion -> promotion.isApplicable(totalBenefit))
                .findFirst()
                .orElse(DEFAULT);
    }
}
