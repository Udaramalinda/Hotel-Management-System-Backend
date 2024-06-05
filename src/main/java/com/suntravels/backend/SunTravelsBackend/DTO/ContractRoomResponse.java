package com.suntravels.backend.SunTravelsBackend.DTO;

public class ContractRoomResponse
{
    private String roomType;
    private float price;
    private Integer numberOfRooms;
    private Integer maxAdults;

    public ContractRoomResponse( String roomType, float price, Integer numberOfRooms, Integer maxAdults )
    {
        this.roomType = roomType;
        this.price = price;
        this.numberOfRooms = numberOfRooms;
        this.maxAdults = maxAdults;
    }

    public String getRoomType()
    {
        return roomType;
    }

    public void setRoomType( String roomType )
    {
        this.roomType = roomType;
    }

    public float getPrice()
    {
        return price;
    }

    public void setPrice( float price )
    {
        this.price = price;
    }

    public Integer getNumberOfRooms()
    {
        return numberOfRooms;
    }

    public void setNumberOfRooms( Integer numberOfRooms )
    {
        this.numberOfRooms = numberOfRooms;
    }

    public Integer getMaxAdults()
    {
        return maxAdults;
    }

    public void setMaxAdults( Integer maxAdults )
    {
        this.maxAdults = maxAdults;
    }
}
