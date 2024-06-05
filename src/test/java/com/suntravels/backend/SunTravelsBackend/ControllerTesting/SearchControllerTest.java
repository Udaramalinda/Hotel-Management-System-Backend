package com.suntravels.backend.SunTravelsBackend.ControllerTesting;

import com.suntravels.backend.SunTravelsBackend.DTO.GuestDTO;
import com.suntravels.backend.SunTravelsBackend.DTO.SearchRespond;
import com.suntravels.backend.SunTravelsBackend.DTO.SearchRoomDTO;
import com.suntravels.backend.SunTravelsBackend.controller.SearchController;
import com.suntravels.backend.SunTravelsBackend.service.SearchService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith( MockitoExtension.class )
public class SearchControllerTest
{
    @Mock
    private SearchService searchService;
    @InjectMocks
    private SearchController searchController;

    @Test
    void testSearchRooms(){
        List<GuestDTO> guestDTOS = new ArrayList<>();
        SearchRoomDTO searchRoomDTO = new SearchRoomDTO(
                LocalDate.of( 2023, 12,1 ),
                2,
                guestDTOS
        );
        List<SearchRespond> searchResponds = new ArrayList<>();
        when( searchService.searchRooms( searchRoomDTO )).thenReturn( searchResponds );

        assertThat( searchController.searchRooms( searchRoomDTO )).isEmpty();


    }
}
