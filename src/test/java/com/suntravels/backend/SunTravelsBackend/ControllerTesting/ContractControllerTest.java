package com.suntravels.backend.SunTravelsBackend.ControllerTesting;

import com.suntravels.backend.SunTravelsBackend.DTO.ContractDTO;
import com.suntravels.backend.SunTravelsBackend.DTO.ContractResponse;
import com.suntravels.backend.SunTravelsBackend.controller.ContractController;
import com.suntravels.backend.SunTravelsBackend.service.ContractService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith( MockitoExtension.class )
public class ContractControllerTest
{
    @Mock
    private ContractService contractService;
    @InjectMocks
    private ContractController contractController;

    @Test
    void testRegisterContract(){
        ContractDTO contractDTO = new ContractDTO();
        when(contractService.addNewContract(any(ContractDTO.class))).thenReturn("Contract Save Successfully");

        ResponseEntity<Map<String, String>> response = contractController.registerContract(contractDTO);

        assertThat(response.getStatusCode().value()).isEqualTo(200 );
        assertThat( response.getBody().get( "status" ) ).isEqualTo( "Contract Save Successfully" );
    }

    @Test
    void testGetContract(){

        List<ContractResponse> contractResponses = new ArrayList<>();
        contractResponses.add( new ContractResponse( "Hilton",
                LocalDate.of( 2023,12,1 ),
                LocalDate.of( 2023,12,31 ),
                new ArrayList<>()) );
        when(contractService.getContract( "Hilton" )).thenReturn( contractResponses );

        Map<String, String> hotel = new HashMap<>();
        hotel.put( "hotelName", "Hilton" );
        List<ContractResponse> contractResponseList = contractController.getContract( hotel );

        assertThat( contractResponseList.get( 0 ).getHotelName() ).isEqualTo( "Hilton" );
    }

}
