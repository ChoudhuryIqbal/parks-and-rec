package edu.psu.sweng894.group7.datastore.service.datastore.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import edu.psu.sweng894.group7.datastore.service.datastore.entity.AppUser;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class UserService {

    @PersistenceContext
    private EntityManager entityManager;

    public long insert(AppUser user) {
        entityManager.persist(user);
        entityManager.flush();
        return user.getId();
    }

    public AppUser find(long id) {
        return entityManager.find(AppUser.class, id);
    }

    public List<AppUser> findAll() {
        Query query = entityManager.createNamedQuery("query_find_all_users", AppUser.class);
        return query.getResultList();
    }
}
