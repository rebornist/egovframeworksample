package egovframework.api.web;

public class ResponseDTO<T> {
    private final int status;

    private final String message;

    private final T data;

    public ResponseDTO(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

}
