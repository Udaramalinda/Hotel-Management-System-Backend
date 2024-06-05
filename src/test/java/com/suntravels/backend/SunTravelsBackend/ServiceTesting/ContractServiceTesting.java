package com.suntravels.backend.SunTravelsBackend.ServiceTesting;

import com.suntravels.backend.SunTravelsBackend.DTO.ContractDTO;
import com.suntravels.backend.SunTravelsBackend.DTO.ContractResponse;
import com.suntravels.backend.SunTravelsBackend.DTO.ContractRoomDTO;
import com.suntravels.backend.SunTravelsBackend.model.Contract;
import com.suntravels.backend.SunTravelsBackend.model.ContractRoom;
import com.suntravels.backend.SunTravelsBackend.model.Hotel;
import com.suntravels.backend.SunTravelsBackend.model.RoomType;
import com.suntravels.backend.SunTravelsBackend.repository.ContractRepository;
import com.suntravels.backend.SunTravelsBackend.repository.ContractRoomRepository;
import com.suntravels.backend.SunTravelsBackend.repository.HotelRepository;
import com.suntravels.backend.SunTravelsBackend.repository.RoomTypeRepository;
import com.suntravels.backend.SunTravelsBackend.service.ContractService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith( MockitoExtension.class )
public class ContractServiceTesting
{
    @Mock
    private ContractRepository contractRepository;
    @Mock
    private ContractRoomRepository contractRoomRepository;
    @Mock
    private HotelRepository hotelRepository;
    @Mock
    private RoomTypeRepository roomTypeRepository;
    @InjectMocks
    private ContractService contractService;

    private ContractDTO contractDTO;
    private ContractDTO contractDTO1;
    private ContractRoomDTO contractRoomDTO1;
    private ContractRoomDTO contractRoomDTO2;
    private ContractRoom contractRoom;
    private List<ContractRoom> contractRooms;
    private Hotel hotel;
    private RoomType roomType1;
    private RoomType roomType2;
    private Contract contract;
    private List<Contract> contracts;

    @BeforeEach
    void setUpData() {
        hotel = new Hotel( "Galadari", "Colombo", "galadari@gmail.com", 1112810426);
        roomType1 = new RoomType("Luxury");
        roomType2 = new RoomType("Sea View");

        contractRoomDTO1 = new ContractRoomDTO( "Luxury", 50.0f, 3, 3);
        contractRoomDTO2 = new ContractRoomDTO("Sea View", 60.0f, 4,4);
        List<ContractRoomDTO> contractRoomDTOS = new ArrayList<>();
        contractRoomDTOS.add( contractRoomDTO1 );
        contractRoomDTOS.add( contractRoomDTO2 );

        contractDTO = new ContractDTO(
                "Galadari",
                LocalDate.of( 2024, 1, 1 ),
                LocalDate.of( 2024,1,31 ),
                contractRoomDTOS);

        contractDTO1 = new ContractDTO(
                "Galadari",
                LocalDate.of( 2024, 2, 1 ),
                LocalDate.of( 2024,3,1 ),
                contractRoomDTOS);

        contract = new Contract(
                hotel,
                LocalDate.of( 2024, 1, 1 ),
                LocalDate.of( 2024,1,31 ) );

        contractRoom = new ContractRoom(
                contract,
                roomType1,
                50.0f,
                3,
                3
        );

        contractRooms = new ArrayList<>();
        contractRooms.add( contractRoom );

        contracts = new ArrayList<>();

        contracts.add( contract );

    }

    @Test
    void testAddNewContract(){
        when(hotelRepository.getHotelByHotelName( contractDTO.getHotelName() )).thenReturn( hotel );
        when( contractRepository.getContractByHotel( any(Hotel.class) ) ).thenReturn( new ArrayList<Contract>() );
        when( roomTypeRepository.getRoomTypeByRoomTypeName( "Luxury" )).thenReturn( roomType1 );
        when( roomTypeRepository.getRoomTypeByRoomTypeName( "Sea View" ) ).thenReturn( roomType2 );

        String result = contractService.addNewContract( contractDTO );
        assertThat( result ).isEqualTo( "Contract Save Successfully" );

    }

    @Test
    void testAddNewContractOne(){
        when(hotelRepository.getHotelByHotelName( contractDTO.getHotelName() )).thenReturn( hotel );
        when( contractRepository.getContractByHotel( any(Hotel.class) ) ).thenReturn( contracts );
        when( roomTypeRepository.getRoomTypeByRoomTypeName( "Luxury" )).thenReturn( roomType1 );
        when( roomTypeRepository.getRoomTypeByRoomTypeName( "Sea View" ) ).thenReturn( roomType2 );

        String result = contractService.addNewContract( contractDTO1 );
        assertThat( result ).isEqualTo( "Contract Save Successfully" );
    }

    @Test
    void testAddNewContractFailure(){

        when(hotelRepository.getHotelByHotelName( contractDTO.getHotelName() )).thenReturn( hotel );
        when( contractRepository.getContractByHotel( any(Hotel.class) ) ).thenReturn( contracts );

        String result = contractService.addNewContract( contractDTO );
        assertThat( result ).isEqualTo( "Hotel Has Contract On That Time Period" );
    }

    @Test
    void testGetContract(){
        when( hotelRepository.getHotelByHotelName( "Galadari" ) ).thenReturn( hotel );
        when(contractRepository.getContractByHotel( hotel )).thenReturn( contracts );
        when(contractRoomRepository.getContractRoomByContract( contract )).thenReturn( contractRooms );

        List<ContractResponse> contractResponse = contractService.getContract( "Galadari" );
        assertThat( contractResponse.get( 0 ).getHotelName() ).isEqualTo( "Galadari" );
        assertThat( contractResponse.size() ).isEqualTo( 1 );
        assertThat( contractResponse.get( 0 ).getContractRoomList().get( 0 ).getRoomType() ).isEqualTo( "Luxury" );

    }


}
