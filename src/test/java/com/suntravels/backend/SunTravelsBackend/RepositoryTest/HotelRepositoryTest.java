package com.suntravels.backend.SunTravelsBackend.RepositoryTest;

import com.suntravels.backend.SunTravelsBackend.model.Hotel;
import com.suntravels.backend.SunTravelsBackend.repository.HotelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class HotelRepositoryTest
{
    @Autowired
    private HotelRepository hotelRepository;

    private Hotel hotel;

    @BeforeEach
    public void setUpData() {
        hotel = new Hotel(
                "Galadari",
                "Colombo",
                "galadari@gmail.com",
                1234567890 );
        hotelRepository.save( hotel );
    }

    @Test
    public void testFindHotelByHotelName(){

        String hotelName1 = "Galadari";
        String hotelName2 = "Hilton";

        assertThat( hotelRepository.findHotelByHotelName( hotelName1 )).isPresent();
        assertThat( hotelRepository.findHotelByHotelName( hotelName1 ).get().getHotelName() ).isEqualTo( hotel.getHotelName() );

        assertThat( hotelRepository.findHotelByHotelName( hotelName2 ) ).isNotPresent();
    }

    @Test
    public void testGetHotelByHotelName() {
        String hotelName1 = "Galadari";

        assertThat( hotelRepository.getHotelByHotelName( hotelName1 ).getHotelName()).isEqualTo( hotel.getHotelName() );

    }
}
