package com.suntravels.backend.SunTravelsBackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "roomType")
public class RoomType
{

    @Id
    private String roomTypeName;

    public RoomType()
    {
    }

    public RoomType(String roomTypeName)
    {
        super();
        this.roomTypeName = roomTypeName;
    }

    public String getRoomTypeName()
    {
        return roomTypeName;
    }

    public void setRoomTypeName( String roomTypeName )
    {
        this.roomTypeName = roomTypeName;
    }
}
