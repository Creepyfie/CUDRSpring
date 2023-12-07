package ru.krutov.crudspring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
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

        jdbcTemplate.update("Insert Into Person VALUES (1,?,?,?)"
                ,person.getName(),person.getAge(),person.getEmail());
    }

    public void update(int id, Person updperson){
        jdbcTemplate.update("UPDATE  From Person SET name =?, age =?, email = ?, id = 1", updperson.getName(),updperson.getAge(),updperson.getEmail());

    }

    public void delete(int id){
        jdbcTemplate.update("DELETE From Person WHERE id = ?",id);
    }


    //////////////////////////////

    public  void testMultipleUpdate(){
        List<Person> people = create1000People();

        long before = System.currentTimeMillis();
        for (Person person: people){
            jdbcTemplate.update("Insert Into Person VALUES (?,?,?,?)"
                    ,person.getId(),person.getName(),person.getAge(),person.getEmail());
        }
        long after= System.currentTimeMillis();
        System.out.println("Time " + (after-before));
    }

    public void testBatchUpdate(){
        List<Person> people = create1000People();
        long before = System.currentTimeMillis();
        jdbcTemplate.batchUpdate("INSERT INTO Person VALUES (?,?,?,?)", new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setInt(1,people.get(i).getId());
                ps.setString(2,people.get(i).getName());
                ps.setInt(3,people.get(i).getAge());
                ps.setString(4,people.get(i).getEmail());
            }

            @Override
            public int getBatchSize() {
                return people.size();
            }
        });
        long after= System.currentTimeMillis();
        System.out.println("Time " + (after-before));

    }

    private List<Person> create1000People() {
        List<Person> people = new ArrayList<>();

        for(int i = 0;i<1000;i++){
            people.add(new Person(i, "Name"+i,30,"test"+i+"@mail.ru"));
        }
        return people;
    }
}
