package com.suntravels.backend.SunTravelsBackend.controller;

import com.suntravels.backend.SunTravelsBackend.DTO.ContractDTO;
import com.suntravels.backend.SunTravelsBackend.DTO.ContractResponse;
import com.suntravels.backend.SunTravelsBackend.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/contract/")
public class ContractController
{
    private final ContractService contractService;

    @Autowired
    public ContractController( ContractService contractService ){
        this.contractService = contractService;
    }
    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerContract ( @RequestBody ContractDTO contractDTO ){
        String message = contractService.addNewContract(contractDTO);
        Map<String, String> response = new HashMap<>();
        response.put("status", message);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/details")
    public List<ContractResponse> getContract( @RequestBody Map<String, String> hotel ){
        String hotelName = hotel.get("hotelName");
        return contractService.getContract( hotelName );
    }
}
