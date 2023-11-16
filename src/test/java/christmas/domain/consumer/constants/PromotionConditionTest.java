package christmas.domain.consumer.constants;

import christmas.domain.consumer.Orders;
import christmas.domain.consumer.VisitDay;
import christmas.domain.promotion.badge.BadgePromotion;
import christmas.domain.promotion.constants.PromotionCondition;
import christmas.fixture.OrdersFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static christmas.domain.promotion.badge.BadgePromotion.DEFAULT;
import static christmas.domain.promotion.constants.PromotionCondition.CHRISTMAS_D_DAY_PROMOTION_CONDITION;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Nested
@DisplayName("[PromotionCondition] - Domain Layer")
class PromotionConditionTest {

    @Nested
    @DisplayName("[isApplicable] 해당 프로모션 적용 여부를 검증하고, 적용 가능하면 true, 아니라면 false 리턴")
    class UtilityMethods {

        /**
         * 1. 해당 방문일에 적용할 수 있는 프로모션이라면 true
         * 2. 해당 방문일과, 주문 금액으로 프로모션을 적용할 수 있는 조건이라면 true
         * 3. 유저의 보유중인 배지(기본값 : DEFAULT[없음])로 적용할 수 있는 프로모션이라면 true
         * 4. 상기 3개 조건이 모두 true일 경우 true 리턴 / 아니라면 false 리턴
         */
        @ParameterizedTest
        @EnumSource(names = {
                "CHRISTMAS_D_DAY_PROMOTION_CONDITION",
                "WEEKEND_PROMOTION_CONDITION",
                "CHAMPAGNE_GIFT_CONDITION"
        })
        @DisplayName("[isApplicable] True Case")
        void Should_ReturnTrue_When_RequestApplicableCase(PromotionCondition condition) {
            /**
             * Given Fixture Information
             * 방문일 : 15일
             * 주말 할인: -4,046원
             * 크리스마스 디데이 할인: -2,400원
             * 증정 이벤트: -25,000원 (샴페인)
             **/
            VisitDay visitDay = VisitDay.create(15);
            Orders orders = OrdersFixture.VALID__A.toEntity();
            BadgePromotion defaultBadge = DEFAULT;
            // when && then
            assertTrue(condition.isApplicable(visitDay, orders, defaultBadge));
        }

        @ParameterizedTest
        @EnumSource(names = {
                "WEEKDAY_PROMOTION_CONDITION",
                "SPECIAL_PROMOTION_CONDITION"
        })
        @DisplayName("[isApplicable] False Case")
        void Should_ReturnFalse_When_RequestInapplicableCase(PromotionCondition condition) {
            /**
             * Given Fixture Information
             * 방문일 : 15일
             * 주말 할인: -4,046원
             * 크리스마스 디데이 할인: -2,400원
             * 증정 이벤트: -25,000원 (샴페인)
             **/
            VisitDay visitDay = VisitDay.create(15);
            Orders orders = OrdersFixture.VALID__A.toEntity();
            BadgePromotion defaultBadge = DEFAULT;
            // when && then
            assertFalse(condition.isApplicable(visitDay, orders, defaultBadge));
        }

        @ParameterizedTest
        @EnumSource(names = {
                "WEEKEND_PROMOTION_CONDITION",
                "CHAMPAGNE_GIFT_CONDITION",
                "WEEKDAY_PROMOTION_CONDITION",
                "SPECIAL_PROMOTION_CONDITION"
        })
        @DisplayName("[isPromotionPeriod] 인자로 받은 VisitDay가 해당 배지 프로모션 적용 기간이므로 true 리턴")
        void Should_ReturnTrue_When_isPromotionPeriod(PromotionCondition condition) {
            /**
             * Given Fixture Information
             * 크리스마스 디데이 이벤트 (1일 ~ 25일)
             * 크리스마스 디데이 제외 모든 이벤트 (1 ~ 31일)
             **/
            VisitDay visitDay = VisitDay.create(31);
            // when && then
            assertTrue(condition.isPromotionPeriod(visitDay));
        }

        @Test
        @DisplayName("[isPromotionPeriod] 인자로 받은 VisitDay가 해당 배지 프로모션 적용 기간이 아니므로 false 리턴")
        void Should_ReturnFalse_When_isNotPromotionPeriod() {
            /**
             * Given Fixture Information
             * 크리스마스 디데이 이벤트 (1일 ~ 25일)
             * 크리스마스 디데이 제외 모든 이벤트 (1 ~ 31일)
             **/
            VisitDay visitDay = VisitDay.create(31);
            // when && then
            assertFalse(CHRISTMAS_D_DAY_PROMOTION_CONDITION.isPromotionPeriod(visitDay));
        }
    }
}
