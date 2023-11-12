package christmas.controller.mapper;

public sealed interface ResponseMapper<S, D>
        permits OrderResponseMapper, VisitDayMapper, DiscountResultMapper {
    D toResponse(S source);
}
