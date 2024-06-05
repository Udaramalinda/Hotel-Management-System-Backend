package com.suntravels.backend.SunTravelsBackend.ServiceTesting;

import com.suntravels.backend.SunTravelsBackend.model.Hotel;
import com.suntravels.backend.SunTravelsBackend.repository.HotelRepository;
import com.suntravels.backend.SunTravelsBackend.service.HotelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
@ExtendWith( MockitoExtension.class )
public class HotelServiceTesting
{
    @Mock
    private HotelRepository hotelRepository;
    @InjectMocks
    private HotelService hotelService;
    private Hotel hotel;
    private List<Hotel> hotels;

    @BeforeEach
    void setUpData(){
        hotel = new Hotel( "Galadari", "Colombo", "galadari@gmail.com", 1234567890);
        hotels = new ArrayList<>();
        hotels.add( hotel );
    }

    @Test
    void testAddNewHotel(){
        when( hotelRepository.findHotelByHotelName( hotel.getHotelName() ) ).thenReturn( Optional.empty() );

        assertThat( hotelService.addNewHotel( hotel ) ).isEqualTo( "Hotel Register Successfully" );

    }

    @Test
    void testAddNewHotelFailed(){
        when( hotelRepository.findHotelByHotelName( hotel.getHotelName() ) ).thenReturn( Optional.of( hotel ) );

        assertThat( hotelService.addNewHotel( hotel ) ).isEqualTo( "Hotel Already In System" );

    }

    @Test
    void testGetHotelNames(){
        when( hotelRepository.findAll() ).thenReturn( hotels );

        assertThat( hotelService.getHotelNames().get( 0 ) ).isEqualTo( "Galadari" );
    }

    @Test
    void testGetHotels(){
        when( hotelRepository.findAll()).thenReturn( hotels );

        assertThat( hotelService.getHotel().size() ).isEqualTo( 1);
        assertThat( hotelService.getHotel().get( 0 ).getHotelName() ).isEqualTo( "Galadari" );
    }
}
