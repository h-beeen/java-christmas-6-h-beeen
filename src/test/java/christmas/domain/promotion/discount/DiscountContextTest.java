package christmas.domain.promotion.discount;

import christmas.domain.consumer.VisitDay;
import christmas.fixture.OrdersFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static christmas.domain.promotion.badge.BadgePromotion.DEFAULT;
import static christmas.domain.promotion.discount.DiscountPromotion.CHRISTMAS_D_DAY_DISCOUNT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("[DiscountContext] - Domain Layer")
class DiscountContextTest {

    @Nested
    @DisplayName("[create] 정적 팩토리 메소드 / 생성자 테스트")
    class create {

        @Test
        @DisplayName("[Success] 방문일, 주문, 이벤트 배지를 인자로, 적용 가능한 할인 프로모션을 생성합니다.")
        void Should_Success_When_RequestValid() {
            /**
             * Given Fixture Information
             * 방문일 : 12/1
             * 주문 : 아이스크림-2
             * 크리스마스 디데이 할인: -1,000원
             */
            var visitDay = VisitDay.create(1);
            var twoIcecreamOrders = OrdersFixture.VALID__E.toEntity();
            // when
            var discountContext = DiscountContext.create(visitDay, twoIcecreamOrders, DEFAULT);
            List<DiscountPromotion> applicablePromotions = discountContext.getApplicablePromotions();
            // then
            assertAll(
                    () -> assertThat(applicablePromotions).hasSize(1),
                    () -> assertEquals(applicablePromotions.get(0), CHRISTMAS_D_DAY_DISCOUNT)
            );
        }
    }

    @Nested
    @DisplayName("[applyPromotions] 적용 가능한 프로모션을 적용하고 프로모션 EnumMap을 리턴")
    class applyPromotions {

        @Test
        @DisplayName("[Applicable] 적용 가능한 이벤트 배지가 존재해, 해당 배지를 리턴")
        void Should_ReturnValidBadge_When_RequestApplicable() {
            /**
             * Given Fixture Information
             * 방문일 : 12/1
             * 주문 : 아이스크림-2
             * 크리스마스 디데이 할인: -1,000원
             */
            var visitDay = VisitDay.create(1);
            var twoIcecreamOrders = OrdersFixture.VALID__E.toEntity();
            // when
            var discountContext = DiscountContext.create(visitDay, twoIcecreamOrders, DEFAULT);
            // when
            var applicableDiscountPromotions = discountContext.applyPromotions(visitDay, twoIcecreamOrders);
            //then
            assertAll(
                    () -> assertThat(applicableDiscountPromotions).hasSize(1),
                    () -> assertEquals(applicableDiscountPromotions.get(0), 1_000)
            );
        }
    }
}
