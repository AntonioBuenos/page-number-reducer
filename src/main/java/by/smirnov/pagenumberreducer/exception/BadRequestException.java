package by.smirnov.pagenumberreducer.exception;

public class BadRequestException extends RuntimeException{

    public static final String ERROR_NEGATIVE = "Negative digits are not allowed";
    public static final String ERROR_NULL = "Negative digits are not allowed";
    public static final String ERROR_EMPTY = "Input is empty";
    public static final String ERROR_BAD_REQUEST =
            "Input does not correspond the format needed: non-digit symbols and non-Integer numbers are not allowed. " +
            "Numbers shall be separated by commas (',')";

    public BadRequestException (String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "BadRequestException{}" + super.toString();
    }
}
