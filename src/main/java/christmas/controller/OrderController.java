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

import static christmas.view.ResponseMessage.REQUEST_MENU_ORDER;
import static christmas.view.ResponseMessage.RESPONSE_ORIGINAL_TOTAL_PRICE;

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

    public static void responseMenuOrders(MenuOrders menuOrders) {
        MenuOrdersResponse menuOrdersResponse = orderMenuResponseMapper.toResponse(menuOrders);

        OutputWriter.printNewLine();
        OutputWriter.printResponseMessage(RESPONSE_ORIGINAL_TOTAL_PRICE);
        OutputWriter.printMenuOrdersResponse(menuOrdersResponse);
    }
}
