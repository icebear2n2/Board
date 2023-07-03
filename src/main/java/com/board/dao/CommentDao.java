package com.board.dao;

import com.board.domain.dto.CommentDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class CommentDao {
    private final JdbcTemplate jdbcTemplate;

    public CommentDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    //DI,디펜 의존성 주입한다, 그리고 new JdbcTemplate(dataSource);를 쓰기 전까지는 스프링을 쓸 수 없다.

    public List<CommentDto> findAll(Integer id) {
        String sql = "select c.id, c.board_id, c.content, c.username, c.name, c.create_at from board.comment  as c where c.board_id=? ORDER BY c.create_at  DESC";
        List<CommentDto> boardDtoList = jdbcTemplate.query(sql, getCommentDtoRowMapper(), id);
        return boardDtoList;
    }

    private RowMapper<CommentDto> getCommentDtoRowMapper() {
        return (rs, rowNum) ->
                new CommentDto(
                        rs.getInt("id"),
                        rs.getInt("board_id"),
                        rs.getString("content"),
                        rs.getString("username"),
                        rs.getString("name"),
                        rs.getTimestamp("create_at")
                );
    }

    public int insert(Integer bid, String content, String username, String name) {
        String sql = "insert into comment (board_id, content, username, name)" +
                "values (?, ?, ?, ?)";
        // 컨텐츠가 필요, 유저 id가 필요하다.
        try {
            return jdbcTemplate.update(sql, bid, content, username, name);
        } catch (Exception e) {
            return 0;
        }
    }
}