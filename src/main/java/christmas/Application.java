package christmas;

import camp.nextstep.edu.missionutils.Console;
import christmas.controller.OrderController;
import christmas.controller.VisitDayController;
import christmas.domain.order.Orders;
import christmas.domain.order.VisitDay;
import christmas.domain.promotion.promotion.post.AppliedPostPromotions;
import christmas.domain.promotion.promotion.post.PostPromotion;
import christmas.domain.promotion.promotion.pre.AppliedPrePromotions;
import christmas.domain.promotion.promotion.pre.PrePromotion;

import java.util.EnumMap;

public class Application {
    public static void main(String[] args) {
        VisitDay visitDay = VisitDayController.requestVisitDay();
        Orders orders = OrderController.requestOrders();
        VisitDayController.responseVisitDay(visitDay);
        OrderController.responseOrdersResult(orders);

        AppliedPrePromotions appliedPrePromotion = AppliedPrePromotions.create(visitDay, orders);
        AppliedPostPromotions appliedPostPromotion = AppliedPostPromotions.create(appliedPrePromotion);

        EnumMap<PrePromotion, Integer> prePromotions = appliedPrePromotion.getPrePromotions();
        EnumMap<PostPromotion, Integer> postPromotions = appliedPostPromotion.getPostPromotions();
        System.out.println("prePromotions = " + appliedPrePromotion.calculateTotalDiscountBenefit());

        prePromotions.forEach((key, value) -> System.out.println(key.name() + " : " + value));
        postPromotions.forEach((key, value) -> System.out.println(key.name() + " : " + value));

        Console.close();
    }
}
