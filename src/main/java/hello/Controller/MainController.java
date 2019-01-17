package hello.Controller;


import hello.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;

import hello.Repository.UserRepository;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.List;
import java.util.ListIterator;


@Controller
@RequestMapping(path = "/demo")
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String goToHome() {
        return "index";
    }

    @GetMapping("/add")
    public @ResponseBody
    String addNewUser(@RequestParam String name, @RequestParam String email) {
        User n = new User();
        n.setName(name);
        n.setEmail(email);
        userRepository.save(n);
        return "Saved";
    }

    @GetMapping("/all")
    public @ResponseBody
    Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{name}")
    public String findUserByName(@PathVariable String name, ModelMap model) {
        List<User> users = userRepository.findByNameLike("%" + name + "%");
        model.addAttribute("test", users);
        return "index";
    }

    @GetMapping("/list")
    public String showList(ModelMap model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("test", users);
        return "ListName";
    }

    @GetMapping("/form")
    public String testForm() {
        return "form";
    }


    @PostMapping("/form")
    public RedirectView testFormSubmit(@RequestParam String username, @RequestParam String password) {
        User user = new User(username, password);
        userRepository.save(user);
        return new RedirectView("list");
    }

    @GetMapping("user/{id}/edit")
    public String goToUpdate(@PathVariable String id, Model model) {
        int integerId = Integer.parseInt(id);
        User user = userRepository.findById(integerId);
        model.addAttribute("edit",user);
        return "Edit";
    }

    @PostMapping("user/{id}/update")
    public String updateUser(@PathVariable int id,@RequestParam String name,@RequestParam String email) {
        User user = userRepository.findById(id);
        user.setName(name);
        user.setEmail(email);
        userRepository.save(user);
        return "redirect:/demo/list";
    }

    @PostMapping("/user/delete")
    public String deleteUser(@RequestParam int id) {
        userRepository.deleteById(id);
        return "redirect:/demo/list";
    }

}