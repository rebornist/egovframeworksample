package egovframework.api.keyword.service;

import egovframework.api.keyword.service.impl.KeywordDTO;

import java.util.List;

public interface KeywordService {
    List<KeywordDTO> getKeywords(KeywordVO vo) throws Exception;

    void insertKeyword(KeywordDTO dto) throws Exception;
}
