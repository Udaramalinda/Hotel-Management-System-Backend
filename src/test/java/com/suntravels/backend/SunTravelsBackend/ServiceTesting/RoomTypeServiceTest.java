package com.suntravels.backend.SunTravelsBackend.ServiceTesting;

import com.suntravels.backend.SunTravelsBackend.model.RoomType;
import com.suntravels.backend.SunTravelsBackend.repository.RoomTypeRepository;
import com.suntravels.backend.SunTravelsBackend.service.RoomTypeService;
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
public class RoomTypeServiceTest
{
    @Mock
    private RoomTypeRepository roomTypeRepository;
    @InjectMocks
    private RoomTypeService roomTypeService;

    private RoomType roomType;
    private List<RoomType> roomTypes;

    @BeforeEach
    void setUpData(){
        roomType = new RoomType("Luxury");
        roomTypes = new ArrayList<>();
        roomTypes.add( roomType );
    }

    @Test
    void testAddNewRoomType(){
        when( roomTypeRepository.findRoomTypeByRoomTypeName( "Luxury" ) ).thenReturn( Optional.of( roomType ) );

        assertThat( roomTypeService.addNewRoomType( roomType ) ).isEqualTo( "Room Type Is Already In The System" );
    }

    @Test
    void testAddNewRoomTypeFailed(){
        when( roomTypeRepository.findRoomTypeByRoomTypeName( "Luxury" ) ).thenReturn( Optional.empty() );

        assertThat( roomTypeService.addNewRoomType( roomType ) ).isEqualTo( "Room Type Successfully Register In System" );
    }

    @Test
    void testGetRoomTypeNames(){
        when(roomTypeRepository.findAll()).thenReturn( roomTypes );

        assertThat( roomTypeService.getRoomTypeNames().size() ).isEqualTo( 1 );
        assertThat( roomTypeService.getRoomTypeNames().get( 0 ) ).isEqualTo( "Luxury" );
    }

}
