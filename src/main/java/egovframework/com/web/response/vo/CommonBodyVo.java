package egovframework.com.web.response.vo;

import egovframework.api.keyword.service.KeywordDto;
import lombok.*;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class CommonBodyVo {

    private KeywordDto keyword;
    private List<KeywordDto> keywords;
    private Map<String, String> validError;
    private Object data;

}
