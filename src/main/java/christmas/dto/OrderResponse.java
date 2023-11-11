package christmas.dto;

import java.util.Map;

public record OrderResponse(
        Map<String, Integer> menuOrders
) {
}
