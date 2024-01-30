package christmas.domain.consumer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;

import static christmas.exception.ErrorCode.INVALID_DATE;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("[VisitDay] - Domain Layer")
class VisitDayTest {

    @Nested
    @DisplayName("[create] 정적 팩토리 메소드 / 생성자 테스트")
    class create {

        /**
         * 1. 제약조건에 명시된 Month에 해당 일자(Date)가 존재하지 않을 경우 예외를 던진다.
         */

        @ParameterizedTest
        @ValueSource(ints = {
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
                11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
                21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31
        })
        @DisplayName("[Success] 사용자에게 유효한 방문일을 입력 받아 성공한다.")
        void Should_Success_When_ValidVisitDayRequest(int visitDayRequest) {
            // given && when && then
            assertDoesNotThrow(() -> VisitDay.create(visitDayRequest));
        }

        @ParameterizedTest
        @ValueSource(ints = {
                -5, 0, 33, 34, 35
        })
        @DisplayName("[Exception] 사용자의 방문일이 양수가 아니거나, 해당 달에 포함되어있지 않아 예외를 던진다.")
        void Should_ThrowException_When_InvalidVisitDayRequest(int visitDayRequest) {
            // given && when && then
            assertThatIllegalArgumentException()
                    .isThrownBy(() -> VisitDay.create(visitDayRequest))
                    .withMessageContaining(INVALID_DATE.getMessage());
        }
    }

    @Nested
    @DisplayName("[Utility Methods] public 접근제어자로 선언된 기능 메소드 테스트")
    class utilityMethods {

        @ParameterizedTest
        @ValueSource(ints = {
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
                11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
                21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31
        })
        @DisplayName("[multiplyDate] 날짜에 인자로 들어온 값을 곱해 리턴한다.")
        void multiplyDate(int visitDay) {
            // given
            final var visitDayEntity = VisitDay.create(visitDay);
            // when
            final var multiplyValue = 5;
            final var result = visitDayEntity.multiplyDate(multiplyValue);
            // then
            assertEquals(result, multiplyValue * visitDay);
        }

        @Test
        @DisplayName("[containPeriod] 인자에 들어온 시작일, 종료일 사이에 visitDay가 포함되면 true, 아니라면 false 리턴")
        void containPeriod() {
            // given
            final var startDate = LocalDate.of(2023, 12, 1);
            final var endDate = LocalDate.of(2023, 12, 15);
            // when
            final var containDay = VisitDay.create(12);
            final var notContainDay = VisitDay.create(17);
            // then
            assertAll(
                    () -> assertTrue(containDay.containPeriod(startDate, endDate)),
                    () -> assertFalse(notContainDay.containPeriod(startDate, endDate))
            );
        }

        @Test
        @DisplayName("[isWeekend] 해당 날짜가 프로모션 제약 기준 주말이면 true, 아니라면 false를 리턴합니다.")
        void isWeekend() {
            // given
            final var weekend = VisitDay.create(2);
            final var weekday = VisitDay.create(6);
            // then
            assertAll(
                    () -> assertTrue(weekend.isWeekend()),
                    () -> assertFalse(weekday.isWeekend())
            );
        }

        @Test
        @DisplayName("[isWeekday] 해당 날짜가 프로모션 제약 기준 주중이면 true, 아니라면 false를 리턴합니다.")
        void isWeekday() {
            // given
            final var weekend = VisitDay.create(2);
            final var weekday = VisitDay.create(6);
            // then
            assertAll(
                    () -> assertTrue(weekday.isWeekday()),
                    () -> assertFalse(weekend.isWeekday())
            );
        }

        @ParameterizedTest
        @ValueSource(ints = {
                3, 10, 17, 24, 25, 31
        })
        @DisplayName("[isWeekday] 해당 날짜가 특별 할인(별) 기간이라면 true")
        void isSpecialDay(int visitDay) {
            // given
            final var specialDay = VisitDay.create(visitDay);
            // then
            assertTrue(specialDay.isSpecialDay());
        }
    }
}
