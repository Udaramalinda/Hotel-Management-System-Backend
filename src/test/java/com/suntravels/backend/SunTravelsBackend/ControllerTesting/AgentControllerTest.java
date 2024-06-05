package com.suntravels.backend.SunTravelsBackend.ControllerTesting;

import com.suntravels.backend.SunTravelsBackend.DTO.AgentDTO;
import com.suntravels.backend.SunTravelsBackend.DTO.AgentResponse;
import com.suntravels.backend.SunTravelsBackend.DTO.LoginDTO;
import com.suntravels.backend.SunTravelsBackend.controller.AgentController;
import com.suntravels.backend.SunTravelsBackend.service.AgentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith( MockitoExtension.class )
public class AgentControllerTest
{
    @Mock
    private AgentService agentService;
    @InjectMocks
    private AgentController agentController;

    @Test
    void testRegisterAgent(){
        AgentDTO agentDTO = new AgentDTO(
                "admin@gmail.com",
                "admin",
                "udaramalinda",
                true
        );

        when( agentService.addNewAgent( agentDTO )).thenReturn( "Success" );

        ResponseEntity<Map<String,String>> response = agentController.registerAgent( agentDTO );

        assertThat( response.getStatusCode().value() ).isEqualTo( 200 );
        assertThat( response.getBody().get( "status" ) ).isEqualTo( "Success" );
    }

    @Test
    void testGetAgent(){
        List<AgentResponse> agentResponses = new ArrayList<>();
        AgentResponse agentResponse = new AgentResponse(
                "Udara Malinda",
                "udara@gmail.com",
                "ADMIN"
        );
        agentResponses.add( agentResponse );

        when( agentService.getAgents()).thenReturn( agentResponses );

        assertThat( agentController.getAgents().get( 0 ).getName() ).isEqualTo( "Udara Malinda" );
        assertThat( agentController.getAgents().size() ).isEqualTo( 1 );
    }

    @Test
    void testLoginAgent(){
        LoginDTO loginDTO = new LoginDTO( "admin@gmail.com", "admin3" );
        when(agentService.loginAgent( loginDTO )).thenReturn( "Authentication Success" );

        assertThat( agentController.loginAgent( loginDTO ).getStatusCode().value() ).isEqualTo( 200 );

    }

    @Test
    void testLoginAgentFail(){
        LoginDTO loginDTO = new LoginDTO( "admin@gmail.com", "admin3" );
        when(agentService.loginAgent( loginDTO )).thenReturn( "Authentication Failed...!" );

        assertThat( agentController.loginAgent( loginDTO ).getStatusCode().value() ).isEqualTo( 400 );

    }
}
