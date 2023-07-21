package egovframework.api.keyword.web;

import egovframework.api.keyword.service.KeywordDto;
import egovframework.api.keyword.service.KeywordService;
import egovframework.com.web.response.CommonResponse;

import egovframework.com.web.response.vo.CommonBodyVo;
import egovframework.com.web.response.vo.CommonHeaderVo;
import egovframework.com.web.response.CommonResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/keyword")
public class KeyworkController extends CommonResponse {

    @Autowired
    private KeywordService keywordService;

    @GetMapping(value = "/searchAll.do",
                produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public void searchAll(HttpServletRequest request, HttpServletResponse response,
                                 @RequestParam(required = false) String format) throws Exception {

        List<KeywordDto> keywordEntities = new ArrayList<>();
        keywordService.searchAll().forEach(keyword -> keywordEntities.add(keyword.toDto()));

        if (keywordEntities == null) fail(response, "Not Found", HttpStatus.NOT_FOUND);

        successByResponseType(request, response, format, new CommonResponseDto()
                                                                .builder()
                                                                .header(new CommonHeaderVo(
                                                                            HttpStatus.OK.value(),
                                                                            "Success"
                                                                ))
                                                                .body(new CommonBodyVo()
                                                                            .builder()
                                                                            .keywords(keywordEntities)
                                                                            .build()
                                                                )
                                                                .build());
    }

    @PostMapping("/search.do")
    public void searchKeyword(HttpServletResponse response,
                                @Valid @ModelAttribute KeywordDto dto) throws Exception {
        List<KeywordDto> keywordEntities = new ArrayList<>();
        keywordService.getKeywords(dto).forEach(keyword -> keywordEntities.add(keyword.toDto()));

        success(response, new CommonResponseDto()
                                .builder()
                                .header(new CommonHeaderVo(
                                            HttpStatus.OK.value(),
                                            "Success"))
                                .body(new CommonBodyVo()
                                            .builder()
                                            .keywords(keywordEntities)
                                            .build())
                                .build());
    }

    @PostMapping("/add.do")
    public void addKeyword(@Valid @ModelAttribute KeywordDto dto, HttpServletResponse response) throws Exception {
        keywordService.insertKeyword(dto);
        success(response, new CommonResponseDto()
                                .builder()
                                .header(new CommonHeaderVo(
                                            HttpStatus.OK.value(),
                                            "Success"))
                                .body(new CommonBodyVo()
                                            .builder()
                                            .keyword(dto)
                                            .build())
                                .build());
    }

    @PutMapping("/update.do")
    public void updateKeyword(@Valid @ModelAttribute KeywordDto dto, HttpServletResponse response) throws Exception {
        keywordService.updateKeyword(dto);
        success(response, new CommonResponseDto()
                                .builder()
                                .header(new CommonHeaderVo(
                                        HttpStatus.OK.value(),
                                        "Success"))
                                .body(new CommonBodyVo()
                                        .builder()
                                        .keyword(dto)
                                        .build())
                                .build());
    }
}
