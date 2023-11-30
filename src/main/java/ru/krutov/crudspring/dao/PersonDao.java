package ru.krutov.crudspring.dao;

import org.springframework.stereotype.Component;
import ru.krutov.crudspring.models.Person;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDao {
    private static  int Peopple_count;
    private List<Person> people;

    {
        people = new ArrayList<>();
        people.add(new Person(++Peopple_count,"Tim"));
        people.add(new Person(++Peopple_count,"Tom"));
        people.add(new Person(++Peopple_count,"Bunm"));
        people.add(new Person(++Peopple_count,"BPLo"));
    }
    public List<Person> index(){
        return people;
    }
    public  Person show(int id){
      return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }
}
