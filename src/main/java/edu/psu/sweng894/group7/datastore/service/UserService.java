package edu.psu.sweng894.group7.datastore.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import edu.psu.sweng894.group7.datastore.entity.Users;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class UserService {

    @PersistenceContext
    private EntityManager entityManager;

    public long insert(Users user) {
        entityManager.persist(user);

        entityManager.flush();
        return user.getId();
    }

    public Users find(long id) {
        return entityManager.find(Users.class, id);
    }

    public List<Users> findAll() {
        Query query = entityManager.createNamedQuery("query_find_all_users", Users.class);
        return query.getResultList();
    }
}
