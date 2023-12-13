package ru.krutov.crudspring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.krutov.crudspring.models.Person;

import java.sql.*;
import java.util.List;


@Component
public class PersonDao {

    private final RowMapper<Person> rowMapper = new PersonRowMapper();

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public PersonDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }

    public List<Person> index(){
        return jdbcTemplate.query("Select * From Person", rowMapper);
    }
   public  Person show(int id){
        return jdbcTemplate.query("Select * From Person Where person_id = ?", new Object[]{id}, rowMapper)
                .stream().findAny().orElse(null);
    }

    public void save(Person person){

        jdbcTemplate.update("Insert Into Person(name, birthyear) VALUES (?,?)"
                ,person.getName(),person.getBirthYear());
    }

    public void update(int id, Person updperson){
        jdbcTemplate.update("UPDATE Person SET name =?, birthyear =? Where person_id = ?",
                updperson.getName(),updperson.getBirthYear(),
                id);

    }

    public void delete(int id){
        jdbcTemplate.update("DELETE From Person WHERE person_id = ?",id);
    }

    private static class PersonRowMapper implements RowMapper<Person>{

        @Override
        public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Person(
                    rs.getInt("person_id"),
                    rs.getString("name"),
                    rs.getInt("birthyear")
            );
        }
    }
}
