package ru.krutov.crudspring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.krutov.crudspring.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDao {

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public PersonDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }

    public List<Person> index(){
        return jdbcTemplate.query("Select * From Person", new BeanPropertyRowMapper<>(Person.class));
    }
   public  Person show(int id){
        return jdbcTemplate.query("Select * From Person Where id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }
    public void save(Person person){

        jdbcTemplate.update("Insert Into Person VALUES (1,?,?,?)",person.getName(),person.getAge(),person.getEmail());
    }

    public void update(int id, Person updperson){
        jdbcTemplate.update("UPDATE  From Person SET name =?, age =?, email = ?, id = 1", updperson.getName(),updperson.getAge(),updperson.getEmail());

    }

    public void delete(int id){
        jdbcTemplate.update("DELETE From Person WHERE id = ?",id);
    }
}
