package com.suntravels.backend.SunTravelsBackend.service;

import com.suntravels.backend.SunTravelsBackend.DTO.AgentDTO;
import com.suntravels.backend.SunTravelsBackend.DTO.AgentResponse;
import com.suntravels.backend.SunTravelsBackend.DTO.LoginDTO;
import com.suntravels.backend.SunTravelsBackend.config.JwtService;
import com.suntravels.backend.SunTravelsBackend.model.Agent;
import com.suntravels.backend.SunTravelsBackend.model.Role;
import com.suntravels.backend.SunTravelsBackend.repository.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AgentService
{
    private final AgentRepository agentRepository;
    private AuthenticationManager authenticationManager;
    private JwtService jwtService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AgentService( AgentRepository agentRepository,
                         AuthenticationManager authenticationManager,
                         JwtService jwtService,
                         PasswordEncoder passwordEncoder){
        this.agentRepository = agentRepository;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    public String addNewAgent( AgentDTO agentDTO){
        Optional<Agent> agentOptional = agentRepository.findAgentByEmail( agentDTO.getEmail() );

        Role role = null;
        // Check the agent exist the system
        if (agentOptional.isPresent()){
            return "User Already Exists";
        }
        // create agent role
        else if ( agentDTO.isAdmin() ){
            role = Role.ADMIN;
        }
        else {
            role = Role.USER;
        }
        // encode password
        String encodePassword = passwordEncoder.encode( agentDTO.getPassword() );

        Agent agent = new Agent( agentDTO.getEmail(), agentDTO.getName(), encodePassword, role );
        agentRepository.save( agent );
        return "Success";
    }
    public List<AgentResponse> getAgents(){
        List<Agent> agentList = agentRepository.findAll();
        List<AgentResponse> agentResponses = new ArrayList<>();

        for ( Agent agent: agentList){
            AgentResponse agentResponse = new AgentResponse(
                    agent.getName(),
                    agent.getEmail(),
                    agent.isAdmin().toString()
            );

            agentResponses.add( agentResponse );
        }

        return agentResponses;
    }

    public String loginAgent ( LoginDTO loginDTO ){

        try {
            // authenticate the user
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDTO.getEmail(),
                            loginDTO.getPassword()
                    )
            );
            var user = agentRepository.findByEmail( loginDTO.getEmail() ).orElseThrow();
            Map<String, Object> extraClaims = new HashMap<>();
            // create the token
            extraClaims.put( "role", user.isAdmin());
            return jwtService.generateToken( extraClaims, user );
        } catch( AuthenticationException e ){
            return "Authentication Failed...!";
        }
    }
}
