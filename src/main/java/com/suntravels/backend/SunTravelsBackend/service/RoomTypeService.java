package com.suntravels.backend.SunTravelsBackend.service;

import com.suntravels.backend.SunTravelsBackend.model.RoomType;
import com.suntravels.backend.SunTravelsBackend.repository.RoomTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoomTypeService
{
    private final RoomTypeRepository roomTypeRepository;

    @Autowired
    public RoomTypeService( RoomTypeRepository roomTypeRepository){
        this.roomTypeRepository = roomTypeRepository;
    }

    public String addNewRoomType( RoomType roomType ){
        Optional<RoomType> roomTypeOptional = roomTypeRepository.findRoomTypeByRoomTypeName(roomType.getRoomTypeName());
        if (roomTypeOptional.isPresent()){
            return "Room Type Is Already In The System";
        }
        roomTypeRepository.save( roomType );
        return "Room Type Successfully Register In System";
    }

    public List<String> getRoomTypeNames(){
        List<RoomType> roomTypeList = roomTypeRepository.findAll();
        List<String> roomTypeNames = new ArrayList<>();
        for ( RoomType roomType: roomTypeList)
        {
            roomTypeNames.add( roomType.getRoomTypeName() );
        }
        return roomTypeNames;
    }

}
