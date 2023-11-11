package christmas.domain.promotion.discount;

public interface DiscountStrategy {

    int apply();

    boolean canApplicable();

}
