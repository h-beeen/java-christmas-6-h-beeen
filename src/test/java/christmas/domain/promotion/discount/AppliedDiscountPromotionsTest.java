package christmas.domain.promotion.discount;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static christmas.fixture.OrdersFixture.VALID__A;
import static christmas.fixture.PromotionsFixture.TOTAL__173_000__VISIT__15;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("[AppliedDiscountPromotion] - Domain Layer")
class AppliedDiscountPromotionsTest {

    @Nested
    @DisplayName("[Utility Methods] public 접근제어자로 선언된 기능 메소드 테스트")
    class utilityMethods {

        @Test
        @DisplayName("[getTotalDiscountAmount] 총 할인 금액을 계산 후 리턴합니다")
        void getTotalDiscountAmount() {
            /**
             * Given Fixture Information
             * 주말 할인: -4,046원
             * 크리스마스 디데이 할인: -2,400원
             * 총 할인 : -6,446원
             **/
            var discountPromotions = TOTAL__173_000__VISIT__15.toDiscountPromotionsEntity();
            // when
            int totalDiscountAmount = discountPromotions.getTotalDiscountAmount();
            // then
            assertEquals(totalDiscountAmount, 6_446);
        }

        @Test
        @DisplayName("[getExpectedPayment] 할인 적용 이후 실제 소비자 계산 금액을 리턴합니다.")
        void getExpectedPayment() {
            /**
             * Given Fixture Information
             * 총 가격 173,000원
             * 총 할인 : 6,446원
             * 소비자 할인가 : 166,554원
             * Given && when */
            var discountPromotions = TOTAL__173_000__VISIT__15.toDiscountPromotionsEntity();
            var expectedPayment = discountPromotions.getExpectedPayment(VALID__A.toEntity());
            // then
            assertEquals(expectedPayment, 166_554);
        }
    }
}
