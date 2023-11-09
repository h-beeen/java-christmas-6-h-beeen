package christmas.domain;

import christmas.domain.constants.Menu;

import java.util.EnumMap;

public class MenusOrder {
    private final EnumMap<Menu, Integer> menuOrders;

    private MenusOrder(EnumMap<Menu, Integer> menuOrders) {
        this.menuOrders = menuOrders;
    }

    public static MenusOrder create(EnumMap<Menu, Integer> menuOrders) {
        return new MenusOrder(menuOrders);
    }
}
