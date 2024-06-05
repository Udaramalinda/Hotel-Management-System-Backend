package com.suntravels.backend.SunTravelsBackend.repository;

import com.suntravels.backend.SunTravelsBackend.model.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomTypeRepository extends JpaRepository<RoomType, Integer>
{
    Optional<RoomType> findRoomTypeByRoomTypeName( String roomTypeName);

    RoomType getRoomTypeByRoomTypeName(String roomTypeName);
}
