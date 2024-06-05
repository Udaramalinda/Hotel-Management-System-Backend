package com.suntravels.backend.SunTravelsBackend.repository;

import com.suntravels.backend.SunTravelsBackend.model.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AgentRepository extends JpaRepository<Agent, String>
{
    Optional<Agent> findAgentByEmail(String email);

    Optional<Agent> findByEmail(String email);


}
