package christmas.dto;

import java.util.Map;

public record MenuOrdersResponse(
        Map<String, Integer> menuOrders
) {
}
