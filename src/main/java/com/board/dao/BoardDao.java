package com.board.dao;

import com.board.domain.dto.BoardDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class BoardDao {

    private final JdbcTemplate jdbcTemplate;


    public BoardDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<BoardDto> getBoardDtoRowMapper() {
        return (rs, rowNum) ->
                new BoardDto(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getString("username"),
                        rs.getString("name"),
                        rs.getTimestamp("create_at")
                );
    }

    public List<BoardDto> findAll() {
        String sql = "SELECT b.id, b.title, b.username, b.name, b.content, b.create_at FROM board.board AS b ORDER BY b.create_at DESC";
        List<BoardDto> boardDtoList = jdbcTemplate.query(sql, getBoardDtoRowMapper());
        return boardDtoList;
    }

    public int insert(String title, String content, String username, String name) {
        String sql = "insert into board(title, content, username, name) VALUES (?,?,?,?)";
        return jdbcTemplate.update(sql, title, username, content, name);
    }

    public BoardDto findBoardById(Integer id) {
        String sql = "select b.id, b.title, b.username, b.name, b.content, b.create_at from board.board as b where id = ?";
        return jdbcTemplate.queryForObject(sql, getBoardDtoRowMapper(), id);

    }

    public int update(String title, String content, Integer id) {
        String sql = "update board set title = ?, content = ? where id = ?";
        return jdbcTemplate.update(sql, title, content, id);
    }

    public int delete(Integer id) {
        String sql = "DELETE c, b\n" +
                "FROM comment c\n" +
                "JOIN board b ON c.board_id = b.id\n" +
                "WHERE b.id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
