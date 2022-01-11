package com.vadim.budgettracker.dao.impl;

import com.vadim.budgettracker.dao.CategoryDAO;
import com.vadim.budgettracker.entity.Category;
import com.vadim.budgettracker.entity.Operation;
import com.vadim.budgettracker.entity.User;
import com.vadim.budgettracker.entity.enums.Section;
import com.vadim.budgettracker.exception.NotFoundException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.*;

@Repository
public class CategoryDAOImpl implements CategoryDAO {

    private final NamedParameterJdbcTemplate template;

    // language=SQL
    private final String SQL_FIND_BY_ID = "SELECT * FROM categories WHERE id=:id";

    // language=SQL
    private final String SQL_FIND_ALL = "SELECT * FROM categories";

    // language=SQL
    private final String SQL_SAVE = "INSERT INTO categories VALUES(:name)";

    // language=SQL
    private final String SQL_DELETE_BY_ID = "DELETE FROM categories WHERE id=:id";

    // language=SQL
    private final String SQL_UPDATE = "UPDATE categories SET name=:name WHERE id=:id";

    public CategoryDAOImpl(DataSource dataSource) {
        this.template = new NamedParameterJdbcTemplate(dataSource);
    }

    private final RowMapper<Category> categoryRowMapper = (ResultSet rs, int rowNum) -> {
        Category category = new Category();

        category.setId(rs.getLong("id"));
        category.setName(rs.getString("name"));
        category.setSection(rs.getObject("section", Section.class));
        category.setColor(rs.getString("color"));
        category.setLogo(rs.getString("logo"));
        category.setUser(rs.getObject("user_id", User.class));
        //category.setOperations();
       // private List<Operation> operations;

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
        SqlParameterSource paramSource = new MapSqlParameterSource("name", category.getName());
        template.update(SQL_SAVE, paramSource);
        return category;
    }

    @Override
    public void deleteById(Long id) {
        SqlParameterSource paramSource = new MapSqlParameterSource("id", id);
        template.update(SQL_DELETE_BY_ID, paramSource);
    }

    @Override
    public Category update(Category category) {
        SqlParameterSource paramSource = new MapSqlParameterSource("id", category.getId())
                .addValue("name", category.getName());
        int status = template.update(SQL_UPDATE, paramSource);
        if (status == 0) {
            throw new NotFoundException("Category with id" + category.getId() + "is not found");
        }
        return category;
    }

    @Override
    public boolean existsById(Long id) {
        return false;
    }

    @Override
    public List<Operation> findAllByUserId(Long userId) {
        return null;
    }

    @Override
    public Category getById(Long categoryId) {
        return null;
    }
}
