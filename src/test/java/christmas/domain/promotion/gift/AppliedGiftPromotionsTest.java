package christmas.domain.promotion.gift;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static christmas.fixture.PromotionsFixture.TOTAL__173_000__VISIT__15;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("[AppliedGiftPromotion] - Domain Layer")
class AppliedGiftPromotionsTest {

    @Nested
    @DisplayName("[Utility Methods] public 접근제어자로 선언된 기능 메소드 테스트")
    class utilityMethods {

        @Test
        @DisplayName("[getTotalGiftPrice] 증정 상품의 총액을 계산해 리턴한다.")
        void Should_ReturnTotalPrice_When_ValidRequest() {
            /**
             * Given Fixture Information
             * 증정 이벤트 : 25,000원
             **/
            var giftPromotions = TOTAL__173_000__VISIT__15.toGiftPromotionsEntity();
            // when && then
            assertEquals(25_000, giftPromotions.getTotalGiftPrice());
        }

        @Test
        @DisplayName("[getTotalBenefit] 증정 상품의 총액을 계산해 리턴한다.")
        void Should_ReturnTotalBenefitAmount_When_ValidRequest() {
            /**
             * Given Fixture Information
             * 할인 가격 : 6,446원
             * 증정 이벤트 : 25,000원
             * 총 혜택 가격 : 31,446원
             **/
            var discountPromotions = TOTAL__173_000__VISIT__15.toDiscountPromotionsEntity();
            var giftPromotions = TOTAL__173_000__VISIT__15.toGiftPromotionsEntity();
            // when && then
            assertEquals(31_446, giftPromotions.getTotalBenefit(discountPromotions));
        }
    }
}
