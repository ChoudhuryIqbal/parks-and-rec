package edu.psu.sweng894.group7.datastore.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import edu.psu.sweng894.group7.datastore.entity.User;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class UserService {

    @PersistenceContext
    private EntityManager entityManager;

    public long insert(User user) {
        entityManager.persist(user);

        entityManager.flush();
        return user.getId();
    }

    public User find(long id) {
        return entityManager.find(User.class, id);
    }

    public List<User> findAll() {
        Query query = entityManager.createNamedQuery("query_find_all_users", User.class);
        return query.getResultList();
    }
}
