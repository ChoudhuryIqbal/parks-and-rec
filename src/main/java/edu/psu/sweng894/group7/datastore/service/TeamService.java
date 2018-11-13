package edu.psu.sweng894.group7.datastore.service;

import edu.psu.sweng894.group7.datastore.entity.Teams;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class TeamService {

    @PersistenceContext
    private EntityManager entityManager;

    public long insert(Teams team) {
        entityManager.persist(team);
        entityManager.flush();
        return team.getTeamId();
    }

    public Teams find(long id) {

        return entityManager.find(Teams.class, id);
    }

    public List<Teams> findAll() {
        Query query = entityManager.createNamedQuery("query_find_teams", Teams.class);
        return query.getResultList();
    }

    public void delete(Teams team){
        entityManager.remove(team);
        flush();
    }

    /*
     JPA provider will update the new state upon calling flush method.
     Just update the new values to the entity in persistance context
     */
    public void update(Teams team){
        List<Teams> teams = findAll();
        for (Teams tempTeam : teams) {
            if (tempTeam.getTeamId().equals(team.getTeamId())) {
                //user match the update
                tempTeam.setTeamName(team.getTeamName());
                tempTeam.setDescription(team.getDescription());
                tempTeam.setTeamManager(team.getTeamManager());
                tempTeam.setLeagueId(team.getLeagueId());
                tempTeam.setIsChampion(team.getIsChampion());
                entityManager.merge(tempTeam);
                flush();
                break;
            }
        }
    }

    public void flush(){
        entityManager.flush();
    }

}
