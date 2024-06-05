package com.suntravels.backend.SunTravelsBackend.ControllerTesting;

import com.suntravels.backend.SunTravelsBackend.controller.HotelController;
import com.suntravels.backend.SunTravelsBackend.model.Hotel;
import com.suntravels.backend.SunTravelsBackend.service.HotelService;
import org.apache.coyote.Response;
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
public class HotelControllerTest
{
    @Mock
    private HotelService hotelService;
    @InjectMocks
    private HotelController hotelController;

    @Test
    void testRegisterHotel(){
        Hotel hotel = new Hotel();
        when(hotelService.addNewHotel( hotel )).thenReturn( "Hotel Register Successfully" );

        ResponseEntity<Map<String,String>> response = hotelController.registerHotel( hotel );

        assertThat( response.getStatusCode().value()).isEqualTo( 200 );
        assertThat( response.getBody().get("status") ).isEqualTo( "Hotel Register Successfully" );
    }

    @Test
    void testGetHotelNames(){
        List<String> hotelNames = new ArrayList<>();
        hotelNames.add( "Galadari" );

        when( hotelService.getHotelNames() ).thenReturn( hotelNames );

        List<String> hotelNameList = hotelController.getHotelNames();

        assertThat( hotelNameList.get( 0 ) ).isEqualTo( "Galadari" );
    }

    @Test
    void testGetHotel(){
        List<Hotel> hotels = new ArrayList<>();
        Hotel hotel = new Hotel("Galadari","Colombo", "galadari@gamil.com", 1234567891);
        hotels.add( hotel );

        when( hotelService.getHotel()).thenReturn( hotels );

        assertThat( hotelController.getHotel().get( 0 ).getHotelName() ).isEqualTo( "Galadari" );
    }
}
