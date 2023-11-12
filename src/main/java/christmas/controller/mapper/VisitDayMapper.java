package christmas.controller.mapper;

import christmas.controller.dto.VisitDayResponse;
import christmas.domain.consumer.VisitDay;

import java.time.LocalDate;

public final class VisitDayMapper implements ResponseMapper<VisitDay, VisitDayResponse> {
    private static final VisitDayMapper VISIT_DAY_MAPPER = new VisitDayMapper();

    private VisitDayMapper() {
    }

    public static VisitDayMapper getInstance() {
        return VISIT_DAY_MAPPER;
    }

    @Override
    public VisitDayResponse toResponse(VisitDay date) {
        LocalDate visitDay = date.getVisitDay();
        return new VisitDayResponse(visitDay.getMonthValue(), visitDay.getDayOfMonth());
    }
}
