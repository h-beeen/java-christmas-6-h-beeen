package christmas.controller.mapper;

public sealed interface ResponseMapper<S, D> permits
        OrderResponseMapper, VisitDayMapper, DiscountResponseMapper, GiftResponseMapper {
    D toResponse(S source);
}
