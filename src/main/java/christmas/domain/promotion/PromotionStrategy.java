package christmas.domain.promotion;

public interface PromotionStrategy {

    int apply();

    boolean canApplicable();

}
