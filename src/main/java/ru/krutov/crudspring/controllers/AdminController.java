package ru.krutov.crudspring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.krutov.crudspring.dao.PersonDao;
import ru.krutov.crudspring.models.Person;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final PersonDao personDao;
    @Autowired
    public AdminController(PersonDao personDao) {
        this.personDao = personDao;
    }

    @GetMapping()
    public String adminPage(Model model, @ModelAttribute("person") Person person){
        model.addAttribute("people", personDao.index());
        return "admin/adminPage";
    }
    @PatchMapping("/add")
    public String adminAdd(@ModelAttribute("person") Person person){
        System.out.println(person.getId());

        return "redirect:/people";
    }
}
