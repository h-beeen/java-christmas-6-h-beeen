package christmas.controller;

import christmas.domain.MenuOrders;
import christmas.domain.constants.Menu;
import christmas.exception.ExceptionHandler;
import christmas.utility.Parser;
import christmas.view.InputReader;
import christmas.view.OutputWriter;

import java.util.EnumMap;

import static christmas.view.ResponseMessage.REQUEST_MENU_ORDER;

public class OrderController {
    private OrderController() {
    }

    public static MenuOrders requestOrder() {
        OutputWriter.printResponseMessage(REQUEST_MENU_ORDER);
        return ExceptionHandler.retryOnBusinessException(OrderController::createMenuOrdersFromInput);
    }

    private static MenuOrders createMenuOrdersFromInput() {
        String menuOrdersInput = InputReader.readInput();
        EnumMap<Menu, Integer> menuOrders = Parser.parseMenuOrdersInputByDelimiter(menuOrdersInput);
        return MenuOrders.create(menuOrders);
    }
}
