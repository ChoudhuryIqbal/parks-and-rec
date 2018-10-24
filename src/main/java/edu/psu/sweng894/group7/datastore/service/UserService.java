package edu.psu.sweng894.group7.datastore.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import edu.psu.sweng894.group7.datastore.entity.AppUser;
import edu.psu.sweng894.group7.datastore.entity.Roles;
import edu.psu.sweng894.group7.datastore.entity.Tokens;
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
        java.util.List<AppUser> result = entityManager.createQuery("select t from AppUser t" ).getResultList();
        return result;
    }

    public void delete(AppUser user){
        entityManager.remove(user);
        flush();
    }

    /*
     JPA provider will update the new state upon calling flush method.
     Just update the new values to the entity in persistance context
     */
    public void update(AppUser user){
        entityManager.merge(user);

        /*
        List<AppUser> appUsers = findAll();
        for (AppUser tempuser : appUsers) {
            if (tempuser.getName().equalsIgnoreCase(user.getUsername())) {
                //user match the update
                tempuser.setPassword(user.getPassword());
                tempuser.setRoles(user.getRoles());
                tempuser.setUsername(user.getUsername());
                entityManager.merge(tempuser);
                flush();
                break;
            }
        }*/
    }

    public void flush(){
        entityManager.flush();
    }

    public List<Roles> findAllRoles() {
        Query query = entityManager.createNamedQuery("query_find_roles", Roles.class);
        return query.getResultList();
    }


}
