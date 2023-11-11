package christmas.domain.menu;

import christmas.domain.menu.constants.Menu;

import java.util.EnumMap;
import java.util.Map.Entry;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;

import static christmas.domain.menu.constants.MenuCategory.BEVERAGE;
import static christmas.exception.ErrorCode.EXCEED_ORDER_QUANTITY_LIMIT;
import static christmas.exception.ErrorCode.ONLY_ORDER_BEVERAGES;

public class Order {
    private static final int ORDER_QUANTITY_LIMIT = 20;
    private final EnumMap<Menu, Integer> menuOrders;

    //== Constructor ==//
    private Order(EnumMap<Menu, Integer> menuOrders) {
        ONLY_ORDER_BEVERAGES.validate(() -> isAllBeverages(menuOrders));
        EXCEED_ORDER_QUANTITY_LIMIT.validate(() -> isExceedMaximumQuantity(menuOrders));

        this.menuOrders = menuOrders;
    }

    //== Static Factory Method ==//
    public static Order create(EnumMap<Menu, Integer> menuOrders) {
        return new Order(menuOrders);
    }

    //== Utility Method ==//
    public int calculateTotalOriginPrice() {
        return menuOrders.entrySet()
                .stream()
                .mapToInt(calculateOriginPrice())
                .sum();
    }

    private ToIntFunction<Entry<Menu, Integer>> calculateOriginPrice() {
        return entry -> entry.getKey().calculatePrice(entry.getValue());
    }

    private boolean isExceedMaximumQuantity(EnumMap<Menu, Integer> menuOrders) {
        int totalOrderQuantity = menuOrders.values()
                .stream()
                .mapToInt(quantity -> quantity)
                .sum();
        return totalOrderQuantity > ORDER_QUANTITY_LIMIT;
    }


    //== Validation Method ==//
    private boolean isAllBeverages(EnumMap<Menu, Integer> menuOrders) {
        return menuOrders.entrySet()
                .stream()
                .allMatch(isBeverage());
    }

    private Predicate<Entry<Menu, Integer>> isBeverage() {
        return entry -> entry.getKey().isSameCategory(BEVERAGE);
    }

    //== Getter (Only permit to use Dto/ResponseMapper) ==//
    public EnumMap<Menu, Integer> getMenuOrders() {
        return menuOrders;
    }
}
