package christmas.controller.mapper;

import christmas.domain.menu.MenuOrders;
import christmas.domain.menu.constants.Menu;
import christmas.dto.MenuOrdersResponse;

import java.util.Map;
import java.util.stream.Collectors;

public final class OrderMenuResponseMapper implements Mapper<MenuOrders, MenuOrdersResponse> {
    public static final OrderMenuResponseMapper orderMenuResponseMapper = new OrderMenuResponseMapper();

    private OrderMenuResponseMapper() {
    }

    public static OrderMenuResponseMapper getInstance() {
        return orderMenuResponseMapper;
    }

    @Override
    public MenuOrdersResponse toResponse(MenuOrders menuOrders) {
        Map<String, Integer> menuOrdersResponse = menuOrders
                .getMenuOrders()
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        this::getName,
                        Map.Entry::getValue));

        return new MenuOrdersResponse(menuOrdersResponse);
    }

    private String getName(Map.Entry<Menu, Integer> entry) {
        return entry.getKey().getName();
    }
}
