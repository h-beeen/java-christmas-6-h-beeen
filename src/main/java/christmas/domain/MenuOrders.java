package christmas.domain;

import christmas.domain.constants.Menu;

import java.util.EnumMap;

public class MenuOrders {
    private final EnumMap<Menu, Integer> menuOrders;

    private MenuOrders(EnumMap<Menu, Integer> menuOrders) {
        this.menuOrders = menuOrders;
    }

    public static MenuOrders create(EnumMap<Menu, Integer> menuOrders) {
        return new MenuOrders(menuOrders);
    }
}
