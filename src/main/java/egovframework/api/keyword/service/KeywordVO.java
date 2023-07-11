package egovframework.api.keyword.service;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.constraints.Pattern;

@Getter
@Setter
@ToString
public class KeywordVO {

    @Pattern(regexp = "^[a-zA-Z가-힣0-9\\. ]+$", message = "특수문자가 포함되어 있습니다.")
    private String hanWord;

    @Pattern(regexp = "^[a-zA-Z가-힣0-9\\. ]+$", message = "특수문자가 포함되어 있습니다.")
    private String engWord;

    @Pattern(regexp = "^[a-zA-Z가-힣0-9\\. ]+$", message = "특수문자가 포함되어 있습니다.")
    private String engAbbrWord;

    @Pattern(regexp = "^[a-zA-Z가-힣0-9\\. ]+$", message = "특수문자가 포함되어 있습니다.")
    private String description;

}
