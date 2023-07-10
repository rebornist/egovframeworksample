package egovframework.api.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import egovframework.com.ex.CustomApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;

public class CmmResponseHandler {

    public static final Logger logger = LoggerFactory.getLogger(CmmResponseHandler.class);

    public static void success(HttpServletResponse response, Object dto, HttpStatus httpStatus) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            ResponseDTO<?> responseDto = new ResponseDTO<>(httpStatus.value(), "success", dto);

            String responseBody = mapper.writeValueAsString(responseDto);

            response.setContentType("application/json;charset=utf-8");
            response.setStatus(httpStatus.value());
            response.getWriter().println(responseBody);
            logger.info("responseBody: {}", responseBody);
        } catch (Exception e) {
            throw new CustomApiException(e.getMessage());
        }
    }

    public static void fail(HttpServletResponse response, String message, HttpStatus httpStatus) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            ResponseDTO<?> responseDto = new ResponseDTO<>(httpStatus.value(), message, null);

            String responseBody = mapper.writeValueAsString(responseDto);

            response.setContentType("application/json;charset=utf-8");
            response.setStatus(httpStatus.value());
            response.getWriter().println(responseBody);
            logger.error("msg: {}, responseBody: {}", message, responseBody);
        } catch (Exception e) {
            throw new CustomApiException(e.getMessage());
        }
    }
}
