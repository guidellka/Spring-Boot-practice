package hello.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DashboardController {

    @GetMapping("/admin")
    public String goToAdminPage() {
        return "admin";
    }

    @GetMapping("/user")
    public String goToUserPage() {
        return "user";
    }
}
