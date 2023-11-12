package christmas;

import camp.nextstep.edu.missionutils.Console;
import christmas.controller.OrderController;
import christmas.controller.PromotionController;
import christmas.controller.VisitDayController;
import christmas.domain.order.Orders;
import christmas.domain.order.VisitDay;
import christmas.domain.promotion.promotion.discount.AppliedDiscountPromotions;

public class Application {
    public static void main(String[] args) {
        VisitDay visitDay = VisitDayController.requestVisitDay();
        Orders orders = OrderController.requestOrders();
        VisitDayController.responseVisitDay(visitDay);
        OrderController.responseOrdersResult(orders);
        AppliedDiscountPromotions appliedPromotion = AppliedDiscountPromotions.create(visitDay, orders);
        PromotionController.responseGiftResponse(appliedPromotion);

//        //todo 증정메뉴 출력
//        //todo 혜택내역 출력
//        //todo 총혜택금액 출력
//        //todo 할인 후 예상 결제금액 출력
//        //todo 이벤트 배지 출력
//
//        EnumMap<DiscountPromotion, Integer> promotions = appliedPromotion.getPromotions();
//        int i = appliedPromotion.calculateTotalDiscountBenefit();
//
//        System.out.println("총 할인 혜택 금액 = " + i);
//
//        promotions.forEach((key, value) -> System.out.println(key.getPromotionName() + " : " + value));

        Console.close();
    }
}
