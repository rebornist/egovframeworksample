package egovframework.com.web.response.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import egovframework.com.ex.CustomApiException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

public class ResponseTypeBuilder {

    public ResponseTypeBuilder() {
    }

    public String toXml(Object obj) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = jaxbContext.createMarshaller();

            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            StringWriter sw = new StringWriter();
            marshaller.marshal(obj, sw);

            return sw.toString();
        } catch (JAXBException e) {
            throw new CustomApiException(e);
        }
    }

    public String toJson(Object obj) {
        try {
            return new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL).writeValueAsString(obj);
        } catch (Exception e) {
            throw new CustomApiException(e);
        }
    }

}
