package egovframework.api.keyword.service.impl;

import egovframework.api.keyword.service.KeywordService;
import egovframework.api.keyword.service.KeywordVO;
import org.springframework.stereotype.Service;

@Service("keywordService")
public class KeywordServiceImpl implements KeywordService {
    @Override
    public String[] getKeywords(KeywordVO vo) throws Exception {
        return vo.getKeyword().split(" ");
    }

}
