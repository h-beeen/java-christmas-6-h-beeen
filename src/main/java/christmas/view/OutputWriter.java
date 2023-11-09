package christmas.view;

public class OutputWriter {
    private static final OutputWriter outputWriterInstance = new OutputWriter();

    private OutputWriter() {
    }

    public static OutputWriter getInstance() {
        return outputWriterInstance;
    }

    public void println(Object object) {
        System.out.println(object);
    }


    public void print(Object object) {
        System.out.print(object);
    }
}
