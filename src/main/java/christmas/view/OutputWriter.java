package christmas.view;

public class OutputWriter {
    private OutputWriter() {
    }

    public static void println(Object object) {
        System.out.println(object);
    }

    public static void print(Object object) {
        System.out.print(object);
    }

    public static void printResponseMessage(ResponseMessage responseMessage) {
        System.out.println(responseMessage.getMessage());
    }
}
