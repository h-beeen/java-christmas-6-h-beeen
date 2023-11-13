package christmas.fixture;

import christmas.domain.consumer.Orders;
import christmas.domain.consumer.constants.Menu;
import christmas.domain.utility.Parser;

import java.util.EnumMap;

public enum OrdersFixture {
    VALID__A("양송이수프-1,티본스테이크-2,초코케이크-3,제로콜라-4"),
    VALID__B("타파스-1,바비큐립-1,아이스크림-1,레드와인-1"),
    VALID__C("초코케이크-1,제로콜라-1"),

    INVALID__ONLY_CONTAIN_BEVERAGES("제로콜라-1,샴페인-2"),
    INVALID__CONTAIN_BLANK("티본 스테이크-1"),
    INVALID__DUPLICATED_MENU("시저샐러드-1,시저샐러드-2"),
    INVALID__NONEXISTENCE_MENU("해빈이의우아한볶음밥-1"),
    INVALID__ENDS_WITH_COMMA("타파스-1,"),
    INVALID__EXCEEDS_MAXIMUM_MENU_QUANTITY_CONSTRAINT("타파스-15,시저샐러드-6"),
    INVALID__LESS_MINIMUM_MENU_QUANTITY_CONSTRAINT("바비큐립-0"),
    INVALID__NEGATIVE_MENU_QUANTITY("크리스마스파스타--1"),
    INVALID__MENU_FORM("레드와인,1-양송이수프,2");

    private final String value;

    OrdersFixture(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public Orders toEntity() {
        EnumMap<Menu, Integer> menus = Parser.parseMenuOrdersInputByDelimiter(value);
        return Orders.create(menus);
    }
}
