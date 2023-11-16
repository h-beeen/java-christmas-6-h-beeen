package christmas.utility;

import christmas.domain.utility.Parser;
import christmas.fixture.OrdersFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import static christmas.exception.ErrorCode.INVALID_DATE;
import static christmas.exception.ErrorCode.INVALID_ORDER;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@DisplayName("[Parser] Global/Utility Layer")
class ParserTest {

    @Nested
    @DisplayName("[parseDateInput] 날짜 요청에 대해 유효성 검증을 실시하고, int로 리턴")
    class parseDateInput {

        @ParameterizedTest
        @ValueSource(strings = {
                "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
                "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
                "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"
        })
        @DisplayName("[Success] 유효한 정수를 입력받아 성공한다.")
        void Should_Success_When_RequestValidInteger(String input) {
            // given && when && then
            assertDoesNotThrow(() -> Parser.parseDateInput(input));
        }

        @ParameterizedTest
        @ValueSource(strings = {
                "2147483650", "-2147483650"
        })
        @DisplayName("[Exception] 유효하지 않은 정수가 입력되어 예외를 던진다.")
        void Should_ThrowException_When_RequestInvalidInteger(String input) {
            // given && when && then
            assertThatIllegalArgumentException()
                    .isThrownBy(() -> Parser.parseDateInput(input))
                    .withMessageContaining(INVALID_DATE.getMessage());
        }
    }

    @Nested
    @DisplayName("[parseMenuOrdersInputByDelimiter] 메뉴 입력 요청을 하이픈(-) 단위로 나누고, EnumMap에 담아 리턴")
    class parseMenuOrdersInputByDelimiter {

        @ParameterizedTest
        @EnumSource(
                value = OrdersFixture.class,
                names = {
                        "VALID__A",
                        "VALID__B",
                        "VALID__C",
                        "VALID__D",
                        "VALID__E",
                }
        )
        @DisplayName("[Exception] 유효하지 않은 정수가 입력되어 예외를 던진다.")
        void Should_ThrowException_When_RequestInvalidInteger(OrdersFixture input) {
            // given && when && then
            assertThatIllegalArgumentException()
                    .isThrownBy(() -> Parser.parseDateInput(input.getValue()))
                    .withMessageContaining(INVALID_DATE.getMessage());
        }

        /**
         * 1. (한글메뉴이름 하이픈 숫자 콤마) 패턴이 아닐경우 예외를 던진다
         * 2. 요청에 공백 등 whiteSpace가 포함되어 있으면 예외를 던진다.
         * 3. 메뉴 이름이 MENU 열거형 클래스에 포함되어 있지 않으면 예외를 던진다.
         * 4. 주문 갯수가 0 이하인 요소가 있다면 예외를 던진다.
         */

        @ParameterizedTest
        @ValueSource(strings = {
                "1-제로콜라,2-해산물파스타",
                "크리스마스 파스타 - 1",
                "해빈이의특제파스타-1,해빈이의특제피자-2",
                "타파스-0,초코케이크-6"
        })
        @DisplayName("[Exception] 유효한 주문을 입력받아 성공한다.")
        void Should_ThrowException_When_RequestInValidOrders(String orderInput) {
            // given && when && then
            assertThatIllegalArgumentException()
                    .isThrownBy(() -> Parser.parseMenuOrdersInputByDelimiter(orderInput))
                    .withMessageContaining(INVALID_ORDER.getMessage());
        }
    }
}
