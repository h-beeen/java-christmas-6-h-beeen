package christmas.controller;

import christmas.controller.dto.OrderResponse;
import christmas.controller.mapper.OrderResponseMapper;
import christmas.domain.order.Order;
import christmas.domain.order.constants.Menu;
import christmas.domain.utility.Parser;
import christmas.exception.ExceptionHandler;
import christmas.view.InputReader;
import christmas.view.OutputWriter;

import java.util.EnumMap;

import static christmas.view.constants.ResponseMessage.*;

public class OrderController {
    private static final OrderResponseMapper orderMenuResponseMapper = OrderResponseMapper.getInstance();

    private OrderController() {
    }

    public static Order requestOrder() {
        OutputWriter.printMessageResponse(REQUEST_MENU_ORDER);
        return ExceptionHandler.retryOnBusinessException(OrderController::createMenuOrdersFromInput);
    }

    private static Order createMenuOrdersFromInput() {
        String orderInput = InputReader.readInput();
        EnumMap<Menu, Integer> parsedOrder = Parser.parseMenuOrdersInputByDelimiter(orderInput);
        return Order.create(parsedOrder);
    }

    public static void responseOrderResult(Order order) {
        OrderResponse orderResponse = orderMenuResponseMapper.toResponse(order);

        OutputWriter.printNewLine();
        OutputWriter.printMessageResponse(RESPONSE_MENU_ORDER_RESULT);
        OutputWriter.printMenuOrdersResponse(orderResponse);
    }

    public static void responseTotalOriginPriceResult(Order order) {
        int totalOriginPrice = order.calculateTotalOriginPrice();

        OutputWriter.printNewLine();
        OutputWriter.printMessageResponse(RESPONSE_TOTAL_ORIGIN_PRICE_RESULT);
        OutputWriter.printTotalOriginPriceResponse(totalOriginPrice);
    }
}
