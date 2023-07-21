package egovframework.com.web.response;


import egovframework.com.web.response.vo.CommonBodyVo;
import egovframework.com.web.response.vo.CommonHeaderVo;
import lombok.Builder;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@Builder
@XmlRootElement(name = "keywords")
@XmlType(propOrder = {"header", "body"})
public class CommonResponseDto implements Serializable {

    private static final long serialVersionUID = 4L;

    private CommonHeaderVo header;

    private CommonBodyVo body;

    public CommonResponseDto() {
    }

    public CommonResponseDto(CommonHeaderVo header, CommonBodyVo body) {
        this.header = header;
        this.body = body;
    }

    @XmlElement
    public CommonHeaderVo getHeader() {
        return header;
    }

    public void setHeader(CommonHeaderVo header) {
        this.header = header;
    }

    @XmlElement
    public CommonBodyVo getBody() {
        return body;
    }

    public void setBody(CommonBodyVo body) {
        this.body = body;
    }
}
