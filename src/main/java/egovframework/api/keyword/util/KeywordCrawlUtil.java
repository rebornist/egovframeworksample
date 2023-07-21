package egovframework.api.keyword.util;

import egovframework.api.keyword.service.KeywordDto;
import egovframework.api.keyword.service.impl.Keyword;
import egovframework.com.ex.CustomApiException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class KeywordCrawlUtil {

    private final String url = "https://dic.daum.net/search.do?q=";

    protected List<Keyword> searchHanKeywords(String keyword) throws CustomApiException, IOException {

        List<Keyword> result = new ArrayList<>();

        Document doc = Jsoup.connect(url + keyword).get();

        // 사전 검색 결과
        Elements elems = doc.select("#mArticle > div.search_cont");

        // 영어단어 검색 후 데이터 적재
        elems.select("div:nth-child(4) > div.search_box > div:nth-child(1) > ul").forEach(element -> {
            element.select("span.txt_search").forEach(txtElem -> {
                String engWord = txtElem.text().trim();
                try {
                    result.addAll(searchEngKeywords(keyword, engWord));
                } catch (IOException e) {
                    throw new CustomApiException(e);
                }
            });

        });
        return result;
    }

    protected List<Keyword> searchEngKeywords(String parentKeyword, String keyword) throws IOException {

        List<Keyword> result = new ArrayList<>();

        Document doc = Jsoup.connect(url + keyword).get();

        // 사전 검색 결과
        doc.select("#mArticle > div.search_cont > div:nth-child(3) > div:nth-child(2) > div > ul")
                .forEach(engElem -> {
                    List<String> engWords = new ArrayList<>();
                    engElem.select("span.txt_search").forEach(txtElem -> engWords.add(txtElem.text().trim()));
                    result.add(new KeywordDto().builder()
                            .keyword(parentKeyword)
                            .engWord(keyword)
                            .description(engWords.stream().collect(Collectors.joining(", ")))
                            .build().toEntity());
                });

        return result;
    }

    protected boolean isEnglish(String text) {
        return text.matches("[a-zA-Z]+");
    }

    protected boolean isKorean(String text) {
        return text.matches("[가-힣]+");
    }

}
