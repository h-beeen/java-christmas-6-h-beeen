package christmas.fixture;

import christmas.domain.consumer.Orders;
import christmas.domain.consumer.VisitDay;
import christmas.domain.promotion.discount.AppliedDiscountPromotions;
import christmas.domain.promotion.gift.AppliedGiftPromotions;

import static christmas.domain.promotion.badge.BadgePromotion.DEFAULT;

public enum PromotionsFixture {
    TOTAL__173_000__VISIT__15(
            VisitDay.create(15),
            OrdersFixture.VALID__A.toEntity()
    ),
    TOTAL__18_000__VISIT__1(
            VisitDay.create(1),
            OrdersFixture.VALID__C.toEntity()
    );

    PromotionsFixture(
            VisitDay visitDay,
            Orders orders
    ) {
        this.visitDay = visitDay;
        this.orders = orders;
    }

    private final VisitDay visitDay;
    private final Orders orders;


    public AppliedDiscountPromotions toDiscountPromotionsEntity() {
        return AppliedDiscountPromotions.create(visitDay, orders, DEFAULT);
    }

    public AppliedGiftPromotions toGiftPromotionsEntity() {
        return AppliedGiftPromotions.create(visitDay, orders, DEFAULT);
    }

    public VisitDay getVisitDay() {
        return visitDay;
    }

    public Orders getOrders() {
        return orders;
    }
}
