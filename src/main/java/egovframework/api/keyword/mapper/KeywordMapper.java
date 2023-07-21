package egovframework.api.keyword.mapper;

import egovframework.api.keyword.service.KeywordDto;
import egovframework.api.keyword.service.impl.Keyword;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;

@Mapper("keywordMapper")
public interface KeywordMapper {

    List<Keyword> searchAll() throws Exception;

    List<Keyword> searchByKeyword(Keyword dto) throws Exception;

    void insertKeyword(Keyword dto) throws Exception;

    void updateKeyword(Keyword dto) throws Exception;

}
