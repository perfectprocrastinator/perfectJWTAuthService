package com.example.perfectuserservice.repositories;

import com.example.perfectuserservice.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    Session save(Session session);
}
