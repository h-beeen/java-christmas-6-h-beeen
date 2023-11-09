package christmas.exception;

public class BusinessException extends IllegalArgumentException {
    private BusinessException(
            ErrorMessage errorMessage,
            Exception exception
    ) {
        super(errorMessage.getMessage(), exception);
    }

    private BusinessException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
    }

    public static BusinessException of(
            ErrorMessage errorMessage,
            Exception exception
    ) {
        return new BusinessException(errorMessage, exception);
    }

    public static BusinessException from(ErrorMessage errorMessage) {
        return new BusinessException(errorMessage);
    }
}
