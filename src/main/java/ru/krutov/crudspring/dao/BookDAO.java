package ru.krutov.crudspring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.krutov.crudspring.models.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Component
public class BookDAO {
    private final BookRowMapper rowMapper = new BookRowMapper();


    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index(){


        return jdbcTemplate.query("Select * From Books", rowMapper);
    }

    public Book show(int id){
        return jdbcTemplate.query("Select * From Person Where id = ?", Objects[]{id},rowMapper);
    }
    private  static class BookRowMapper implements RowMapper{

        @Override
        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Book(rs.getInt("book_id"),
                    rs.getInt("person_id"),
                    rs.getString("name"),
                    rs.getString("author"),
                    rs.getInt("year"));
        }
    }


}
