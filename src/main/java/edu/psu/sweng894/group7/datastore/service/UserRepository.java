package edu.psu.sweng894.group7.datastore.service;

import edu.psu.sweng894.group7.datastore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
}