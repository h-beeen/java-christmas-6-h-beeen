package christmas.domain.order;

import christmas.domain.order.constants.Menu;

import java.util.EnumMap;
import java.util.Map.Entry;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;

import static christmas.domain.order.constants.MenuCategory.BEVERAGE;
import static christmas.exception.ErrorCode.EXCEED_ORDER_QUANTITY_LIMIT;
import static christmas.exception.ErrorCode.ONLY_ORDER_BEVERAGES;

public class Order {
    private static final int ORDER_QUANTITY_LIMIT = 20;
    private final EnumMap<Menu, Integer> menus;

    //== Constructor ==//
    private Order(EnumMap<Menu, Integer> menus) {
        ONLY_ORDER_BEVERAGES.validate(() -> isAllBeverages(menus));
        EXCEED_ORDER_QUANTITY_LIMIT.validate(() -> isExceedMaximumQuantity(menus));

        this.menus = menus;
    }

    //== Static Factory Method ==//
    public static Order create(EnumMap<Menu, Integer> menus) {
        return new Order(menus);
    }

    //== Utility Method ==//
    public int calculateTotalOriginPrice() {
        return menus.entrySet()
                .stream()
                .mapToInt(calculateOriginPrice())
                .sum();
    }

    private ToIntFunction<Entry<Menu, Integer>> calculateOriginPrice() {
        return entry -> entry.getKey().calculatePrice(entry.getValue());
    }

    private boolean isExceedMaximumQuantity(EnumMap<Menu, Integer> menus) {
        int totalOrderQuantity = menus.values()
                .stream()
                .mapToInt(quantity -> quantity)
                .sum();
        return totalOrderQuantity > ORDER_QUANTITY_LIMIT;
    }


    //== Validation Method ==//
    private boolean isAllBeverages(EnumMap<Menu, Integer> menus) {
        return menus.entrySet()
                .stream()
                .allMatch(isBeverage());
    }

    private Predicate<Entry<Menu, Integer>> isBeverage() {
        return entry -> entry.getKey().isSameCategory(BEVERAGE);
    }

    //== Getter (Only permit to use Dto/ResponseMapper) ==//
    public EnumMap<Menu, Integer> getMenus() {
        return menus;
    }
}
