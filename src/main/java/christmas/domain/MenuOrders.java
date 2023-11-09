package christmas.domain;

import christmas.domain.constants.Menu;

import java.util.EnumMap;
import java.util.function.BooleanSupplier;

import static christmas.exception.ExceptionCode.INVALID_ORDER;

public class MenuOrders {
    private final EnumMap<Menu, Integer> menuOrders;

    private MenuOrders(EnumMap<Menu, Integer> menuOrders) {
        INVALID_ORDER.validate(isValidSize(menuOrders));
        this.menuOrders = menuOrders;
    }

    public static MenuOrders create(EnumMap<Menu, Integer> menuOrders) {
        return new MenuOrders(menuOrders);
    }

    private static BooleanSupplier isValidSize(EnumMap<Menu, Integer> menuOrders) {
        return () -> menuOrders.size() == 0;
    }
}
