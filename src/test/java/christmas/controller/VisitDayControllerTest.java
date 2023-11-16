package christmas.controller;

import camp.nextstep.edu.missionutils.test.NsTest;
import christmas.domain.consumer.VisitDay;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static christmas.exception.ErrorCode.ERROR_PREFIX;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@DisplayName("[VisitDayController] - Controller Layer")
class VisitDayControllerTest {

    @Nested
    @DisplayName("[requestVisitDay] 주문할 메뉴, 갯수를 요청하고, 요청이 제약조건에 맞지 않으면 예외를 던진다.")
    class requestVisitDay extends NsTest {

        @Override
        protected void runMain() {
            VisitDayController.requestVisitDay();
        }

        @ParameterizedTest
        @ValueSource(strings = {
                "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
                "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
                "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"
        })
        @DisplayName("[Success] 사용자에게 유효한 방문일을 입력 받아 성공한다.")
        void Should_Success_When_ValidVisitDayRequest(String visitDayRequest) {
            // given && when && then
            assertSimpleTest(() ->
                    assertDoesNotThrow(() -> run(visitDayRequest)));
        }

        @ParameterizedTest
        @ValueSource(strings = {
                "-50", "-10", "-5", "-1", "35", "55", "2147483650", "-2147483650"
        })
        @DisplayName("[Success] 사용자에게 유효하지 않은 방문일을 입력 받아 예외를 던진다.")
        void Should_ThrowException_When_InValidVisitDayRequest(String visitDayRequest) {
            // given && when && then
            runException(visitDayRequest);
            assertThat(output()).contains(ERROR_PREFIX);
        }
    }

    @Nested
    @DisplayName("[responseVisitDay] 방문 일자를 형식에 맞게 출력하도록 View에 요청 성공")
    class responseVisitDay extends NsTest {

        @Override
        protected void runMain() {
            VisitDay visitDay = VisitDay.create(22);
            VisitDayController.responseVisitDay(visitDay);
        }
           
        @Test
        @DisplayName("[Success] 사용자에게 유효한 방문일을 입력 받아 성공한다.")
        void Should_Success_When_ValidVisitDayRequest() {
            // given && when && then
            assertDoesNotThrow(() -> run());
            assertThat(output())
                    .contains("12월 22일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
        }
    }
}
