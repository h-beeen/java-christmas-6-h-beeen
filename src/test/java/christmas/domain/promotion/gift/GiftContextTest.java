package christmas.domain.promotion.gift;

import christmas.domain.consumer.VisitDay;
import christmas.fixture.OrdersFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static christmas.domain.promotion.badge.BadgePromotion.DEFAULT;
import static christmas.domain.promotion.gift.GiftPromotion.GIFT_CHAMPAGNE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("[GiftContext] - Domain Layer")
class GiftContextTest {

    @Nested
    @DisplayName("[create] 정적 팩토리 메소드 / 생성자 테스트")
    class create {

        @Test
        @DisplayName("[Success] 방문일, 주문, 이벤트 배지를 인자로, 적용 가능한 할인 프로모션을 생성합니다.")
        void Should_Success_When_RequestValid() {
            /**
             * Given Fixture Information
             * 방문일 : 12/25
             * 주문 : "양송이수프-1,티본스테이크-2,초코케이크-3,제로콜라-4"
             * 증정 이벤트 : 샴페인 1개
             */
            var visitDay = VisitDay.create(25);
            var twoIcecreamOrders = OrdersFixture.VALID__A.toEntity();
            // when && then
            assertDoesNotThrow(() -> GiftContext.create(visitDay, twoIcecreamOrders, DEFAULT));
        }
    }

    @Nested
    @DisplayName("[applyPromotions] 적용 가능한 프로모션을 적용하고 프로모션 EnumMap을 리턴")
    class applyPromotions {

        @Test
        @DisplayName("[Success] 적용 가능한 프로모션을 적용하고 프로모션 EnumMap을 리턴")
        void Should_ReturnValidBadge_When_RequestApplicable() {
            /**
             * Given Fixture Information
             * 방문일 : 12/25
             * 주문 : "양송이수프-1,티본스테이크-2,초코케이크-3,제로콜라-4"
             * 증정 이벤트 : 샴페인 1개
             */
            var visitDay = VisitDay.create(1);
            var twoIcecreamOrders = OrdersFixture.VALID__A.toEntity();
            // when
            var giftContext = GiftContext.create(visitDay, twoIcecreamOrders, DEFAULT);
            // when
            var giftPromotions = giftContext.applyPromotions();
            //then
            assertAll(
                    () -> assertThat(giftPromotions).hasSize(1),
                    () -> assertEquals(giftPromotions.get(GIFT_CHAMPAGNE), 1)
            );
        }
    }
}
