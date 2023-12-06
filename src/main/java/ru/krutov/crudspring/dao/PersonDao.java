package ru.krutov.crudspring.dao;

import org.springframework.stereotype.Component;
import ru.krutov.crudspring.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDao {
    private static  int Peopple_count;
    private  static final String URL = "jdbc:postgresql://localhost:5432/first_db";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "140502";

    private  static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }

    public List<Person> index(){
        List<Person> people = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * from Person";
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()){
                Person person = new Person();
                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setAge(resultSet.getInt("age"));
                person.setEmail(resultSet.getString("email"));

                people.add(person);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return people;
    }
   public  Person show(int id){
        Person person = null;
       try {
           PreparedStatement preparedStatement =
                   connection.prepareStatement("SELECT * FROM  Person Where id = ?");
           preparedStatement.setInt(1, id);
           ResultSet resultSet =  preparedStatement.executeQuery();
           resultSet.next();
           person = new Person();
           person.setId(resultSet.getInt("id"));
           person.setName(resultSet.getString("name"));
           person.setAge(resultSet.getInt("age"));
           person.setEmail(resultSet.getString("email"));

       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
       return person;
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
