package egovframework.api.keyword.service;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.json.JSONObject;

import javax.validation.constraints.Pattern;

public class KeywordVO {

    @Pattern(regexp = "^[a-zA-Z가-힣 ]+$", message = "특수문자가 포함되어 있습니다.")
    private String keyword;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public String toJsonString() {
        JSONObject obj = new JSONObject();
        obj.put("keyword", keyword);
        return obj.toString();
    }
}
