package hello.Controller;

import hello.Entity.Student;
import hello.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RequestMapping(path = "/student")
@Controller
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @GetMapping("/")
    public String goToHome(Model model) {
        Student newStudent = new Student();
        model.addAttribute("student", newStudent);
        return "studentList";
    }


    @GetMapping(value="/thanks")
    public String thankYou(@ModelAttribute Student newStudent, Model model){
        List<Student> students = studentRepository.findAll();
        model.addAttribute("student",students);

        return "thanks";
    }

    @PostMapping(value = "/")
    public String addAStudent(@ModelAttribute @Valid Student newStudent, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            System.out.println("BINDING RESULT ERROR");
            return "studentList";
        } else {
            model.addAttribute("student", newStudent);

            if (newStudent.getName() != null) {
                try {
                    //    check for comments and if not present set to 'none'
                    String comments = checkNullString(newStudent.getComments());
                    if (comments != "None") {
                        System.out.println("nothing changes");
                    } else {
                        newStudent.setComments(comments);
                    }
                } catch (Exception e) {

                    System.out.println(e);

                }
                studentRepository.save(newStudent);
                System.out.println("new student added: " + newStudent);
            }

            return "redirect:thanks";
        }
    }

    public String checkNullString(String str){
        String endString = null;
        if(str == null || str.isEmpty()){
            System.out.println("yes it is empty");
            str = null;
            Optional<String> opt = Optional.ofNullable(str);
            endString = opt.orElse("None");
            System.out.println("endString : " + endString);
        }
        else{
            ; //do nothing
        }


        return endString;

    }

    @GetMapping("/ajaxlist")
    public String goToStudentAjaxList(ModelMap model){
        List<Student> students = studentRepository.findAll();
        model.addAttribute("students",students);
        return "StudentAjax";
    }

    @DeleteMapping("/ajaxlist")
    public ResponseEntity DeleteStudent(@RequestBody String id){
        Student student = studentRepository.findById(Long.parseLong(id));
        studentRepository.delete(student);
        return ResponseEntity.ok("Deleted");
    }

    @PutMapping("/ajaxlist")
    public ResponseEntity DeleteStudent(@RequestBody Student student){
        System.out.println(student);
        return ResponseEntity.ok("Edited");

    }




}
