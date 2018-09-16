package edu.psu.sweng894.group7.datastore.service;

import edu.psu.sweng894.group7.datastore.entity.Leagues;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class LeagueService {

    @PersistenceContext
    private EntityManager entityManager;

    public long insert(Leagues league) {
            entityManager.persist(league);
            entityManager.flush();
            return league.getLeagueId();
    }

    public Leagues find(long id) {
        return entityManager.find(Leagues.class, id);
    }

    public List<Leagues> findAll() {
        Query query = entityManager.createNamedQuery("query_find_all_leagues", Leagues.class);
        return query.getResultList();
    }

    public void delete(Leagues league){
        entityManager.remove(league);
        flush();
    }

    /*
     JPA provider will update the new state upon calling flush method.
     Just update the new values to the entity in persistance context
     */
    public void update(Leagues league){
        List<Leagues> leagues = findAll();
        for (Leagues tempLeague : leagues) {
            if (tempLeague.getLeagueName().equalsIgnoreCase(league.getLeagueName()) &&
                    tempLeague.getLeagueId().equals(league.getLeagueId())) {
                //user match the update
                tempLeague.setDescription(league.getDescription());
                tempLeague.setSportId(league.getSportId());
                tempLeague.setAgeMin(league.getAgeMin());
                tempLeague.setAgeMax(league.getAgeMax());
                tempLeague.setCoed(league.getCoed());
                tempLeague.setTeamMin(league.getTeamMin());
                tempLeague.setTeamMax(league.getTeamMax());
                tempLeague.setLeagueSchedule(league.getLeagueSchedule());
                tempLeague.setLeagueRules(league.getLeagueRules());
                entityManager.merge(tempLeague);
                flush();
                break;
            }
        }
    }

    public void flush(){
        entityManager.flush();
    }



}
