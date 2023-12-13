package ru.krutov.crudspring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.krutov.crudspring.models.Book;

import java.sql.*;
import java.util.List;

@Component
public class BookDAO {
    private final BookRowMapper rowMapper = new BookRowMapper();


    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index(){
        return jdbcTemplate.query("Select * From Book", rowMapper);
    }

    public Book show(int id){
        return jdbcTemplate.query("Select * From Book Where book_id = ?",
                new Object[]{id},rowMapper).stream().findAny().orElse(null);
    }

    public void save(Book book){
        jdbcTemplate.update("Insert Into Book(name, author, year) VALUES (?,?,?)"
                ,book.getBookName(),book.getAuthor(),book.getYear());
    }

    public void update(int id, Book updBook){
        jdbcTemplate.update("UPDATE Book SET name = ?, author = ?, year = ? Where book_id = ? ",
                updBook.getBookName(),updBook.getAuthor(),updBook.getYear(),id);
    }

    public void unlink(int book_id){
        jdbcTemplate.update("UPDATE Book SET person_id = ? Where book_id = ?", null, book_id);
    }

    public void link(int book_id, int person_id){
        jdbcTemplate.update("UPDATE Book SET person_id = ? Where book_id = ?", person_id, book_id);
    }

    public void delete(int id){
        jdbcTemplate.update("DELETE FROM Book WHERE book_id = ?",id);
    }
    public List<Book> search(int person_id){
        return jdbcTemplate.query("Select * From Book Where person_id = ?", new Object[]{person_id},rowMapper);
    }

    private  static class BookRowMapper implements RowMapper<Book>{

        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Book(rs.getInt("book_id"),
                    rs.getInt("person_id"),
                    rs.getString("name"),
                    rs.getString("author"),
                    rs.getInt("year"));
        }
    }


}
