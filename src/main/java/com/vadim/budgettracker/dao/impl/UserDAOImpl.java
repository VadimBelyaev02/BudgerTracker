package com.vadim.budgettracker.dao.impl;

import com.vadim.budgettracker.dao.UserDAO;
import com.vadim.budgettracker.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDAOImpl implements UserDAO {

    private final EntityManager manager;

    public UserDAOImpl(@Qualifier("entityManager") EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public List<User> findAll() {
        return manager.createQuery("SELECT u FROM User u", User.class)
                .getResultList();
    }

    @Override
    public Optional<User> findById(Long id) {
        User user = manager.find(User.class, id);
        manager.detach(user);
        return Optional.of(user);
    }

    @Override
    public boolean existsById(Long id) {
        return false;
    }

    @Override
    public User save(User user) {
        manager.getTransaction().begin();
        manager.persist(user);
        manager.getTransaction().commit();
        return user;
    }

    @Override
    public void deleteById(Long id) {
        manager.getTransaction().begin();
        User user = manager.find(User.class, id);
        manager.remove(user);
        manager.getTransaction().commit();
    }

    @Override
    public User update(User user) {
        manager.getTransaction().begin();
        user = manager.merge(user);
        manager.getTransaction().commit();
        return user;
    }

    @Override
    public boolean existsByEmail(String email) {
        return manager.createQuery("SELECT u FROM User u WHERE u.email=:email", User.class)
                .setParameter("email", email)
                .getSingleResult() == null;
        // can be a mistake like it will throw an exception
    }

    @Override
    public Optional<User> findByEmail(String email) {
        User user = manager.createQuery("SELECT u FROM User u WHERE u.email=:email", User.class)
                .setParameter("email", email)
                .getSingleResult();
        return Optional.of(user);
    }

    @Override
    public User getById(Long userId) {
        return null;
    }
}

