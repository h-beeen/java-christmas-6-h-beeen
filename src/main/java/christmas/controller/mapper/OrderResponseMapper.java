package christmas.controller.mapper;

import christmas.controller.dto.OrderResponse;
import christmas.domain.consumer.Orders;

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
    public OrderResponse from(Orders order) {
        Map<String, Integer> orderResponse = order
                .getMenus()
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey().getName(),
                        Entry::getValue));

        return new OrderResponse(orderResponse);
    }
}
