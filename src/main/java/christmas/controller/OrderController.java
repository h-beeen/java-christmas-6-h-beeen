package christmas.controller;

import christmas.controller.dto.OrderResponse;
import christmas.domain.consumer.Orders;
import christmas.domain.consumer.constants.Menu;
import christmas.domain.utility.Parser;
import christmas.exception.ExceptionHandler;
import christmas.view.InputReader;
import christmas.view.out.OrderOutputWriter;

import java.util.EnumMap;

import static christmas.view.constants.ResponseMessage.*;

public class OrderController {
    private OrderController() {
    }

    public static Orders requestOrders() {
        OrderOutputWriter.printMessageResponse(REQUEST_MENU_ORDERS);
        return ExceptionHandler.retryOnBusinessException(OrderController::createMenuOrdersFromInput);
    }

    private static Orders createMenuOrdersFromInput() {
        String orderInput = InputReader.readInput();
        EnumMap<Menu, Integer> parsedOrders = Parser.parseMenuOrdersInputByDelimiter(orderInput);
        return Orders.create(parsedOrders);
    }

    public static void responseOrdersResult(Orders orders) {
        OrderResponse orderResponse = OrderResponse.from(orders);

        OrderOutputWriter.printNewLine();
        OrderOutputWriter.printMessageResponse(RESPONSE_MENU_ORDERS_RESULT);
        OrderOutputWriter.printMenuOrdersResponse(orderResponse);
    }

    public static void responseTotalOriginPriceResult(Orders orders) {
        final int totalOriginPrice = orders.calculateTotalOriginPrice();

        OrderOutputWriter.printNewLine();
        OrderOutputWriter.printMessageResponse(RESPONSE_TOTAL_ORIGIN_PRICE_RESULT);
        OrderOutputWriter.printTotalOriginPriceResponse(totalOriginPrice);
    }
}
