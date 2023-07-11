package egovframework.api.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import egovframework.com.ex.CustomApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;

@Component
public class CmmResponse {
    private final Logger logger = LoggerFactory.getLogger(CmmResponse.class);

    public void success(HttpServletResponse res, Object dto) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            ResponseDTO<?> responseDto = new ResponseDTO<>(HttpStatus.OK.value(), "success", dto);

            String responseBody = mapper.writeValueAsString(responseDto);
            logger.info("responseBody: {}", responseBody);

            res.setContentType("application/json;charset=utf-8");
            res.setStatus(HttpStatus.OK.value());
            res.getWriter().write(responseBody);

        } catch (Exception e) {
            throw new CustomApiException(e.getMessage());
        }
    }

    public void fail(HttpServletResponse res, String message, HttpStatus httpStatus) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            ResponseDTO<?> responseDto = new ResponseDTO<>(httpStatus.value(), message, null);

            String responseBody = mapper.writeValueAsString(responseDto);
            logger.error("msg: {}, responseBody: {}", message, responseBody);

            res.setContentType("application/json;charset=utf-8");
            res.setStatus(httpStatus.value());
            res.getWriter().write(responseBody);
        } catch (Exception e) {
            throw new CustomApiException(e.getMessage());
        }
    }
}
