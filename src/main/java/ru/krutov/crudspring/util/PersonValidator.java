package ru.krutov.crudspring.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.krutov.crudspring.dao.PersonDao;
import ru.krutov.crudspring.models.Person;
@Component
public class PersonValidator implements Validator {

    private PersonDao personDao;

    @Autowired
    public PersonValidator(PersonDao personDao){
        this.personDao = personDao;
    }
    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Person person = (Person) target;

        if (personDao.show(person.getEmail()).isPresent()){
            errors.rejectValue("email","", "This email already exist!");
        }

    }
}
