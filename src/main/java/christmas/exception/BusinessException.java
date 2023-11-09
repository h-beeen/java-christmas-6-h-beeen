package christmas.exception;

public class BusinessException extends IllegalArgumentException {
    private BusinessException(
            ExceptionCode errorMessage,
            Exception exception
    ) {
        super(errorMessage.getMessage(), exception);
    }

    private BusinessException(ExceptionCode errorMessage) {
        super(errorMessage.getMessage());
    }

    public static BusinessException of(
            ExceptionCode errorMessage,
            Exception exception
    ) {
        return new BusinessException(errorMessage, exception);
    }

    public static BusinessException from(ExceptionCode errorMessage) {
        return new BusinessException(errorMessage);
    }

    public static BusinessException dynamicInvokeBy(ExceptionCode errorMessage) {
        return new BusinessException(errorMessage);
    }
}
