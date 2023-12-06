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
        people.add(new Person(++Peopple_count,"Tim", 24,"tim@mail.ru"));
        people.add(new Person(++Peopple_count,"Tom",43,"tom@mail.ru"));
        people.add(new Person(++Peopple_count,"Bunm",54,"bunm@mail.ru"));
        people.add(new Person(++Peopple_count,"Bill",63,"billy@mail.ru"));
    }
    public List<Person> index(){
        return people;
    }
    public  Person show(int id){
      return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }
    public void save(Person person){
        person.setId(++Peopple_count);
        people.add(person);
    }

    public void update(int id, Person updperson){
        Person persnToBeUpdated = show(id);
        persnToBeUpdated.setName(updperson.getName());
        persnToBeUpdated.setAge(updperson.getAge());
        persnToBeUpdated.setEmail(updperson.getEmail());
    }

    public void delete(int id){
        people.removeIf(person -> person.getId()==id);
    }
}
