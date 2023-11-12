package christmas.domain.promotion.promotion.post;

import christmas.domain.promotion.promotion.pre.AppliedPrePromotions;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PostPromotionContext {
    private final List<PostPromotion> applicablePostPromotions;

    private PostPromotionContext(AppliedPrePromotions appliedPrePromotions) {
        this.applicablePostPromotions = Arrays.stream(PostPromotion.values())
                .filter(promotion -> promotion.isApplicable(appliedPrePromotions))
                .toList();
    }

    public static PostPromotionContext create(AppliedPrePromotions appliedPrePromotions) {
        return new PostPromotionContext(appliedPrePromotions);
    }

    public EnumMap<PostPromotion, Integer> applyPostPromotions(AppliedPrePromotions appliedPrePromotions) {
        return applicablePostPromotions.stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        promotion -> promotion.applyPromotion(appliedPrePromotions),
                        (previous, next) -> next,
                        () -> new EnumMap<>(PostPromotion.class)));
    }
}
