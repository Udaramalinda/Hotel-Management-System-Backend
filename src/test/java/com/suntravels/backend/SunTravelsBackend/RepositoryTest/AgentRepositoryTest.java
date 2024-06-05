package com.suntravels.backend.SunTravelsBackend.RepositoryTest;

import com.suntravels.backend.SunTravelsBackend.model.Agent;
import com.suntravels.backend.SunTravelsBackend.model.Role;
import com.suntravels.backend.SunTravelsBackend.repository.AgentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class AgentRepositoryTest
{
    @Autowired
    private AgentRepository agentRepository;

    private Agent agent;

    @BeforeEach
    public void setUpData()
    {
        agent = new Agent(
                "udaram@codegen.net",
                "Benuka",
                "udaram",
                Role.ADMIN );
        agentRepository.save( agent );
    }

    @Test
    public void testFindAgentByEmail()
    {

        String email1 = "udaram@codegen.net";
        String email2 = "udara@codegen.net";

        assertThat( agentRepository.findAgentByEmail( email1 ) ).isPresent();
        assertThat( agentRepository.findAgentByEmail( email1 ).get().getEmail() ).isEqualTo( agent.getEmail() );

        assertThat( agentRepository.findAgentByEmail( email2 ) ).isNotPresent();
    }

    @Test
    public void testFindByEmail()
    {

        String email1 = "udaram@codegen.net";
        String email2 = "udara@codegen.net";

        assertThat( agentRepository.findByEmail( email1 ) ).isPresent();
        assertThat( agentRepository.findByEmail( email1 ).get().getEmail() ).isEqualTo( agent.getEmail() );

        assertThat( agentRepository.findByEmail( email2 ) ).isNotPresent();
    }
}


