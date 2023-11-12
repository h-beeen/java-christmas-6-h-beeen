package christmas.controller.mapper;

public sealed interface ResponseMapper<S, D>
        permits OrderResponseMapper, VisitDayMapper, BenefitResponseMapper {
    D toResponse(S source);
}
