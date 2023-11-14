package christmas.domain.promotion;

import christmas.domain.promotion.badge.BadgeContext;
import christmas.domain.promotion.badge.BadgePromotion;
import christmas.domain.promotion.discount.AppliedDiscountPromotions;
import christmas.domain.promotion.gift.AppliedGiftPromotions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static christmas.domain.promotion.badge.BadgePromotion.DEFAULT;
import static christmas.domain.promotion.badge.BadgePromotion.SANTA;
import static christmas.fixture.PromotionsFixture.TOTAL__173_000__VISIT__15;
import static christmas.fixture.PromotionsFixture.TOTAL__18_000__VISIT__1;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("[BadgeContext] - Domain Layer")
class BadgeContextTest {

    @Nested
    @DisplayName("[applyPromotions] 적용 가능한 이벤트 배지를 적용하고, 적용된 배지를 리턴")
    class create {

        @Test
        @DisplayName("[Applicable] 적용 가능한 이벤트 배지가 존재해, 해당 배지를 리턴")
        void Should_ReturnValidBadge_When_RequestApplicable() {

            /**
             * Given Fixture Information
             * <혜택 내역>
             * 주말 할인: -4,046원
             * 크리스마스 디데이 할인: -2,400원
             * 증정 이벤트: -25,000원
             *
             * <총혜택 금액>
             * -31,446원
             *
             * <12월 이벤트 배지>
             * 산타
             */

            // given
            AppliedDiscountPromotions discountPromotions = TOTAL__173_000__VISIT__15.toDiscountPromotionsEntity();
            AppliedGiftPromotions giftPromotions = TOTAL__173_000__VISIT__15.toGiftPromotionsEntity();
            // when && then
            BadgePromotion badge = BadgeContext.applyPromotions(TOTAL__173_000__VISIT__15.getVisitDay(), discountPromotions, giftPromotions);
            assertEquals(SANTA, badge);
        }

        @Test
        @DisplayName("[Inapplicable] 적용 가능한 이벤트 배지가 존재하지 않아, DEFAULT(없음) 배지를 리턴")
        void Should_ReturnDefault_When_RequestInapplicable() {

            /**
             * Given Fixture Information
             * <혜택 내역>
             * 크리스마스 디데이 할인: -1,000원
             *
             * <총혜택 금액>
             * -1,000원
             *
             * <12월 이벤트 배지>
             * 없음
             */

            // given
            AppliedDiscountPromotions discountPromotions = TOTAL__18_000__VISIT__1.toDiscountPromotionsEntity();
            AppliedGiftPromotions giftPromotions = TOTAL__18_000__VISIT__1.toGiftPromotionsEntity();
            // when && then
            BadgePromotion badge = BadgeContext.applyPromotions(TOTAL__18_000__VISIT__1.getVisitDay(), discountPromotions, giftPromotions);
            assertEquals(DEFAULT, badge);
        }
    }
}
