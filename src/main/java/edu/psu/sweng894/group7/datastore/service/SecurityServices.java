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
import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public class SecurityServices {
    @PersistenceContext
    private EntityManager entityManager;

    public String generateToken(String user) {
        UUID uuid = UUID.randomUUID();
        Tokens token = new Tokens();
        token.setToken(uuid.toString());
        long currentTime = System.currentTimeMillis();
        Timestamp ts = new Timestamp(currentTime);
        token.setCreatedTime(ts);
        token.setUsername(user);
        insert(token);
        return uuid.toString();
    }

    public void insert(Tokens token) {
        Tokens toUpdate=findTokenOfUser(token.getUsername());
         if(toUpdate ==null) {
             entityManager.persist(token);
         }else{
             long currentTime = System.currentTimeMillis();
             Timestamp ts = new Timestamp(currentTime);
             toUpdate.setCreatedTime(ts);
             toUpdate.setToken(token.getToken());
             entityManager.merge(toUpdate);
         }
        entityManager.flush();
    }

    public Tokens findTokenOfUser(String userName) {
        java.util.List<Tokens> result = entityManager.createQuery("select t from Tokens t where  t.username = :username").setParameter("username", userName).getResultList();
        if(result != null && result.size()==1)
            return  result.get(0);
        else
            return null;
    }

    public Tokens findToken(String token) {
        java.util.List<Tokens> result = entityManager.createQuery("select t from Tokens t where  t.token = :token").setParameter("token", token).getResultList();
        if(result != null && result.size()==1)
            return  result.get(0);
        else
            return null;
    }

    public boolean validate(String token) {
        System.out.println("Validating user token");
        System.out.println("user supplied authToken="+ token);
        Tokens tokens = findToken(token);
        if(tokens!=null) {
            System.out.println("Token in the system="+ tokens.getToken());
            long currentTime = System.currentTimeMillis();
            long tokencreatedTime = tokens.getCreatedTime().getTime();
            // tokencreatedTime=tokencreatedTime*1000;
            long elapsed = (currentTime - tokencreatedTime);
            elapsed = elapsed / (1000 * 60);
            System.out.println("Token elapsed in minutes"+ elapsed);
            if (elapsed <= 10) {
                System.out.println("End of Validationg user token: Authorized");
                return true;
            }
        }
        System.out.println("End of Validationg user token: Un-Authorized");
        return false;
    }

}



