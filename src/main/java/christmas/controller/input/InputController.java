package christmas.controller.input;

import christmas.exception.BusinessException;
import christmas.view.InputReader;
import christmas.view.OutputWriter;

public abstract class InputController<E> {
    protected final InputReader inputReader;
    protected final OutputWriter outputWriter;

    protected InputController() {
        this.inputReader = InputReader.getInstance();
        this.outputWriter = OutputWriter.getInstance();
    }

    public E executeRequest() {
        try {
            return request();
        } catch (BusinessException exception) {
            outputWriter.println(exception.getMessage());
            return executeRequest();
        }
    }

    public abstract E request();
}
