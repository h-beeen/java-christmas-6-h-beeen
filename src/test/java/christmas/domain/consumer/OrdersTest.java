package christmas.domain.consumer;

import christmas.domain.utility.Parser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static christmas.domain.consumer.constants.MenuCategory.*;
import static christmas.exception.ErrorCode.EXCEED_ORDER_QUANTITY_LIMIT;
import static christmas.exception.ErrorCode.ORDERS_ONLY_CONTAIN_BEVERAGES;
import static christmas.fixture.OrdersFixture.*;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("[Orders] - Domain Layer")
class OrdersTest {

    @Nested
    @DisplayName("[create] 정적 팩토리 메소드 / 생성자 테스트")
    class create {

        /**
         * 1. 음료만 주문할 경우 예외를 던진다.
         * 2. 주문의 총 갯수가 20개를 초과할 경우 예외를 던진다.
         */

        @Test
        @DisplayName("[Success] 정상적인 메뉴 요청으로 객체 생성 성공")
        void Should_Success_When_ValidRequest() {
            // given && when && then
            Assertions.assertDoesNotThrow(VALID__A::toEntity);
        }

        @Test
        @DisplayName("[Exception] 음료만 주문되어, 예외를 던진다.")
        void Should_ThrowException_When_OrderContainOnlyBeverages() {
            // given
            final var beverages = INVALID__ONLY_CONTAIN_BEVERAGES.getValue();
            final var parsedBeverages = Parser.parseMenuOrdersInputByDelimiter(beverages);
            // when && then
            assertThatIllegalArgumentException()
                    .isThrownBy(() -> Orders.create(parsedBeverages))
                    .withMessageContaining(ORDERS_ONLY_CONTAIN_BEVERAGES.getMessage());
        }

        @ParameterizedTest
        @ValueSource(strings = {
                "양송이수프-1,타파스-19,시저샐러드-5,샴페인-5",
                "초코케이크-30",
                "크리스마스파스타-10,타파스-5,시저샐러드-5,양송이수프-5"
        })
        @DisplayName("[Exception] 주문 수량이 20개를 초과하여, 예외를 던진다.")
        void Should_ThrowException_When_OrderContainOnlyBeverages(String exceedsQuantityOrders) {
            // given
            final var parsedBeverages = Parser.parseMenuOrdersInputByDelimiter(exceedsQuantityOrders);
            // when && then
            assertThatIllegalArgumentException()
                    .isThrownBy(() -> Orders.create(parsedBeverages))
                    .withMessageContaining(EXCEED_ORDER_QUANTITY_LIMIT.getMessage());
        }
    }

    @Nested
    @DisplayName("[Utility Methods] public 접근제어자로 선언된 기능 메소드 테스트")
    class utilityMethods {

        /**
         * Given Fixture Information
         * OrderList : "양송이수프-1,티본스테이크-2,초코케이크-3,제로콜라-4"
         * TotalOriginPrice : 173,000
         */

        @Test
        @DisplayName("[calculateTotalOriginPrice] 메뉴의 총 가격을 계산하여 리턴한다.")
        void calculateTotalOriginPrice() {
            // given -> expect 173,000
            Orders orders = VALID__A.toEntity();
            // when
            final var calculatedTotalOriginPrice = orders.calculateTotalOriginPrice();
            // then 173,000원
            assertEquals(calculatedTotalOriginPrice, 173_000);
        }

        @Test
        @DisplayName("[countOrdersByMenuType] 인자로 받은 메뉴의 종류에 해당하는 주문의 갯수를 계산한다.")
        void countOrdersByMenuType() {
            // given -> expect : "애피타이저-1,메인-2,디저트-3,음료-4"
            Orders orders = VALID__A.toEntity();
            // when
            final var appetizerCount = orders.countOrdersByMenuType(APPETIZER);
            final var mainDishCount = orders.countOrdersByMenuType(MAIN_DISH);
            final var dessertCount = orders.countOrdersByMenuType(DESSERT);
            final var beverageCount = orders.countOrdersByMenuType(BEVERAGE);
            // then
            assertAll(
                    () -> assertEquals(1, appetizerCount),
                    () -> assertEquals(2, mainDishCount),
                    () -> assertEquals(3, dessertCount),
                    () -> assertEquals(4, beverageCount)
            );
        }


        @Test
        @DisplayName("[True] 주문에 해당 카테고리가 있다면 true, 없다면 false 리턴")
        void Should_True_When_OrdersContainTheirCategory() {

            /**
             * Given Fixture Information
             * OrderList : "초코케이크-1,제로콜라-1"
             * Category : 디저트-1, 음료-1
             */

            Orders orders = VALID__C.toEntity();
            // when && then
            assertAll(
                    () -> assertTrue(orders.hasMenuCategory(DESSERT)),
                    () -> assertTrue(orders.hasMenuCategory(BEVERAGE)),
                    () -> assertFalse(orders.hasMenuCategory(MAIN_DISH)),
                    () -> assertFalse(orders.hasMenuCategory(APPETIZER))
            );
        }
    }
}
