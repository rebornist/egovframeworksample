package egovframework.api.keyword.service.impl;

import egovframework.api.keyword.service.KeywordService;
import egovframework.api.keyword.service.KeywordVO;
import egovframework.com.ex.CustomApiException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service("keywordService")
public class KeywordServiceImpl implements KeywordService {

    private final String url = "https://dic.daum.net/search.do?q=";

    @Override
    public List<KeywordDTO> getKeywords(KeywordVO vo) {

        List<KeywordDTO> result = new ArrayList<>();

        List<String> keywords = Arrays.asList(vo.getHanWord().trim().split(" "));

        for (String keyword : keywords) {
            try {
                Document doc = Jsoup.connect(url + keyword).get();
                if (isKorean(keyword)) result.addAll(searchHanKeywords(doc, keyword));
                if (isEnglish(keyword)) result.addAll(searchEngKeywords(doc, keyword, keyword));
            } catch (IOException e) {
                throw new CustomApiException(e.getMessage());
            }
        }

        return result;
    }

    @Override
    public void insertKeyword(KeywordDTO dto) {}

    public List<KeywordDTO> searchHanKeywords(Document doc, String keyword) throws IOException {

        List<KeywordDTO> result = new ArrayList<>();

        // 사전 검색 결과
        Elements elems = doc.select("#mArticle > div.search_cont");

        // 영어단어 검색 후 데이터 적재
        elems.select("div:nth-child(4) > div.search_box > div:nth-child(1) > ul").forEach(element -> {
            // String id = element.select("span.num_search").text().trim().replace(".", "");
            element.select("span.txt_search").forEach(txtElem -> {
                String engWord = txtElem.text().trim();
                try {
                    Document engDoc = Jsoup.connect(url + engWord).get();

                    result.addAll(searchEngKeywords(engDoc, keyword, engWord));
                } catch (IOException e) {
                    throw new CustomApiException(e.getMessage());
                }

            });

        });

        return result;
    }

    public List<KeywordDTO> searchEngKeywords(Document doc, String parentKeyword, String keyword) throws IOException {

        List<KeywordDTO> result = new ArrayList<>();

        // 사전 검색 결과
        doc.select("#mArticle > div.search_cont > div:nth-child(3) > div:nth-child(2) > div > ul")
                .forEach(engElem -> {
                    List<String> engWords = new ArrayList<>();
                    engElem.select("span.txt_search").forEach(txtElem -> engWords.add(txtElem.text().trim()));
                    result.add(new KeywordDTO(parentKeyword, keyword, "", engElem.text().trim()));
                });

        return result;
    }

    private boolean isEnglish(String text) {
        return text.matches("[a-zA-Z]+");
    }

    private boolean isKorean(String text) {
        return text.matches("[가-힣]+");
    }

}
