package christmas.domain.consumer.constants;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static christmas.domain.consumer.constants.Menu.CHAMPAGNE;
import static christmas.domain.consumer.constants.MenuCategory.*;
import static christmas.exception.ErrorCode.INVALID_ORDER;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("[Menu] - Domain Layer")
class MenuTest {

    @Nested
    @DisplayName("[findMenuByName] 음식의 이름을 입력받고, Menu 형식의 enum을 리턴 / 찾지 못하면 예외를 던진다.")
    class findMenuByName {


        @ParameterizedTest
        @ValueSource(strings = {
                "양송이수프",
                "타파스",
                "시저샐러드",
                "티본스테이크",
                "바비큐립",
                "해산물파스타",
                "크리스마스파스타",
                "초코케이크",
                "아이스크림",
                "제로콜라",
                "레드와인",
                "샴페인"
        })
        @DisplayName("[Success] Enum에 정의되는 이름이 요청되어, 메뉴 Enum을 리턴한다.")
        void Should_Success_When_hasNameInEnum(String nameInput) {
            // given - nameInput
            // when
            Menu menu = Menu.findMenuByName(nameInput);
            // then
            assertEquals(menu.getName(), nameInput);
        }

        @ParameterizedTest
        @ValueSource(strings = {
                "우아한테크코스",
                "벌써4주차네요",
                "아주즐겁습니다",
                "오늘저녁은특제샐러드"
        })
        @DisplayName("[Exception] Enum에 정의되는 이름이 요청되지 않아, 예외를 던진다.")
        void Should_ThrowException_When_HasNotNameInEnum(String nameInput) {
            // given - nameInput
            // when && Then
            assertThatIllegalArgumentException()
                    .isThrownBy(() -> Menu.findMenuByName(nameInput))
                    .withMessageContaining(INVALID_ORDER.getMessage());
        }
    }

    @Nested
    @DisplayName("[Utility Methods] public 접근제어자로 선언된 기능 메소드 테스트")
    class utilityMethods {


        @ParameterizedTest
        @ValueSource(ints = {
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10
        })
        @DisplayName("[calculatePrice] 인자로 받은 수량을 기준으로, 메뉴의 가격을 계산한다.")
        void calculatePrice(int quantity) {
            // given && when
            int price = CHAMPAGNE.calculatePrice(quantity);
            assertEquals(price, quantity * CHAMPAGNE.getPrice());
        }
    }

    @Nested
    @DisplayName("[Validation Methods] public 접근제어자로 선언된 검증 메소드 테스트")
    class validationMethods {

        @Test
        @DisplayName("[isSameCategory] 인자의 MenuCategory와 메뉴의 카테고리가 같다면 true, 아니라면 false 리턴")
        void isSameCategory() {
            // given && when - expect BEVERAGE
            assertAll(
                    () -> assertTrue(CHAMPAGNE.isSameCategory(BEVERAGE)),
                    () -> assertFalse(CHAMPAGNE.isSameCategory(DESSERT)),
                    () -> assertFalse(CHAMPAGNE.isSameCategory(APPETIZER)),
                    () -> assertFalse(CHAMPAGNE.isSameCategory(MAIN_DISH))
            );
        }
    }
}
