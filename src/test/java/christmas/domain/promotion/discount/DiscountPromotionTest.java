package christmas.domain.promotion.discount;

import christmas.domain.consumer.VisitDay;
import christmas.fixture.OrdersFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static christmas.domain.promotion.discount.DiscountPromotion.CHRISTMAS_D_DAY_DISCOUNT;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("[DiscountPromotion] - Domain Layer")
class DiscountPromotionTest {

    @Nested
    @DisplayName("[applyPromotion] 함수형 인터페이스 로직으로 프로모션 실행 결과를 리턴합니다.")
    class applyPromotion {
        // given
        @Test
        @DisplayName("[Success] 적용 가능한 프로모션을 사용해 프로모션 적용 할인 가격을 리턴합니다.")
        void Should_Success_When_RequestValid() {
            /**
             * Given Fixture Information
             * 방문일 : 1일
             * 주문 : 아이스크림-2
             * 할인 : 크리스마스 디데이 할인 1,000원
             **/
            var visitDay = VisitDay.create(1);
            var twoIceCreamOrders = OrdersFixture.VALID__E.toEntity();
            // when
            var result = CHRISTMAS_D_DAY_DISCOUNT.applyPromotion(visitDay, twoIceCreamOrders);
            // then
            assertEquals(result, 1_000);
        }

        // isApplicable 이미 테스트한 함수 단순 호출로 생략
        // isPromotionPeriod 이미 테스트한 함수 단순 호출로 생략
    }
}
