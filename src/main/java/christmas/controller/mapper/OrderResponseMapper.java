package christmas.controller.mapper;

import christmas.controller.dto.OrderResponse;
import christmas.domain.order.Orders;
import christmas.domain.order.constants.Menu;

import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public final class OrderResponseMapper implements ResponseMapper<Orders, OrderResponse> {
    public static final OrderResponseMapper orderMenuResponseMapper = new OrderResponseMapper();

    private OrderResponseMapper() {
    }

    public static OrderResponseMapper getInstance() {
        return orderMenuResponseMapper;
    }

    @Override
    public OrderResponse toResponse(Orders order) {
        Map<String, Integer> orderResponse = order
                .getMenus()
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        this::getName,
                        Entry::getValue));

        return new OrderResponse(orderResponse);
    }

    private String getName(Entry<Menu, Integer> entry) {
        return entry.getKey().getName();
    }
}