package com.suntravels.backend.SunTravelsBackend.config;

import com.suntravels.backend.SunTravelsBackend.DTO.AgentDTO;
import com.suntravels.backend.SunTravelsBackend.model.Agent;
import com.suntravels.backend.SunTravelsBackend.repository.AgentRepository;
import com.suntravels.backend.SunTravelsBackend.service.AgentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

@Configuration
public class AgentConfiguration
{
    @Bean
    CommandLineRunner commandLineRunner(
            AgentRepository agentRepository,
            AgentService agentService
    ) {
        return args -> {

            Optional<Agent> agentOptional = agentRepository.findAgentByEmail( "admin@codegen.net" );

            if (agentOptional.isEmpty()) {

                AgentDTO agentDTO = new AgentDTO(
                        "admin@codegen.net",
                        "admin",
                        "admin1",
                        true
                );

                agentService.addNewAgent( agentDTO );

            }

        };
    }
}
