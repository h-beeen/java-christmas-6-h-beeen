package christmas.view;

import christmas.dto.MenuOrdersResponse;

public class OutputWriter {
    private OutputWriter() {
    }

    public static void println(Object object) {
        System.out.println(object);
    }

    public static void printNewLine() {
        System.out.println();
    }

    public static void print(Object object) {
        System.out.print(object);
    }

    public static void printResponseMessage(ResponseMessage responseMessage) {
        System.out.println(responseMessage.getMessage());
    }

    public static void printMenuOrdersResponse(MenuOrdersResponse menuOrdersResponse) {
        menuOrdersResponse.menuOrders()
                .forEach((key, value) -> System.out.printf("%s : %d개%n", key, value));
    }
}
