package com.suntravels.backend.SunTravelsBackend.controller;

import com.suntravels.backend.SunTravelsBackend.DTO.AgentDTO;
import com.suntravels.backend.SunTravelsBackend.DTO.AgentResponse;
import com.suntravels.backend.SunTravelsBackend.DTO.LoginDTO;
import com.suntravels.backend.SunTravelsBackend.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/agent/")
public class AgentController
{

    private final AgentService agentService;

    @Autowired
    public AgentController( AgentService agentService ){
        this.agentService = agentService;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerAgent( @RequestBody AgentDTO agentDTO ){
        String message = agentService.addNewAgent( agentDTO );
        Map<String, String> response = new HashMap<>();
        response.put("status", message);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginAgent ( @RequestBody LoginDTO loginDTO ){
        String token =  agentService.loginAgent( loginDTO );

        if(token.equals( "Authentication Failed...!" )) {
            return ResponseEntity.badRequest().body( token );
        }
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Authorization", "Bearer " + token);

        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("token", token);

        return ResponseEntity.ok().headers(responseHeaders).body(responseBody);

    }

    @GetMapping("/details")
    public List<AgentResponse> getAgents(){
        return agentService.getAgents();
    }

}
