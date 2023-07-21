package egovframework.api.keyword.service;


import egovframework.api.keyword.service.impl.Keyword;

import java.util.List;


public interface KeywordService {

	List<Keyword> getKeywords(KeywordDto dto) throws Exception;

    List<Keyword> searchByKeyword(KeywordDto dto) throws Exception;

    List<Keyword> searchAll() throws Exception;

    void insertKeyword(KeywordDto dto) throws Exception;

    void updateKeyword(KeywordDto dto) throws Exception;

}
