package com.vadim.budgettracker.dao.impl;

import com.vadim.budgettracker.dao.CategoryDAO;
import com.vadim.budgettracker.entity.Category;
import com.vadim.budgettracker.entity.User;
import com.vadim.budgettracker.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class CategoryDAOImpl implements CategoryDAO {

    private final EntityManager manager;

    public CategoryDAOImpl(@Qualifier("entityManager") EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public List<Category> findAllByUserId(Long userId) {
        return manager.createQuery("SELECT u FROM Category u WHERE u.user.id=:user_id", Category.class)
                .setParameter("user_id", userId)
                .getResultList();
    }

    @Override
    public Category getById(Long categoryId) {
        Category category = manager.getReference(Category.class, categoryId);
        if (Objects.isNull(category)) {
            throw new NotFoundException("User with id=" + categoryId + " is not found");
        }
        return category;
    }

    @Override
    public boolean existsByName(String name) {
        return !manager.createQuery("SELECT u FROM Category u WHERE u.name=:name", Category.class)
                .setParameter("name", name)
                .getResultList().isEmpty();
        }

    @Override
    public Category getByName(String name) {
        List<Category> categories = manager.createQuery("SELECT u FROM Category u WHERE u.name=:name", Category.class)
                .setParameter("name", name)
                .getResultList();
        if (categories.isEmpty()) {
            throw new NotFoundException("Category with name=" + name + " is not found");
        }
        return categories.get(0);
    }

    @Override
    public List<Category> findAll() {
        return manager.createQuery("SELECT u FROM Category u", Category.class)
                .getResultList();
    }

    @Override
    public Optional<Category> findById(Long id) {
        Category category = manager.find(Category.class, id);
        if (Objects.isNull(category)) {
            return Optional.empty();
        }
        return Optional.of(category);
    }

    @Override
    public boolean existsById(Long id) {
        return !Objects.isNull(manager.find(Category.class, id));
    }

    @Override
    public Category save(Category category) {
        manager.getTransaction().begin();
        category.setId(null);
        manager.persist(category);
        manager.getTransaction().commit();
        return category;
    }

    @Override
    public void deleteById(Long id) {
        manager.getTransaction().begin();
        Category category = manager.find(Category.class, id);
        manager.remove(category);
        manager.getTransaction().commit();
    }

    @Override
    public Category update(Category category) {
        manager.getTransaction().begin();
        category = manager.merge(category);
        manager.getTransaction().commit();
        return category;
    }
}
