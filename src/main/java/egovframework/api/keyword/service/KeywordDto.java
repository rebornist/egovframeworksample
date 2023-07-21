package egovframework.api.keyword.service;

import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;

import egovframework.api.keyword.service.impl.Keyword;
import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KeywordDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @Pattern(regexp = "^[a-zA-Z가-힣0-9\\, ]+$", message = "특수문자가 포함되어 있습니다.")
    private String keyword;

    @Pattern(regexp = "^[a-zA-Z가-힣0-9\\, ]+$", message = "특수문자가 포함되어 있습니다.")
    private String engWord;

    @Pattern(regexp = "^[a-zA-Z가-힣0-9\\, ]+$", message = "특수문자가 포함되어 있습니다.")
    private String engAbbrWord;

    @Pattern(regexp = "^[a-zA-Z가-힣0-9\\, ]+$", message = "특수문자가 포함되어 있습니다.")
    private String description;

    public Keyword toEntity() {
        return new Keyword(id, keyword, engWord, engAbbrWord, description, Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now()));
    }

}
