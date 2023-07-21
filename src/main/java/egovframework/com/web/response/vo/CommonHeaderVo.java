package egovframework.com.web.response.vo;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class CommonHeaderVo {
    private int status;
    private String message;
}
