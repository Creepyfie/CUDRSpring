package ru.krutov.crudspring.dao;

import org.springframework.beans.factory.annotation.Autowired;
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
        return jdbcTemplate.query("Select * From Person", new PersonMapper());
    }
   public  Person show(int id){
        return jdbcTemplate.query("Select * From Person Where id = ?", new Object[]{id}, new PersonMapper())
                .stream().findAny().orElse(null);
    }
    public void save(Person person){

        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO Person VALUES (1,?,?,?)");
            preparedStatement.setString(1, person.getName());
            preparedStatement.setInt(2, person.getAge());
            preparedStatement.setString(3, person.getEmail());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(int id, Person updperson){
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE Person Set name =?,age = ?, email = ? where id = ?");
            preparedStatement.setString(1,updperson.getName());
            preparedStatement.setString(3,updperson.getEmail());
            preparedStatement.setInt(2,updperson.getAge());
            preparedStatement.setInt(4,id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void delete(int id){
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("DELETE FROM Person WHERE id = ?");
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
