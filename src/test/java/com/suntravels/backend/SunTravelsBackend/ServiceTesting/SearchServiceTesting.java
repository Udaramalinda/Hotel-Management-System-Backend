package com.suntravels.backend.SunTravelsBackend.ServiceTesting;

import com.suntravels.backend.SunTravelsBackend.DTO.GuestDTO;
import com.suntravels.backend.SunTravelsBackend.DTO.SearchRespond;
import com.suntravels.backend.SunTravelsBackend.DTO.SearchRoomDTO;
import com.suntravels.backend.SunTravelsBackend.model.Contract;
import com.suntravels.backend.SunTravelsBackend.model.ContractRoom;
import com.suntravels.backend.SunTravelsBackend.model.Hotel;
import com.suntravels.backend.SunTravelsBackend.model.Markup;
import com.suntravels.backend.SunTravelsBackend.model.RoomType;
import com.suntravels.backend.SunTravelsBackend.repository.ContractRepository;
import com.suntravels.backend.SunTravelsBackend.repository.ContractRoomRepository;
import com.suntravels.backend.SunTravelsBackend.repository.MarkupRepository;
import com.suntravels.backend.SunTravelsBackend.service.SearchService;
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

import static org.mockito.Mockito.when;

@ExtendWith( MockitoExtension.class )
public class SearchServiceTesting
{
    @Mock
    private ContractRepository contractRepository;
    @Mock
    private ContractRoomRepository contractRoomRepository;
    @Mock
    private MarkupRepository markupRepository;
    @InjectMocks
    private SearchService searchService;

    private Markup markup;
    private Hotel hotel;
    private Contract contract;
    private RoomType roomType1;
    private RoomType roomType2;
    private ContractRoom contractRoom1;
    private ContractRoom contractRoom2;
    private GuestDTO guestDTO1;
    private GuestDTO guestDTO2;
    private GuestDTO guestDTO3;
    private GuestDTO guestDTO4;
    private GuestDTO guestDTO5;
    private GuestDTO guestDTO6;
    private List<GuestDTO> guestDTOS;
    private List<GuestDTO> guestDTOS1;
    private List<GuestDTO> guestDTOS2;
    private List<GuestDTO> guestDTOS3;
    private List<Contract> contracts;
    private List<ContractRoom> contractRoomList1;
    private List<ContractRoom> contractRoomList2;
    private SearchRoomDTO searchRoomDTO1;
    private SearchRoomDTO searchRoomDTO2;
    private SearchRoomDTO searchRoomDTO3;
    private SearchRoomDTO searchRoomDTO4;

    @BeforeEach
    void setUpData(){
        markup = new Markup( 15);
        hotel = new Hotel( "Galadari", "Colombo", "galadari@gmail.com", 1112810426);
        contract = new Contract(
                hotel,
                LocalDate.of( 2024, 1, 1 ),
                LocalDate.of( 2024,1,31 ) );

        contract.setContractID( 1 );

        roomType1 = new RoomType("Luxury");
        roomType2 = new RoomType("Sea View");

        contracts = new ArrayList<>();
        contracts.add( contract );

        contractRoom1 = new ContractRoom(
                contract,
                roomType1,
                50.0f,
                4,
                3 );

        contractRoom2 = new ContractRoom(
                contract,
                roomType2,
                60.0f,
                3,
                4 );

        contractRoomList1 = new ArrayList<>();
        contractRoomList1.add( contractRoom2 );
        contractRoomList1.add( contractRoom1 );

        contractRoomList2 = new ArrayList<>();
        contractRoomList2.add( contractRoom1 );
        contractRoomList2.add( contractRoom2 );

        guestDTO1 = new GuestDTO(3, 2);
        guestDTO2 = new GuestDTO(4,2);

        guestDTOS = new ArrayList<>();
        guestDTOS.add( guestDTO1 );
        guestDTOS.add( guestDTO2 );

        guestDTO3 = new GuestDTO(3,6);
        guestDTOS1 = new ArrayList<>();
        guestDTOS1.add( guestDTO3 );

        guestDTO4 = new GuestDTO(4,5);
        guestDTOS2 = new ArrayList<>();
        guestDTOS2.add( guestDTO4 );

        guestDTO5 = new GuestDTO(3,7);
        guestDTO6 = new GuestDTO(4,2);
        guestDTOS3 = new ArrayList<>();
        guestDTOS3.add( guestDTO5 );
        guestDTOS3.add( guestDTO6 );

        searchRoomDTO1 = new SearchRoomDTO(
                LocalDate.of( 2023, 1, 2 ),
                2,
                guestDTOS );

        searchRoomDTO2 = new SearchRoomDTO(
                LocalDate.of( 2023, 1, 2 ),
                2,
                guestDTOS1 );

        searchRoomDTO3 = new SearchRoomDTO(
                LocalDate.of( 2023, 1, 2 ),
                2,
                guestDTOS2 );

        searchRoomDTO4 = new SearchRoomDTO(
                LocalDate.of( 2023, 1, 2 ),
                2,
                guestDTOS3 );

    }

    @Test
    void testSearchRooms(){
        when( markupRepository.findTopByOrderByMarkupIDDesc()).thenReturn( markup );
        when( contractRepository.findAllByStartDateLessThanEqualAndEndDateGreaterThanEqual( LocalDate.of( 2023,1,2 ), LocalDate.of( 2023,1,4 ) ) ).thenReturn( contracts );
        when( contractRoomRepository.findByContractOrderByMaxAdultsDesc( contract )).thenReturn( contractRoomList1 );
        when( contractRoomRepository.findByContractOrderByMaxAdults( contract ) ).thenReturn( contractRoomList2 );

        List<SearchRespond> searchResponds = searchService.searchRooms( searchRoomDTO1 );
        assertThat( searchResponds.size() ).isEqualTo( 1 );
        assertThat( searchResponds.get( 0 ).getHotelName() ).isEqualTo( hotel.getHotelName() );
        assertThat( searchResponds.get( 0 ).getFinalPrice()).isEqualTo( 897.0f );
    }

    @Test
    void testSearchRoomsOne(){
        when( markupRepository.findTopByOrderByMarkupIDDesc()).thenReturn( markup );
        when( contractRepository.findAllByStartDateLessThanEqualAndEndDateGreaterThanEqual( LocalDate.of( 2023,1,2 ), LocalDate.of( 2023,1,4 ) ) ).thenReturn( contracts );
        when( contractRoomRepository.findByContractOrderByMaxAdultsDesc( contract )).thenReturn( contractRoomList1 );
        when( contractRoomRepository.findByContractOrderByMaxAdults( contract ) ).thenReturn( contractRoomList2 );

        List<SearchRespond> searchResponds = searchService.searchRooms( searchRoomDTO2 );
        assertThat( searchResponds.size() ).isEqualTo( 1 );
        assertThat( searchResponds.get( 0 ).getHotelName() ).isEqualTo( hotel.getHotelName() );
        assertThat( searchResponds.get( 0 ).getFinalPrice()).isEqualTo( 1104.0f );
    }

    @Test
    void testSearchRoomsTwo(){
        when( markupRepository.findTopByOrderByMarkupIDDesc()).thenReturn( markup );
        when( contractRepository.findAllByStartDateLessThanEqualAndEndDateGreaterThanEqual( LocalDate.of( 2023,1,2 ), LocalDate.of( 2023,1,4 ) ) ).thenReturn( contracts );
        when( contractRoomRepository.findByContractOrderByMaxAdultsDesc( contract )).thenReturn( contractRoomList1 );

        List<SearchRespond> searchResponds = searchService.searchRooms( searchRoomDTO3 );
        assertThat( searchResponds ).isEmpty();
    }

    @Test
    void testSearchRoomsThree(){
        when( markupRepository.findTopByOrderByMarkupIDDesc()).thenReturn( markup );
        when( contractRepository.findAllByStartDateLessThanEqualAndEndDateGreaterThanEqual( LocalDate.of( 2023,1,2 ), LocalDate.of( 2023,1,4 ) ) ).thenReturn( contracts );
        when( contractRoomRepository.findByContractOrderByMaxAdultsDesc( contract )).thenReturn( contractRoomList1 );

        List<SearchRespond> searchResponds = searchService.searchRooms( searchRoomDTO4 );
        assertThat( searchResponds ).isEmpty();
    }


}
