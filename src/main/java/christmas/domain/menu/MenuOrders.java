package christmas.domain.menu;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.BooleanSupplier;
import java.util.function.Predicate;

import static christmas.domain.menu.MenuCategory.BEVERAGE;
import static christmas.exception.ErrorCode.ONLY_ORDER_BEVERAGES;

public class MenuOrders {
    private final EnumMap<Menu, Integer> menuOrders;

    private MenuOrders(EnumMap<Menu, Integer> menuOrders) {
        ONLY_ORDER_BEVERAGES.validate(hasOnlyBeverages(menuOrders));
        
        this.menuOrders = menuOrders;
    }

    public static MenuOrders create(EnumMap<Menu, Integer> menuOrders) {
        return new MenuOrders(menuOrders);
    }

    private BooleanSupplier hasOnlyBeverages(EnumMap<Menu, Integer> menuOrders) {
        return () -> menuOrders.entrySet()
                .stream()
                .allMatch(hasBeverage());
    }

    private Predicate<Map.Entry<Menu, Integer>> hasBeverage() {
        return entry -> entry.getKey().isSameCategory(BEVERAGE);
    }
}
