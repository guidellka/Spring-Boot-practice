package hello.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path = "/layout")
@Controller
public class FragmentsController {

    @GetMapping("/fragments")
    public String getHome() {
        return "fragments";
    }

    @GetMapping("/markup")
    public String markupPage() {
        return "markup";
    }

    @GetMapping("/params")
    public String paramsPage() {
        return "params.html";
    }

//    @GetMapping("/other")
//    public String otherPage(Model model) {
//        model.addAttribute("data", StudentUtils.buildStudents());
//        return "other.html";
//    }
}
