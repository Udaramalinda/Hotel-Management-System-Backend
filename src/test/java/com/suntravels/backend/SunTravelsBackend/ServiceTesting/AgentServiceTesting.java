package com.suntravels.backend.SunTravelsBackend.ServiceTesting;

import com.suntravels.backend.SunTravelsBackend.DTO.AgentDTO;
import com.suntravels.backend.SunTravelsBackend.DTO.LoginDTO;
import com.suntravels.backend.SunTravelsBackend.config.JwtService;
import com.suntravels.backend.SunTravelsBackend.model.Agent;
import com.suntravels.backend.SunTravelsBackend.model.Role;
import com.suntravels.backend.SunTravelsBackend.repository.AgentRepository;
import com.suntravels.backend.SunTravelsBackend.service.AgentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith( MockitoExtension.class )
public class AgentServiceTesting
{
    @Mock
    private AgentRepository agentRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private JwtService jwtService;
    @InjectMocks
    private AgentService agentService;

    private AgentDTO agentDTO;
    private AgentDTO agentDTO1;
    private Agent agent;
    private List<Agent> agents;
    private LoginDTO loginDTO;
    private LoginDTO loginDTO1;

    @BeforeEach
    void setUpData(){
        agentDTO = new AgentDTO("admin@codegen.net", "admin", "admin1", true);
        agentDTO1 = new AgentDTO("user@codegen.net", "user", "user1", false);
        agent = new Agent( "admin@codegen.net", "admin", "admin1", Role.ADMIN);
        loginDTO = new LoginDTO( "admin@codegen.net", "admin1" );
        loginDTO1 = new LoginDTO( "admin1@codegen.net", "admin2" );
        agents = new ArrayList<>();
        agents.add( agent );
    }

    @Test
    void testAddNewAgent(){
        when( agentRepository.findAgentByEmail( agentDTO.getEmail() )).thenReturn( Optional.empty() );
        when( passwordEncoder.encode( anyString() ) ).thenReturn( "UDARA" );

        assertThat( agentService.addNewAgent( agentDTO ) ).isEqualTo( "Success" );
    }

    @Test
    void testAddNewAgentOne(){
        when( agentRepository.findAgentByEmail( agentDTO1.getEmail() )).thenReturn( Optional.empty() );
        when( passwordEncoder.encode( anyString() ) ).thenReturn( "UDARA" );

        assertThat( agentService.addNewAgent( agentDTO1 ) ).isEqualTo( "Success" );
    }

    @Test
    void testAddNewAgentFailed(){
        when( agentRepository.findAgentByEmail( agentDTO.getEmail() )).thenReturn( Optional.of( agent ) );

        assertThat( agentService.addNewAgent( agentDTO ) ).isEqualTo( "User Already Exists" );
    }

    @Test
    void testGetAgents(){
        when( agentRepository.findAll() ).thenReturn( agents );

        assertThat( agentService.getAgents().size() ).isEqualTo( 1 );
        assertThat( agentService.getAgents().get( 0 ).getEmail() ).isEqualTo( "admin@codegen.net" );
    }

    @Test
    void testLoginAgent(){
        when( agentRepository.findByEmail( loginDTO.getEmail() )).thenReturn( Optional.of( agent ) );
        when( jwtService.generateToken( anyMap(), any(Agent.class) )).thenReturn( "token" );

        String result = agentService.loginAgent( loginDTO );

        assertThat( result ).isEqualTo( "token" );
    }

    @Test
    void testLoginAgentFailed(){
        when( authenticationManager.authenticate( any( UsernamePasswordAuthenticationToken.class ) ) )
                .thenThrow( new BadCredentialsException( "Wrong Credentials" ) );

        String result = agentService.loginAgent( loginDTO1 );

        assertThat( result ).isEqualTo( "Authentication Failed...!" );
    }
}
