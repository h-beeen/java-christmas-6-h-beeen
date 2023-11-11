package christmas.controller;

import christmas.controller.mapper.OrderResponseMapper;
import christmas.domain.menu.Order;
import christmas.domain.menu.constants.Menu;
import christmas.dto.OrderResponse;
import christmas.exception.ExceptionHandler;
import christmas.utility.Parser;
import christmas.view.InputReader;
import christmas.view.OutputWriter;

import java.util.EnumMap;

import static christmas.view.constants.ResponseMessage.*;

public class OrderController {
    private static final OrderResponseMapper orderMenuResponseMapper = OrderResponseMapper.getInstance();

    private OrderController() {
    }

    public static Order requestOrder() {
        OutputWriter.printResponseMessage(REQUEST_MENU_ORDER);
        return ExceptionHandler.retryOnBusinessException(OrderController::createMenuOrdersFromInput);
    }

    private static Order createMenuOrdersFromInput() {
        String menuOrdersInput = InputReader.readInput();
        EnumMap<Menu, Integer> parsedMenuOrders = Parser.parseMenuOrdersInputByDelimiter(menuOrdersInput);
        return Order.create(parsedMenuOrders);
    }

    public static void responseMenuOrderResult(Order menuOrders) {
        OrderResponse menuOrdersResponse = orderMenuResponseMapper.toResponse(menuOrders);

        OutputWriter.printNewLine();
        OutputWriter.printResponseMessage(RESPONSE_MENU_ORDER_RESULT);
        OutputWriter.printMenuOrdersResponse(menuOrdersResponse);


    }

    public static void responseTotalOriginPriceResult(Order menuOrders) {
        int totalOriginPrice = menuOrders.calculateTotalOriginPrice();

        OutputWriter.printNewLine();
        OutputWriter.printResponseMessage(RESPONSE_TOTAL_ORIGIN_PRICE_RESULT);
        OutputWriter.printTotalOriginPrice(totalOriginPrice);
    }
}
