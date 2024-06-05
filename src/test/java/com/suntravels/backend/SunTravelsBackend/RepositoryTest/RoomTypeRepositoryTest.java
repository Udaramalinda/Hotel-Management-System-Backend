package com.suntravels.backend.SunTravelsBackend.RepositoryTest;

import com.suntravels.backend.SunTravelsBackend.model.RoomType;
import com.suntravels.backend.SunTravelsBackend.repository.RoomTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class RoomTypeRepositoryTest
{
    @Autowired
    private RoomTypeRepository roomTypeRepository;

    private RoomType roomType;

    @BeforeEach
    public void setUpData() {

        roomType = new RoomType( "Luxury");
        roomTypeRepository.save( roomType );

    }

    @Test
    public void testFindRoomTypeByRoomTypeName(){

        String roomTypeName1 = "Luxury";
        String roomTypeName2 = "Sea View";

        assertThat( roomTypeRepository.findRoomTypeByRoomTypeName( roomTypeName1 )).isPresent();
        assertThat( roomTypeRepository.findRoomTypeByRoomTypeName( roomTypeName1 ).get().getRoomTypeName() ).isEqualTo( roomType.getRoomTypeName() );

        assertThat( roomTypeRepository.findRoomTypeByRoomTypeName( roomTypeName2 ) ).isNotPresent();
    }

    @Test
    public void testGetRoomTypeByRoomTypeName() {

        String roomTypeName1 = "Luxury";

        assertThat( roomTypeRepository.getRoomTypeByRoomTypeName( roomTypeName1 ).getRoomTypeName() ).isEqualTo( roomType.getRoomTypeName() );
    }




}
