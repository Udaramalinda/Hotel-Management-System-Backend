package com.suntravels.backend.SunTravelsBackend.DTO;

public class SearchRoomTypeRespond
{
    private String roomType;
    private Integer numberOfRooms;
    private Integer visitAdults;
    private Integer maxAdults;
    private float price;

    public SearchRoomTypeRespond( String roomType, Integer numberOfRooms, Integer visitAdults, Integer maxAdults, float price )
    {
        this.roomType = roomType;
        this.numberOfRooms = numberOfRooms;
        this.visitAdults = visitAdults;
        this.maxAdults = maxAdults;
        this.price = price;
    }

    public String getRoomType()
    {
        return roomType;
    }

    public void setRoomType( String roomType )
    {
        this.roomType = roomType;
    }

    public Integer getNumberOfRooms()
    {
        return numberOfRooms;
    }

    public void setNumberOfRooms( Integer numberOfRooms )
    {
        this.numberOfRooms = numberOfRooms;
    }

    public Integer getVisitAdults()
    {
        return visitAdults;
    }

    public void setVisitAdults( Integer visitAdults )
    {
        this.visitAdults = visitAdults;
    }

    public Integer getMaxAdults()
    {
        return maxAdults;
    }

    public void setMaxAdults( Integer maxAdults )
    {
        this.maxAdults = maxAdults;
    }

    public float getPrice()
    {
        return price;
    }

    public void setPrice( float price )
    {
        this.price = price;
    }
}
