package egovframework.com.ex;

public class CustomApiException extends RuntimeException {
    public CustomApiException(Exception e) {
        super(e);
    }
}
