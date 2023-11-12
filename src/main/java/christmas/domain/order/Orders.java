package christmas.domain.order;

import christmas.domain.order.constants.Menu;
import christmas.domain.order.constants.MenuCategory;

import java.util.EnumMap;
import java.util.Map.Entry;
import java.util.function.Predicate;

import static christmas.domain.order.constants.MenuCategory.BEVERAGE;
import static christmas.exception.ErrorCode.EXCEED_ORDER_QUANTITY_LIMIT;
import static christmas.exception.ErrorCode.ORDERS_ONLY_CONTAIN_BEVERAGES;

public class Orders {
    private static final int ORDERS_MAXIMUM_RANGE = 20;
    private final EnumMap<Menu, Integer> menus;

    //== Constructor ==//
    private Orders(EnumMap<Menu, Integer> menus) {
        ORDERS_ONLY_CONTAIN_BEVERAGES.validate(() -> hasOnlyBeverages(menus));
        EXCEED_ORDER_QUANTITY_LIMIT.validate(() -> isExceedMaximumQuantity(menus));

        this.menus = menus;
    }

    //== Static Factory Method ==//
    public static Orders create(EnumMap<Menu, Integer> menus) {
        return new Orders(menus);
    }

    //== Utility Method ==//
    public int calculateTotalOriginPrice() {
        return menus.entrySet()
                .stream()
                .mapToInt(entry -> entry.getKey().calculatePrice(entry.getValue()))
                .sum();
    }

    public int countOrdersByMenuType(MenuCategory menuCategory) {
        return menus.keySet()
                .stream()
                .filter(integer -> integer.isSameCategory(menuCategory))
                .mapToInt(menus::get)
                .sum();
    }


    //== Validation Method ==//
    private boolean hasOnlyBeverages(EnumMap<Menu, Integer> menus) {
        return menus.entrySet()
                .stream()
                .allMatch(isBeverage());
    }

    private boolean isExceedMaximumQuantity(EnumMap<Menu, Integer> menus) {
        int totalOrdersQuantity = menus.values()
                .stream()
                .mapToInt(quantity -> quantity)
                .sum();

        return totalOrdersQuantity > ORDERS_MAXIMUM_RANGE;
    }

    private Predicate<Entry<Menu, Integer>> isBeverage() {
        return entry -> entry.getKey().isSameCategory(BEVERAGE);
    }

    //== Getter (Only permit to use Dto/ResponseMapper) ==//
    public EnumMap<Menu, Integer> getMenus() {
        return menus;
    }
}
