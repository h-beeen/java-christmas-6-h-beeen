package christmas.view;

import camp.nextstep.edu.missionutils.Console;

public class InputReader {
    private static final InputReader inputReader = new InputReader();

    private InputReader() {
    }

    public String readLine() {
        return Console.readLine();
    }

    public static InputReader getInstance() {
        return inputReader;
    }

}
