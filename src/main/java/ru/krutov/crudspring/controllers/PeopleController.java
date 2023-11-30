package ru.krutov.crudspring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.krutov.crudspring.dao.PersonDao;

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
        //Получим всех людей из даво и передадим на отображение в представление
        model.addAttribute("people",personDao.index());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        //Получим 1 человека их  дао и передадим на отображение в представление
        model.addAttribute("person",personDao.show(id));
        return "people/show";
    }
}
