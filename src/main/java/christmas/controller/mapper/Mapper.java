package christmas.controller.mapper;

public sealed interface Mapper<S, D> permits OrderMenuResponseMapper {
    D toResponse(S source);
}
