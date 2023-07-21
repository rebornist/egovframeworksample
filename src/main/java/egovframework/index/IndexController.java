package egovframework.index;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping("/main.do")
    public String index() {
        return "keyword/index";
    }

}
