package christmas.controller;

import camp.nextstep.edu.missionutils.test.NsTest;
import christmas.domain.consumer.Orders;
import christmas.domain.consumer.VisitDay;
import christmas.fixture.OrdersFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@DisplayName("[PromotionController] - Controller Layer")
public class PromotionControllerTest {
    private static final String LINE_SEPARATOR = System.lineSeparator();

    @Nested
    @DisplayName("[responseApplicableBenefitResult] 혜택 관련 응답을 형식에 맞게 출력하도록 View에 요청 성공")
    class responseVisitDay extends NsTest {

        @Override
        protected void runMain() {
            VisitDay visitDay = VisitDay.create(15);
            Orders orders = OrdersFixture.VALID__A.toEntity();
            PromotionController.responseAppliedBenefitResult(visitDay, orders);
        }

        @Test
        @DisplayName("[Success] 사용자에게 유효한 방문일을 입력 받아 성공한다.")
        void Should_Success_When_ValidVisitDayRequest() {
            // given && when && then
            assertDoesNotThrow(() -> run());
            assertThat(output())
                    .contains("<증정 메뉴>")
                    .contains("샴페인 1개")
                    .contains("<혜택 내역>")
                    .contains("주말 할인: -4,046원")
                    .contains("크리스마스 디데이 할인: -2,400원")
                    .contains("증정 이벤트: -25,000원")
                    .contains("<총혜택 금액>")
                    .contains("-31,446원")
                    .contains("<할인 후 예상 결제 금액>")
                    .contains("166,554원")
                    .contains("<12월 이벤트 배지>")
                    .contains("산타");
        }
    }
}
