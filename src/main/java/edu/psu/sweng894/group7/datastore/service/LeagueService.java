package edu.psu.sweng894.group7.datastore.service;

import edu.psu.sweng894.group7.datastore.entity.Leagues;
import edu.psu.sweng894.group7.datastore.entity.Tokens;
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
        java.util.List<Leagues> result = entityManager.createQuery("select l from Leagues l").getResultList();
        return result;
    }

    public List<Leagues>  findUserLeagues(Long userId, String orgId){
        java.util.List<Leagues> result=null;
        if(userId==1)
             result = entityManager.createQuery("select t from leagues t where  t.orgid = :orgid").setParameter("orgid", orgId).getResultList();
        else{
            result = entityManager.createQuery("select t from leagues t where  t.user_id = :user_id and orgid = :orgid").setParameter("user_id", userId).setParameter("orgid", orgId).getResultList();
        }
        return result;
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
            if (tempLeague.getLeagueId().equals(league.getLeagueId())) {
                //league match the update
                tempLeague.setLeagueName(league.getLeagueName());
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
