package christmas;

import camp.nextstep.edu.missionutils.Console;
import christmas.controller.OrderController;
import christmas.controller.VisitDayController;
import christmas.controller.dto.DiscountResponse;
import christmas.controller.dto.GiftResponse;
import christmas.controller.mapper.DiscountResponseMapper;
import christmas.controller.mapper.GiftResponseMapper;
import christmas.domain.consumer.Orders;
import christmas.domain.consumer.VisitDay;
import christmas.domain.promotion.promotion.discount.AppliedDiscountPromotions;
import christmas.domain.promotion.promotion.gift.AppliedGiftPromotions;
import christmas.view.OutputWriter;

import static christmas.view.constants.ResponseMessage.*;

public class Application {
    private static final DiscountResponseMapper DISCOUNT_RESPONSE_MAPPER = DiscountResponseMapper.getInstance();
    private static final GiftResponseMapper GIFT_RESPONSE_MAPPER = GiftResponseMapper.getInstance();

    public static void main(String[] args) {

        VisitDay visitDay = VisitDayController.requestVisitDay();
        Orders orders = OrderController.requestOrders();

        VisitDayController.responseVisitDay(visitDay);
        OrderController.responseOrdersResult(orders);

        AppliedDiscountPromotions appliedDiscountPromotions = AppliedDiscountPromotions.create(visitDay, orders);
        AppliedGiftPromotions appliedGiftPromotions = AppliedGiftPromotions.create(visitDay, orders);

        DiscountResponse discountResponse = DISCOUNT_RESPONSE_MAPPER.toResponse(appliedDiscountPromotions);
        GiftResponse giftResponse = GIFT_RESPONSE_MAPPER.toResponse(appliedGiftPromotions);

        OutputWriter.printNewLine();
        OutputWriter.printMessageResponse(RESPONSE_GIFT_RESPONSE);
        OutputWriter.printGiftQuantityResponse(giftResponse);

        OutputWriter.printNewLine();
        OutputWriter.printMessageResponse(RESPONSE_BENEFIT_RESPONSE);
        OutputWriter.printBenefitResponse(discountResponse, giftResponse);

        OutputWriter.printNewLine();
        OutputWriter.printMessageResponse(RESPONSE_TOTAL_BENEFIT_RESPONSE);
        OutputWriter.println(discountResponse.discountTotalPrice() + "원");

        OutputWriter.printNewLine();
        OutputWriter.println("<할인 후 예상 결제 금액>");
        OutputWriter.println(orders.calculateTotalOriginPrice() - discountResponse.discountTotalPrice() + "원");

        // 총혜택내역, 할인 후 예상 결제금액 포맷팅
        // 총혜택내역에 샴페인 가격 더해져야함
        // 혜택내역 -> 샴페인 갯수 -> 가격
        Console.close();
    }
}
