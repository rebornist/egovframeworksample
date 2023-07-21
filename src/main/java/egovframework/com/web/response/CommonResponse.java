package egovframework.com.web.response;

import egovframework.com.ex.CustomApiException;
import egovframework.com.web.response.util.ResponseTypeBuilder;
import egovframework.com.web.response.vo.CommonHeaderVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component
public class CommonResponse {
    private final Logger logger = LoggerFactory.getLogger(CommonResponse.class);

    private final ResponseTypeBuilder bodyBuilder = new ResponseTypeBuilder();

    protected void success(HttpServletResponse res, CommonResponseDto obj) {
        try {
            res.setContentType(MediaType.APPLICATION_JSON_VALUE);
            res.setCharacterEncoding("utf-8");
            res.setStatus(HttpStatus.OK.value());
            String responseBody = bodyBuilder.toJson(obj);
            res.getWriter().write(responseBody);
            logger.info("responseBody: {}", responseBody);

        } catch (Exception e) {
            throw new CustomApiException(e);
        }
    }

    protected void successByResponseType(HttpServletRequest req, HttpServletResponse res,
                                      String format, CommonResponseDto dto) {
        try {
            String responseBody = null;

            if (format == null) {
                if (req.getHeader("Accept") != null) {
                    if (req.getHeader("Accept").contains("xml")) {
                        format = "xml";
                    } else {
                        format = "json";
                    }
                } else {
                    format = "json";
                }
            }

            res.setContentType("application/" + format);
            res.setCharacterEncoding("utf-8");
            res.setStatus(HttpStatus.OK.value());

            if (format.equals("xml")) {
                responseBody = bodyBuilder.toXml(dto);
                res.getWriter().write(responseBody);
            } else {
                responseBody = bodyBuilder.toJson(dto);
                res.getWriter().write(responseBody);
            }

            logger.info("responseBody: {}", responseBody);
        } catch (Exception e) {
            throw new CustomApiException(e);
        }
    }

    protected void fail(HttpServletResponse res, String message, HttpStatus httpStatus) {
        try {
            CommonResponseDto dto = new CommonResponseDto(new CommonHeaderVo(httpStatus.value(), message), null);

            String responseBody = bodyBuilder.toJson(dto);

            logger.error("msg: {}, responseBody: {}", message, responseBody);

            res.setContentType("application/json;charset=utf-8");
            res.setStatus(httpStatus.value());
            res.getWriter().write(responseBody);

        } catch (Exception e) {
            throw new CustomApiException(e);
        }
    }
}
