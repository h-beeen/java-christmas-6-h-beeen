package christmas.domain.consumer.constants;

import christmas.exception.BusinessException;

import java.util.Arrays;
import java.util.Objects;

import static christmas.domain.consumer.constants.MenuCategory.*;
import static christmas.exception.ErrorCode.INVALID_ORDER;

public enum Menu {
    //== APPETIZER ==//
    MUSHROOM_SOUP(APPETIZER, "양송이수프", 6_000),
    TAPAS(APPETIZER, "타파스", 5_500),
    CAESAR_SALAD(APPETIZER, "시저샐러드", 8_000),

    //== MAIN_DISH ==//
    T_BONE_STEAK(MAIN_DISH, "티본스테이크", 55_000),
    BBQ_RIB(MAIN_DISH, "바비큐립", 54_000),
    SEAFOOD_PASTA(MAIN_DISH, "해산물파스타", 35_000),
    CHRISTMAS_PASTA(MAIN_DISH, "크리스마스파스타", 25_000),

    //== DESSERT ==//
    CHOCOLATE_CAKE(DESSERT, "초코케이크", 15_000),
    ICE_CREAM(DESSERT, "아이스크림", 5_000),

    //== BEVERAGE ==//
    ZERO_COLA(BEVERAGE, "제로콜라", 3_000),
    RED_WINE(BEVERAGE, "레드와인", 60_000),
    CHAMPAGNE(BEVERAGE, "샴페인", 25_000);

    private final MenuCategory category;
    private final String name;
    private final int price;

    Menu(
            MenuCategory category,
            String name,
            int price
    ) {
        this.category = category;
        this.name = name;
        this.price = price;
    }

    //== Utility Method ==//
    public static Menu findMenuByName(String name) {
        return Arrays.stream(values())
                .filter(menu -> menu.isSameName(name))
                .findFirst()
                .orElseThrow(() -> BusinessException.from(INVALID_ORDER));
    }

    public int calculatePrice(final int quantity) {
        return price * quantity;
    }

    //== Validation Method ==//
    public boolean isSameCategory(MenuCategory menuCategory) {
        return Objects.deepEquals(menuCategory, category);
    }

    private boolean isSameName(String comparableName) {
        return name.equals(comparableName);
    }

    //== Getter (Only permit to use Dto/ResponseMapper) ==//
    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
