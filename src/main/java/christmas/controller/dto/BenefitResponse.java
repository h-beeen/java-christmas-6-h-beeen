package christmas.controller.dto;

import java.util.Map;

public record BenefitResponse(
        Map<String, Integer> benefitResponse
) {
}
