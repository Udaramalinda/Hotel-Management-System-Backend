package com.suntravels.backend.SunTravelsBackend.DTO;

import java.util.List;

public class SearchRespond
{
    private String hotelName;
    private float finalPrice;
    private List<SearchRoomTypeRespond> rooms;

    public SearchRespond( String hotelName, float finalPrice, List<SearchRoomTypeRespond> rooms )
    {
        this.hotelName = hotelName;
        this.finalPrice = finalPrice;
        this.rooms = rooms;
    }

    public String getHotelName()
    {
        return hotelName;
    }

    public void setHotelName( String hotelName )
    {
        this.hotelName = hotelName;
    }

    public float getFinalPrice()
    {
        return finalPrice;
    }

    public void setFinalPrice( float finalPrice )
    {
        this.finalPrice = finalPrice;
    }

    public List<SearchRoomTypeRespond> getRooms()
    {
        return rooms;
    }

    public void setRooms( List<SearchRoomTypeRespond> rooms )
    {
        this.rooms = rooms;
    }
}
