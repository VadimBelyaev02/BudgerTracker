package com.vadim.budgettracker.dao.impl;

import com.vadim.budgettracker.dao.CategoryDAO;
import com.vadim.budgettracker.entity.Category;
import com.vadim.budgettracker.entity.Operation;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.*;

@Repository
public class CategoryDAOImpl implements CategoryDAO {

    private final NamedParameterJdbcTemplate template;

    // language=SQL
    private final String SQL_FIND_BY_ID = "SELECT * FROM users WHERE id=:id";

    // language=SQL
    private final String SQL_FIND_ALL = "SELECT * FROM users";

    // language=SQL
    private final String SQL_SAVE_CATEGORY = "INSERT INTO categories VALUES(:name)";

    public CategoryDAOImpl(DataSource dataSource) {
        this.template = new NamedParameterJdbcTemplate(dataSource);
    }

    private final RowMapper<Category> categoryRowMapper = (ResultSet rs, int rowNum) -> {
        Category category = new Category();

        category.setId(rs.getLong("id"));
        category.setName(rs.getString("name"));

        return category;
    };

    @Override
    public List<Category> findAll() {
        return template.query(SQL_FIND_ALL, categoryRowMapper);
    }

    @Override
    public Optional<Category> findById(Long id) {
        SqlParameterSource paramSource = new MapSqlParameterSource("id", id);
        Category category = template.queryForObject(SQL_FIND_BY_ID, paramSource, categoryRowMapper);
        if (Objects.isNull(category)) {
            return Optional.empty();
        }
        return Optional.of(category);
    }

    @Override
    public Category save(Category category) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", category.getName());
        template.update(SQL_SAVE_USER, params);
        return category;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Category update(Category category) {
        return null;
    }
}
