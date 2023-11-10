package christmas.controller;

import christmas.controller.mapper.OrderMenuResponseMapper;
import christmas.domain.menu.MenuOrders;
import christmas.domain.menu.constants.Menu;
import christmas.dto.MenuOrdersResponse;
import christmas.exception.ExceptionHandler;
import christmas.utility.Parser;
import christmas.view.InputReader;
import christmas.view.OutputWriter;

import java.util.EnumMap;

import static christmas.view.constants.ResponseMessage.*;

public class OrderController {
    private static final OrderMenuResponseMapper orderMenuResponseMapper = OrderMenuResponseMapper.getInstance();

    private OrderController() {
    }

    public static MenuOrders requestOrder() {
        OutputWriter.printResponseMessage(REQUEST_MENU_ORDER);
        return ExceptionHandler.retryOnBusinessException(OrderController::createMenuOrdersFromInput);
    }

    private static MenuOrders createMenuOrdersFromInput() {
        String menuOrdersInput = InputReader.readInput();
        EnumMap<Menu, Integer> parsedMenuOrders = Parser.parseMenuOrdersInputByDelimiter(menuOrdersInput);
        return MenuOrders.create(parsedMenuOrders);
    }

    public static void responseMenuOrderResult(MenuOrders menuOrders) {
        MenuOrdersResponse menuOrdersResponse = orderMenuResponseMapper.toResponse(menuOrders);

        OutputWriter.printNewLine();
        OutputWriter.printResponseMessage(RESPONSE_MENU_ORDER_RESULT);
        OutputWriter.printMenuOrdersResponse(menuOrdersResponse);


    }

    public static void responseTotalOriginPriceResult(MenuOrders menuOrders) {
        int totalOriginPrice = menuOrders.calculateTotalOriginPrice();

        OutputWriter.printNewLine();
        OutputWriter.printResponseMessage(RESPONSE_TOTAL_ORIGIN_PRICE_RESULT);
        OutputWriter.printTotalOriginPrice(totalOriginPrice);
    }
}
