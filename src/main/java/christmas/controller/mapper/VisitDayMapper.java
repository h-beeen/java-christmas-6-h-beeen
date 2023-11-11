package christmas.controller.mapper;

import christmas.controller.dto.VisitDayResponse;
import christmas.domain.order.VisitDay;

import java.time.LocalDate;

public final class VisitDayMapper implements ResponseMapper<VisitDay, VisitDayResponse> {
    private static final VisitDayMapper visitDayMapper = new VisitDayMapper();

    private VisitDayMapper() {
    }

    public static VisitDayMapper getInstance() {
        return visitDayMapper;
    }

    @Override
    public VisitDayResponse toResponse(VisitDay date) {
        LocalDate visitDay = date.getVisitDay();
        return new VisitDayResponse(visitDay.getMonthValue(), visitDay.getDayOfMonth());
    }
}
