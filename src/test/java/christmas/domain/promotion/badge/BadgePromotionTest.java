package christmas.domain.promotion.badge;

import christmas.domain.consumer.VisitDay;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static christmas.domain.promotion.badge.BadgePromotion.*;
import static org.junit.jupiter.api.Assertions.*;

@Nested
@DisplayName("[BadgePromotion] - Domain Layer")
class BadgePromotionTest {

    @Nested
    @DisplayName("[Utility Methods] public 접근제어자로 선언된 기능 메소드 테스트")
    class UtilityMethods {

        @Test
        @DisplayName("[isApplicable] 인자로 받은 총 혜택 금액을 바탕으로, 해당 혜택을 적용 가능한지 확인한다.")
        void Should_ReturnValidBoolean_When_RequestTotalBenefitPrice() {
            // given
            final var applicableStarBadge = 5000; // STAR
            final var applicableTreeBadge = 10000; // TREE
            final var applicableSantaBadge = 20000; // SANTA
            // then - 1) True Case
            assertAll(
                    () -> assertTrue(STAR.isApplicable(applicableStarBadge)),
                    () -> assertTrue(TREE.isApplicable(applicableTreeBadge)),
                    () -> assertTrue(SANTA.isApplicable(applicableSantaBadge))
            );
            // then -2) False Case
            assertAll(
                    () -> assertFalse(TREE.isApplicable(applicableStarBadge)),
                    () -> assertFalse(SANTA.isApplicable(applicableStarBadge)),
                    () -> assertFalse(SANTA.isApplicable(applicableTreeBadge))
            );
        }

        @Test
        @DisplayName("[isPromotionPeriod] 인자로 받은 VisitDay가 해당 배지 프로모션 적용 기간에 있다면 true, 아니라면 false 리턴")
        void Should_ReturnValidBoolean_When_RequestVisitDay() {
            // given
            VisitDay visitDay = VisitDay.create(25);
            // when && then
            assertAll(
                    () -> assertTrue(SANTA.isPromotionPeriod(visitDay)),
                    () -> assertTrue(TREE.isPromotionPeriod(visitDay)),
                    () -> assertTrue(STAR.isPromotionPeriod(visitDay)),
                    () -> assertTrue(DEFAULT.isPromotionPeriod(visitDay))
            );
        }
    }
}
