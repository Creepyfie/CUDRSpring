package ru.krutov.crudspring.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.NodeList;
import ru.krutov.crudspring.dao.BookDAO;
import ru.krutov.crudspring.dao.PersonDao;
import ru.krutov.crudspring.models.Book;
import ru.krutov.crudspring.models.Person;
import ru.krutov.crudspring.util.BookValidator;

@Controller
@RequestMapping("/books")
public class BooksController {
    private BookValidator bookValidator;

    private BookDAO bookDAO;
    private PersonDao personDao;

    @Autowired
    public BooksController(BookValidator bookValidator, BookDAO bookDAO, PersonDao personDao) {
        this.bookValidator = bookValidator;
        this.bookDAO = bookDAO;
        this.personDao = personDao;
    }
    @GetMapping()
    public String index(Model model){
        model.addAttribute("books",bookDAO.index());
        return "books/index";
    }
    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book){
        return "books/new";
    }
    @PostMapping()
    public  String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        bookValidator.validate(book,bindingResult);
        if(bindingResult.hasErrors())
            return "books/new";

        bookDAO.save(book);

        return "redirect:/books";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("personLink") Person person){
        model.addAttribute("people", personDao.index());
        model.addAttribute("book", bookDAO.show(id));
        model.addAttribute("person", personDao.show(bookDAO.show(id).getPerson_id()));
        return "books/show";
    }
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model){
        model.addAttribute("book",bookDAO.show(id));
        return "books/edit";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
                         @PathVariable("id") int id){
        bookValidator.validate(book,bindingResult);
        if(bindingResult.hasErrors())
            return "books/edit";
       bookDAO.update(id, book);
        return "redirect:/books";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id, Model model){
        bookDAO.delete(id);
        return "redirect:/books";
    }
    @PatchMapping("{id}/unlink")
    public String unkink(@PathVariable("id") int id, Model model){
        bookDAO.unlink(id);

        return "redirect:/books/{id}";

    }
    @PatchMapping("/{id}/link")
    public String link(@PathVariable("id") int id, @ModelAttribute("person") Person person, Model model){
        bookDAO.link(id, person.getPerson_id());
        return "redirect:/books/{id}";
    }
}
