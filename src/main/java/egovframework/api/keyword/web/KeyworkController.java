package egovframework.api.keyword.web;

import egovframework.api.keyword.service.KeywordService;
import egovframework.api.keyword.service.KeywordVO;
import egovframework.api.web.CmmResponseHandler;
import egovframework.api.web.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/keyword")
public class KeyworkController extends CmmResponseHandler {

    @Resource(name = "keywordService")
    private KeywordService keywordService;

    @PostMapping("/search.do")
    public void searchKeyword(@Valid @ModelAttribute KeywordVO vo, HttpServletResponse response, BindingResult bindingResult) {

        // 유효성 검사
        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();

            for (FieldError error : bindingResult.getFieldErrors()) {
                System.out.println(error.getField() + ": " + error.getDefaultMessage());
                errorMap.put(error.getField(), error.getDefaultMessage());
            }

            fail(response, errorMap.toString(), HttpStatus.BAD_REQUEST);
        }

        try {
            System.out.println(vo.toString());
        } catch (Exception e) {
            fail(response, e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        success(response, new ResponseDTO<>(HttpStatus.OK.value(), "success", vo), HttpStatus.OK);
    }

    @PutMapping("/update.do")
    public void updateKeyword(@Valid @ModelAttribute KeywordVO vo, HttpServletResponse response, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getFieldError().getDefaultMessage());
            fail(response, bindingResult.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
            return;
        }

        try {
            System.out.println(vo.toString());
        } catch (Exception e) {
            fail(response, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        success(response, new ResponseDTO<>(HttpStatus.OK.value(), "success", vo), HttpStatus.OK);
    }
}
