package christmas.controller.mapper;

public sealed interface ResponseMapper<S, D>
        permits OrderResponseMapper, VisitDayMapper, GiftResponseMapper {
    D toResponse(S source);
}
