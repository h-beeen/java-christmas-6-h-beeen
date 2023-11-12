package christmas.controller.dto;

public record BenefitResponse(
        DiscountResponse discountResponse,
        GiftResponse giftResponse,
        int expectedPayment
) {
}
