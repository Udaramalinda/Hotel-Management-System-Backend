package com.suntravels.backend.SunTravelsBackend.RepositoryTest;

import com.suntravels.backend.SunTravelsBackend.model.Contract;
import com.suntravels.backend.SunTravelsBackend.model.Hotel;
import com.suntravels.backend.SunTravelsBackend.repository.ContractRepository;
import com.suntravels.backend.SunTravelsBackend.repository.HotelRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class ContractRepositoryTest
{
    @Autowired
    private ContractRepository contractRepository;
    @Autowired
    private HotelRepository hotelRepository;

    private Contract contract1;
    private Contract contract2;
    private Contract contract3;
    private Hotel hotel1;
    private Hotel hotel2;

    @BeforeEach
    void setUpData() {

        hotel1 = new Hotel(
                "Galadari",
                "Colombo",
                "galadari@gmail.com",
                1234567890);
        hotelRepository.save( hotel1 );

        hotel2 = new Hotel(
                "Hilton",
                "Colombo",
                "hilton@gmail.com",
                1234567890);
        hotelRepository.save( hotel2 );

        contract1 = new Contract(
                hotel1,
                LocalDate.of( 2023,12,1 ),
                LocalDate.of( 2023,12,31 ) );
        contractRepository.save( contract1 );

        contract2 = new Contract(
                hotel1,
                LocalDate.of( 2024,1,1 ),
                LocalDate.of( 2024,1,31 ) );
        contractRepository.save( contract2 );

        contract3 = new Contract(
                hotel2,
                LocalDate.of( 2023,12,4 ),
                LocalDate.of( 2023,12,29 ) );
        contractRepository.save( contract3 );
    }
    @AfterEach
    void tearDown() {
        contractRepository.deleteAll();
        hotelRepository.deleteAll();
    }
//    @Test
//    void testGetContractByHotel() {
//        Hotel gHotel = hotelRepository.getHotelByHotelName( "Galadari" );
//        List<Contract> contractList1 = contractRepository.getContractByHotel( gHotel );
//        List<Contract> contractList2 = contractRepository.getContractByHotel( hotel2 );
//
//        assertThat( contractList1.size()).isEqualTo( 2 );
//        assertThat( contractList2.size() ).isEqualTo( 1 );
//
//        assertThat( contractList1.get( 0 ).getHotel().getHotelName() ).isEqualTo( "Galadari" );
//        assertThat( contractList2.get( 0 ).getHotel().getHotelName() ).isEqualTo( "Hilton" );
//
//    }
}
