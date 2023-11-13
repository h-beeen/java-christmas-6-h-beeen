package christmas.controller;

import camp.nextstep.edu.missionutils.test.NsTest;
import christmas.domain.consumer.Orders;
import christmas.fixture.OrdersFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static christmas.exception.ErrorCode.ERROR_PREFIX;
import static christmas.view.constants.ResponseMessage.RESPONSE_MENU_ORDERS_RESULT;
import static christmas.view.constants.ResponseMessage.RESPONSE_TOTAL_ORIGIN_PRICE_RESULT;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@DisplayName("[OrderController] - Controller Layer")
class OrderControllerTest {

    @Nested
    @DisplayName("[requestOrders] 주문할 메뉴, 갯수를 요청하고, 요청이 제약조건에 맞지 않으면 예외를 던진다.")
    class requestOrders extends NsTest {

        @Override
        protected void runMain() {
            OrderController.requestOrders();
        }

        @ParameterizedTest
        @EnumSource(
                value = OrdersFixture.class,
                names = {
                        "VALID__A",
                        "VALID__B"
                }
        )
        @DisplayName("[Success] 사용자에게 유효한 메뉴 및 갯수 입력을 요청받아 성공한다.")
        void Should_Success_When_ValidOrderRequest(OrdersFixture fixture) {
            // given && when && then
            assertDoesNotThrow(() -> run(fixture.getValue()));
        }

        @ParameterizedTest
        @EnumSource(
                value = OrdersFixture.class,
                names = {
                        "INVALID__ONLY_CONTAIN_DRINK",
                        "INVALID__CONTAIN_BLANK",
                        "INVALID__DUPLICATED_MENU",
                        "INVALID__NONEXISTENCE_MENU",
                        "INVALID__ENDS_WITH_COMMA",
                        "INVALID__EXCEEDS_MAXIMUM_MENU_QUANTITY_CONSTRAINT",
                        "INVALID__LESS_MINIMUM_MENU_QUANTITY_CONSTRAINT",
                        "INVALID__NEGATIVE_MENU_QUANTITY",
                        "INVALID__MENU_FORM"
                }
        )
        @DisplayName("[Exception] 사용자가 입력한 메뉴 요청이 부적절해 예외를 던진다.")
        void Should_ThrowException_When_InvalidOrderRequest(OrdersFixture fixture) {
            // given && when && then
            runException(fixture.getValue());
            assertThat(output()).contains(ERROR_PREFIX);
        }
    }

    @Nested
    @DisplayName("[responseOrdersResult] 주문 메뉴를 형식에 맞게 출력하도록 View에 요청 성공")
    class responseOrdersResult extends NsTest {

        @Override
        protected void runMain() {
            // OrderRequest = "양송이수프-1,티본스테이크-2,초코케이크-3,제로콜라-4"
            Orders validOrders = OrdersFixture.VALID__A.toEntity();
            OrderController.responseOrdersResult(validOrders);
        }

        @Test
        @DisplayName("[Success] 주문 내역을 형식에 맞게 출력한다.")
        void Should_Success_When_ValidOrderRequest() {
            // given && when && then
            run();
            assertThat(output())
                    .contains(RESPONSE_MENU_ORDERS_RESULT.getMessage())
                    .contains("양송이수프 1개")
                    .contains("티본스테이크 2개")
                    .contains("초코케이크 3개")
                    .contains("제로콜라 4개");
        }
    }

    @Nested
    @DisplayName("[responseOrdersResult] 할인 전 총주문 금액을 형식에 맞게 출력하도록 View에 요청 성공")
    class responseTotalOriginPriceResult extends NsTest {

        @Override
        protected void runMain() {
            // OrderRequest = "양송이수프-1,티본스테이크-2,초코케이크-3,제로콜라-4"
            // TotalOriginPrice = "173,000원"
            Orders validOrders = OrdersFixture.VALID__A.toEntity();
            OrderController.responseTotalOriginPriceResult(validOrders);
        }

        @Test
        @DisplayName("[Success] 할인 전 총주문 금액을 형식에 맞게 출력한다.")
        void Should_Success_When_ValidOrderRequest() {
            // given && when && then
            run();
            assertThat(output())
                    .contains(RESPONSE_TOTAL_ORIGIN_PRICE_RESULT.getMessage())
                    .contains("173,000원");
        }
    }
}
