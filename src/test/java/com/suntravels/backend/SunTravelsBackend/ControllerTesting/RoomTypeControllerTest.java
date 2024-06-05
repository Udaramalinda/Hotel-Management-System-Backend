package com.suntravels.backend.SunTravelsBackend.ControllerTesting;

import com.suntravels.backend.SunTravelsBackend.controller.RoomTypeController;
import com.suntravels.backend.SunTravelsBackend.model.RoomType;
import com.suntravels.backend.SunTravelsBackend.service.RoomTypeService;
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
public class RoomTypeControllerTest
{
    @Mock
    private RoomTypeService roomTypeService;
    @InjectMocks
    private RoomTypeController roomTypeController;

    @Test
    void testRegisterRoomType(){
        RoomType roomType = new RoomType( "Luxury");
        when( roomTypeService.addNewRoomType( roomType )).thenReturn( "Room Type Successfully Register In System" );

        ResponseEntity<Map<String,String>> response = roomTypeController.registerRoomType( roomType );

        assertThat( response.getStatusCode().value()).isEqualTo( 200 );
        assertThat( response.getBody().get( "status" ) ).isEqualTo( "Room Type Successfully Register In System" );

    }

    @Test
    void testGetRoomTypesNames(){
        List<String> roomTypeNames = new ArrayList<>();
        roomTypeNames.add( "Luxury" );
        when( roomTypeService.getRoomTypeNames()).thenReturn( roomTypeNames );

        assertThat( roomTypeController.getRoomTypeNames().get( 0 ) ).isEqualTo( "Luxury" );
    }

}
