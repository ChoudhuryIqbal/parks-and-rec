package edu.psu.sweng894.group7.datastore.service;

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
        return sport.getSportId();
    }

    public Sport find(long id) {

        return entityManager.find(Sport.class, id);
    }

    public List<Sport> findAll() {
        Query query = entityManager.createNamedQuery("query_find_all_sports", Sport.class);
        return query.getResultList();
    }

    public void delete(Sport sport){
        entityManager.remove(sport);
        flush();
    }

    /*
     JPA provider will update the new state upon calling flush method.
     Just update the new values to the entity in persistance context
     */
    public void update(Sport sport){
        List<Sport> sports = findAll();
        for (Sport tempSport : sports) {
            if (tempSport.getSportId().equals(sport.getSportId()) &&
                tempSport.getDepartmentId().equals(sport.getDepartmentId())) {
                tempSport.setSportName(sport.getSportName());
                tempSport.setSportDescription(sport.getSportDescription());
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
