package edu.psu.sweng894.group7.datastore.service;


import edu.psu.sweng894.group7.datastore.entity.AppUser;
import edu.psu.sweng894.group7.datastore.entity.Tokens;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@Repository
@Transactional
public class SecurityServices {
    @PersistenceContext
    private EntityManager entityManager;

    public String generateToken() {
        UUID uuid = UUID.randomUUID();
        Tokens token = new Tokens();
        token.setToken(uuid.toString());
        long currentTime = System.currentTimeMillis();
        Timestamp ts = new Timestamp(currentTime);
        token.setCreatedTime(ts);
        insert(token);
        return uuid.toString();
    }

    public void insert(Tokens token) {
        entityManager.persist(token);
        entityManager.flush();
    }

    public Tokens find(String token) {
        java.util.List<Tokens> result = entityManager.createQuery("select t from Tokens t where  t.token = :token").setParameter("token", token).getResultList();
        if(result != null && result.size()==1)
            return  result.get(0);
        else
            return null;
    }

    public boolean validate(String token) {
        Tokens tokens = find(token);
        if(tokens!=null) {
            long currentTime = System.currentTimeMillis();
            long tokencreatedTime = tokens.getCreatedTime().getTime();
            long elapsed = (currentTime - tokencreatedTime);
            elapsed = elapsed / (1000 * 60);
            if (elapsed < 10) {
                return true;
            }
        }
        return false;
    }

}



