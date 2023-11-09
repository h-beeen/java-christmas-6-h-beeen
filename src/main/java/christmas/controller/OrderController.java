package christmas.controller;

import christmas.domain.MenuOrders;
import christmas.domain.constants.Menu;
import christmas.exception.ExceptionHandler;
import christmas.utility.Parser;
import christmas.view.InputReader;
import christmas.view.OutputWriter;

import java.util.EnumMap;

import static christmas.view.ResponseMessage.REQUEST_ORDER_MENU;

public class OrderController {
    private OrderController() {
    }

    public static MenuOrders requestOrder() {
        OutputWriter.printResponseMessage(REQUEST_ORDER_MENU);
        return ExceptionHandler.retryOnBusinessException(OrderController::createMenuOrdersFromInput);
    }

    private static MenuOrders createMenuOrdersFromInput() {
        String menuOrdersInput = InputReader.readInput();
        EnumMap<Menu, Integer> menuOrders = Parser.parseMenuOrdersInput(menuOrdersInput);
        return MenuOrders.create(menuOrders);
    }
}