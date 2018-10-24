package edu.psu.sweng894.group7.datastore.service;

import edu.psu.sweng894.group7.datastore.entity.Leagues;
import edu.psu.sweng894.group7.datastore.entity.Sport;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class SportService {

    @PersistenceContext
    private EntityManager entityManager;

    public long insert(Sport sport) {
        entityManager.persist(sport);
        entityManager.flush();
        return sport.getId();
    }

    public Sport find(long id) {

        return entityManager.find(Sport.class, id);
    }

    public List<Sport> findAll() {
        Query query = entityManager.createNamedQuery("query_find_sports", Sport.class);
        return query.getResultList();
    }

    public void delete(Sport sport){
        entityManager.remove(sport);
        flush();
    }

    public List<Sport>  findUserSport(Long userId, String orgId){
        java.util.List<Sport> result = null;
        if(userId==1) {
            result= entityManager.createQuery("select t from sport t where  t.orgid = :orgid").setParameter("orgid", orgId).getResultList();
        }else{
            result=entityManager.createQuery("select t from sport t where  t.user_id = :user_id and orgid = :orgid").setParameter("user_id", userId).setParameter("orgId", orgId).getResultList();
        }
        return result;
    }
    /*
     JPA provider will update the new state upon calling flush method.
     Just update the new values to the entity in persistance context
     */
    public void update(Sport sport){
        List<Sport> sports = findAll();
        for (Sport tempSport : sports) {
            if (tempSport.getId().equals(sport.getId()) &&   tempSport.getName().equals(sport.getName()) &&  tempSport.getDescription().equals(sport.getDescription())){
                 entityManager.merge(tempSport);
                 flush();
                break;
            }
        }
    }

    public void flush(){
        entityManager.flush();
    }
    
}
