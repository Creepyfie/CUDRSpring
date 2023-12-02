package ru.krutov.crudspring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.krutov.crudspring.dao.PersonDao;
import ru.krutov.crudspring.models.Person;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PersonDao personDao;

    @Autowired
    public PeopleController(PersonDao personDao) {
        this.personDao = personDao;
    }

    @GetMapping()
    public String index(Model model){
        //Получим всех людей из дао и передадим на отображение в представление
        model.addAttribute("people",personDao.index());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        //Получим 1 человека их  дао и передадим на отображение в представление
        model.addAttribute("person",personDao.show(id));
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person){
        return "people/new";
    }

    @PostMapping()
    public  String create(@ModelAttribute("person") Person person) {
        personDao.save(person);
        return "redirect:/people";
    }
}
