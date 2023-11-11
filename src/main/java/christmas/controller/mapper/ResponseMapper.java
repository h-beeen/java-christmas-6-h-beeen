package christmas.controller.mapper;

public sealed interface ResponseMapper<S, D> permits OrderResponseMapper, VisitDayMapper {
    D toResponse(S source);
}
