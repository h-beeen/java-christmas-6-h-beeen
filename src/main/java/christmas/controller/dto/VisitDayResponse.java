package christmas.controller.dto;

import christmas.domain.consumer.VisitDay;

import java.time.LocalDate;

public record VisitDayResponse(
        int month,
        int date
) {
    public static VisitDayResponse from(VisitDay date) {
        LocalDate visitDay = date.getVisitDay();
        
        return new VisitDayResponse(visitDay.getMonthValue(), visitDay.getDayOfMonth());
    }
}
