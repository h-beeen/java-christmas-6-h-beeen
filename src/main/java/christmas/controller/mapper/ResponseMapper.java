package christmas.controller.mapper;

public sealed interface ResponseMapper<S, D> permits OrderResponseMapper {
    D toResponse(S source);
}
