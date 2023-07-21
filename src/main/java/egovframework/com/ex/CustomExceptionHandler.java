package egovframework.com.ex;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.web.response.vo.CommonBodyVo;
import egovframework.com.web.response.vo.CommonHeaderVo;
import egovframework.com.web.response.CommonResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class CustomExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EgovMessageSource messageSource;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> defaultException(Exception e) {

        String msg = e.getCause() != null ? e.getCause().getMessage() : "Unknown Exception";
        logger.error(msg);
        e.printStackTrace();

        return new ResponseEntity<>(
                new CommonResponseDto(
                        new CommonHeaderVo(
                                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                                messageSource.getMessage("fail.common.msg")
                        ),
                        null
                ), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CustomApiException.class)
    public ResponseEntity<?> apiException(CustomApiException e) {

        String msg = e.getCause() != null ? e.getCause().getMessage() : e.getMessage();
        logger.error(msg);
        e.printStackTrace();

        return new ResponseEntity<>(
                new CommonResponseDto(
                        new CommonHeaderVo(
                                HttpStatus.BAD_REQUEST.value(),
                                messageSource.getMessage("fail.common.msg")
                        ),

                        null
                ), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomValidationException.class)
    public ResponseEntity<?> validationException(CustomValidationException e) {
        logger.error(e.getCause().getMessage());
        e.printStackTrace();
        return new ResponseEntity<>(
                new CommonResponseDto(
                        new CommonHeaderVo(
                                HttpStatus.BAD_REQUEST.value(),
                                messageSource.getMessage("fail.request.msg")
                        ),
                        null
                ), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<?> nullException(NullPointerException e) {
        String msg = "No Content";
        logger.error(msg);
        return new ResponseEntity<>(
                new CommonResponseDto(
                        new CommonHeaderVo(
                                HttpStatus.NO_CONTENT.value(),
                                messageSource.getMessage("fail.common.msg")
                        ),
                        null
                ), HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<?> handleBindException(BindException e) {

        logger.error(e.getFieldError().toString());
        e.printStackTrace();

        Map<String, String> errors = new HashMap<>();
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }

        return new ResponseEntity<>(
                new CommonResponseDto(
                        new CommonHeaderVo(
                                HttpStatus.BAD_REQUEST.value(),
                                messageSource.getMessage("fail.request.msg")
                        ),
                        new CommonBodyVo().builder()
                                .validError(errors)
                                .build()
                ), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException e) {
        String msg = e.getCause() != null ? e.getCause().getMessage() : "Unknown Exception";
        logger.error(msg);
        e.printStackTrace();
        return new ResponseEntity<>(
                new CommonResponseDto(
                        new CommonHeaderVo(
                                HttpStatus.METHOD_NOT_ALLOWED.value(),
                                messageSource.getMessage("fail.request.msg")
                        ),
                        null
                ), HttpStatus.BAD_REQUEST);
    }


}
