package com.vadim.budgettracker.dao.impl;

import com.vadim.budgettracker.dao.UserDAO;
import com.vadim.budgettracker.entity.User;
import com.vadim.budgettracker.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Objects;
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
        if (Objects.isNull(user)) {
            return Optional.empty();
        }
        manager.detach(user);
        return Optional.of(user);
    }

    @Override
    public boolean existsById(Long id) {
        return !Objects.isNull(manager.find(User.class, id));
    }

    @Override
    public User save(User user) {
        manager.getTransaction().begin();
        user.setId(null);
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

//        manager.getTransaction().begin();
//        int isSuccessful = manager.createQuery("DELETE FROM User u WHERE u.id=:id")
//                .setParameter("id", id)
//                .executeUpdate();
//        if (isSuccessful == 0) {
//            throw new RuntimeException("Something was wrong during deleting a user");
//        }
//        manager.getTransaction().commit();

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
        return !manager.createQuery("SELECT u FROM User u WHERE u.email=:email", User.class)
                .setParameter("email", email)
                .getResultList().isEmpty();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        List<User> users = manager.createQuery("SELECT u FROM User u WHERE u.email=:email", User.class)
                .setParameter("email", email)
                .getResultList();
        if (users.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(users.get(0));
    }

    @Override
    public User getById(Long userId) {
        User user = manager.getReference(User.class, userId);
        if (Objects.isNull(user)) {
            throw new NotFoundException("User with id=" + userId + " is not found");
        }
        return user;
    }
}

