package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    ClassRepository classRepository;
    @Autowired
    StudentRepository studentRepository;

    Class class1 = new Class();

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("students", studentRepository.findAll());
        model.addAttribute("classes", classRepository.findAll());
        return "index";
    }

    @GetMapping("/class")
    public String classForm(Model model) {
        model.addAttribute("classes", new Class());
        return "class";
    }

    @GetMapping("/student")
    public String studentForm(Model model) {
        model.addAttribute("students", new Student());
        return "student";
    }

    @PostMapping("/processclass")
    public String processClass(@Valid Class class1,
                               BindingResult result) {
        if (result.hasErrors()) {
            return "class";


        }
        classRepository.save(class1);
        return "index";
    }
    @PostMapping("/student")
    public String processStudent(@Valid Student student,
                                 BindingResult result){
        if (result.hasErrors()){
            return "student";
        }
        studentRepository.save(student);
        return "index";
    }
    @RequestMapping("/studentlist")
    public String studentList(Model model){
        model.addAttribute("students", studentRepository.findAll());
        return "studentdisplay";
    }
    @RequestMapping("/detailstudent/{id}")
    public String showStudent(@PathVariable("id") long id, Model model)
    {model.addAttribute("students", studentRepository.findById(id).get());
        return "index";
    }
    @RequestMapping("/updatestudent/{id}")
    public String updateStudent(@PathVariable("id") long id,Model model){
        model.addAttribute("students", studentRepository.findById(id).get());
        return "index";
    }
    @RequestMapping("/deletestudent/{id}")
    public String delStudent(@PathVariable("id") long id){
        studentRepository.deleteById(id);
        return "redirect:/";
    }

    @RequestMapping("/detailclass/{id}")
    public String showClass(@PathVariable("id") long id, Model model)
    {model.addAttribute("classes", classRepository.findById(id).get());
        return "index";
    }
    @RequestMapping("/updateclass/{id}")
    public String updateClass(@PathVariable("id") long id,Model model){
        model.addAttribute("classes", classRepository.findById(id).get());
        return "index";
    }
    @RequestMapping("/deleteclass/{id}")
    public String delClass(@PathVariable("id") long id){
        classRepository.deleteById(id);
        return "redirect:/";
    }
}