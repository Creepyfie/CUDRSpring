package ru.krutov.crudspring.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.krutov.crudspring.dao.BookDAO;
import ru.krutov.crudspring.models.Book;
@Component
public class BookValidator implements Validator {

    private BookDAO bookDAO;
    @Autowired
    public BookValidator (BookDAO bookDAO){
        this.bookDAO = bookDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

        Book book = (Book) target;

    }
}
