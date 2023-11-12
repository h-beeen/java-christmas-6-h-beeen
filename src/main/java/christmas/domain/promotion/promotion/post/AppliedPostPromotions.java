package christmas.domain.promotion.promotion.post;

import christmas.domain.promotion.promotion.pre.AppliedPrePromotions;

import java.util.EnumMap;
import java.util.Map.Entry;

public class AppliedPostPromotions {
    private final EnumMap<PostPromotion, Integer> postPromotions;

    public AppliedPostPromotions(
            AppliedPrePromotions appliedPromotions
    ) {
        PostPromotionContext postPromotionContext = PostPromotionContext.create(appliedPromotions);
        this.postPromotions = postPromotionContext.applyPostPromotions(appliedPromotions);
    }

    //== Static Factory Method ==//

    public int calculateTotalGiftBenefit() {
        return postPromotions.entrySet()
                .stream()
                .filter(entry -> entry.getKey().isGiftType())
                .mapToInt(Entry::getValue)
                .sum();
    }

    public static AppliedPostPromotions create(AppliedPrePromotions appliedPrePromotions) {
        return new AppliedPostPromotions(appliedPrePromotions);
    }

    // todo 지워라
    public EnumMap<PostPromotion, Integer> getPostPromotions() {
        return postPromotions;
    }
}
