package christmas.controller.mapper;

import christmas.domain.menu.Order;
import christmas.domain.menu.constants.Menu;
import christmas.dto.OrderResponse;

import java.util.Map;
import java.util.stream.Collectors;

public final class OrderResponseMapper implements ResponseMapper<Order, OrderResponse> {
    public static final OrderResponseMapper orderMenuResponseMapper = new OrderResponseMapper();

    private OrderResponseMapper() {
    }

    public static OrderResponseMapper getInstance() {
        return orderMenuResponseMapper;
    }

    @Override
    public OrderResponse toResponse(Order menuOrders) {
        Map<String, Integer> menuOrdersResponse = menuOrders
                .getMenuOrders()
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        this::getName,
                        Map.Entry::getValue));

        return new OrderResponse(menuOrdersResponse);
    }

    private String getName(Map.Entry<Menu, Integer> entry) {
        return entry.getKey().getName();
    }
}
