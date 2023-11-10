package christmas.view.constants;

public enum ResponseFormat {
    ORDER_MENUS_RESULT("%s %d개"),
    TOTAL_ORIGIN_PRICE_RESULT("%,d원");


    private final String format;


    ResponseFormat(String format) {
        this.format = format;
    }

    //== Utility Method ==//
    public String generateFormat(Object... objects) {
        return String.format(format, objects);
    }
}
