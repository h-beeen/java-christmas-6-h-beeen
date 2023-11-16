package christmas.controller.dto;

import christmas.domain.consumer.Orders;

import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public record OrderResponse(
        Map<String, Integer> orderMenus
) {
    public static OrderResponse from(Orders order) {
        Map<String, Integer> orderResponse = order
                .getMenus()
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey().getName(),
                        Entry::getValue)
                );

        return new OrderResponse(orderResponse);
    }
}
