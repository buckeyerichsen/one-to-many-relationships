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
    ClassRoomRepository classRoomRepository;
    @Autowired
    StudentRepository studentRepository;

//    Class classes = new Class();

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("students", studentRepository.findAll());
        model.addAttribute("classrooms", classRoomRepository.findAll());
        return "index";
    }

    @GetMapping("/class")
    public String classForm(Model model) {
        model.addAttribute("classes", new ClassRoom());
        return "class";
    }

    @GetMapping("/student")
    public String studentForm(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("class", classRoomRepository.findAll());
        return "student";
    }

    @PostMapping("/processclass")
    public String processClass(@Valid ClassRoom classes,
                               BindingResult result) {
        if (result.hasErrors()) {
            return "class";


        }
        classRoomRepository.save(classes);
        return "redirect:/";
    }
    @PostMapping("/student")
    public String processStudent(@Valid Student student,
                                 BindingResult result){
        if (result.hasErrors()){
            return "student";
        }
        studentRepository.save(student);
        return "redirect:/";
    }
    @RequestMapping("/studentdisplay")
    public String studentList(Model model){
        model.addAttribute("students", studentRepository.findAll());
        return "studentdisplay";
    }
    @RequestMapping("/classdisplay")
    public String classList(Model model){
        model.addAttribute("classes", classRoomRepository.findAll());
        return "classdisplay";
    }
    @RequestMapping("/detailstudent/{id}")
    public String showStudent(@PathVariable("id") long id, Model model)
    {model.addAttribute("students", studentRepository.findById(id).get());
        return "student";
    }
    @RequestMapping("/updatestudent/{id}")
    public String updateStudent(@PathVariable("id") long id,Model model){
        model.addAttribute("students", studentRepository.findById(id).get());
        return "student";
    }
    @RequestMapping("/deletestudent/{id}")
    public String delStudent(@PathVariable("id") long id){
        studentRepository.deleteById(id);
        return "redirect:/";
    }

    @RequestMapping("/detailclass/{id}")
    public String showClass(@PathVariable("id") long id, Model model)
    {model.addAttribute("classes", classRoomRepository.findById(id).get());
        return "class";
    }
    @RequestMapping("/updateclass/{id}")
    public String updateClass(@PathVariable("id") long id,Model model){
        model.addAttribute("classes", classRoomRepository.findById(id).get());
        return "class";
    }
    @RequestMapping("/deleteclass/{id}")
    public String delClass(@PathVariable("id") long id){
        classRoomRepository.deleteById(id);
        return "redirect:/";
    }
}