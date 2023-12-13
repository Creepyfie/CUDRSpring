package ru.krutov.crudspring.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.krutov.crudspring.dao.BookDAO;
import ru.krutov.crudspring.dao.PersonDao;
import ru.krutov.crudspring.models.Person;
import ru.krutov.crudspring.util.PersonValidator;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonValidator personValidator;

    private final PersonDao personDao;
    private final BookDAO bookDAO;

    @Autowired
    public PeopleController(PersonValidator personValidator, PersonDao personDao, BookDAO bookDAO) {
        this.personValidator = personValidator;
        this.personDao = personDao;
        this.bookDAO = bookDAO;
    }

    @GetMapping()
    public String index(Model model){
        //Получим всех людей из дао и передадим на отображение в представление
        model.addAttribute("people",personDao.index());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        //Получим 1 человека из  дао и передадим на отображение в представление
        model.addAttribute("person",personDao.show(id));
        model.addAttribute("books",bookDAO.search(id));
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person){
        return "people/new";
    }

    @PostMapping()
    public  String create(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        personValidator.validate(person,bindingResult);
        if(bindingResult.hasErrors())
            return "people/new";

        personDao.save(person);

        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model){
        model.addAttribute("person",personDao.show(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult,
                         @PathVariable("id") int id){
        personValidator.validate(person,bindingResult);
        if(bindingResult.hasErrors())
            return "people/edit";
        personDao.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id, Model model){

        personDao.delete(id);
        return "redirect:/people";
    }
}
