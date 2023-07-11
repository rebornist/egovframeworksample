package egovframework.api.keyword.web;

import egovframework.api.keyword.service.KeywordService;
import egovframework.api.keyword.service.KeywordVO;
import egovframework.api.keyword.service.impl.KeywordDTO;
import egovframework.api.web.CmmResponse;
import egovframework.api.web.ResponseDTO;
import egovframework.com.ex.CustomApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/keyword")
public class KeyworkController extends CmmResponse {

    @Resource(name = "keywordService")
    private KeywordService keywordService;

    @PostMapping("/search.do")
    public void searchKeyword(@Valid @ModelAttribute KeywordVO vo, HttpServletResponse response) throws Exception {

        List<KeywordDTO> keywords = keywordService.getKeywords(vo);

        success(response, keywords);
    }

    @PostMapping("/add.do")
    public void insertKeywory(@Valid @ModelAttribute KeywordVO vo, HttpServletResponse response) throws Exception {



        success(response, vo);
    }

    @PutMapping("/update.do")
    public void updateKeyword(@Valid @ModelAttribute KeywordVO vo, HttpServletResponse response) throws Exception {

        try {
            System.out.println(vo.toString());
        } catch (Exception e) {
            throw new CustomApiException(e.getMessage());
        }

        success(response, vo);
    }
}
