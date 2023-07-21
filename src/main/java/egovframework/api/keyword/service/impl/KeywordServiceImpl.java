package egovframework.api.keyword.service.impl;

import egovframework.api.keyword.service.KeywordDto;
import egovframework.api.keyword.mapper.KeywordMapper;
import egovframework.api.keyword.service.KeywordService;
import egovframework.api.keyword.util.KeywordCrawlUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class KeywordServiceImpl extends KeywordCrawlUtil implements KeywordService {

    @Autowired
    private KeywordMapper keywordMapper;

    @Override
    public List<Keyword> getKeywords(KeywordDto dto) throws Exception {

        List<Keyword> result = new ArrayList<>();
        
        List<String> newKeywords = Arrays.asList(dto.getKeyword().trim().split(" "));

        for (String newKeyword : newKeywords) {
            if (isKorean(newKeyword)) result.addAll(searchHanKeywords(newKeyword));
            if (isEnglish(newKeyword)) result.addAll(searchEngKeywords(newKeyword, newKeyword));
        }

        return result;
    }

    @Override
    @Transactional
    public List<Keyword> searchByKeyword(KeywordDto dto) throws Exception {
        return keywordMapper.searchByKeyword(dto.toEntity());
    }

    @Override
    @Transactional
    public List<Keyword> searchAll() throws Exception {
        return keywordMapper.searchAll();
    }

    @Override
    @Transactional
    public void insertKeyword(KeywordDto dto) throws Exception {
        keywordMapper.insertKeyword(dto.toEntity());
    }

    @Override
    @Transactional
    public void updateKeyword(KeywordDto dto) throws Exception {
        keywordMapper.updateKeyword(dto.toEntity());
    }

}
