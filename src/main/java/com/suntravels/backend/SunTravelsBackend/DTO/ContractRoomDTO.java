package com.suntravels.backend.SunTravelsBackend.DTO;

public class ContractRoomDTO
{
    private String roomTypeName;
    private float price;
    private Integer numOfRooms;
    private Integer maxAdults;

    public String getRoomTypeName()
    {
        return roomTypeName;
    }

    public void setRoomTypeName( String roomTypeName )
    {
        this.roomTypeName = roomTypeName;
    }

    public float getPrice()
    {
        return price;
    }

    public void setPrice( float price )
    {
        this.price = price;
    }

    public Integer getNumOfRooms()
    {
        return numOfRooms;
    }

    public void setNumOfRooms( Integer numOfRooms )
    {
        this.numOfRooms = numOfRooms;
    }

    public Integer getMaxAdults()
    {
        return maxAdults;
    }

    public void setMaxAdults( Integer maxAdults )
    {
        this.maxAdults = maxAdults;
    }

    public ContractRoomDTO() {}

    public ContractRoomDTO( String roomTypeName, float price, Integer numOfRooms, Integer maxAdults){
        this.roomTypeName = roomTypeName;
        this.price = price;
        this.numOfRooms = numOfRooms;
        this.maxAdults = maxAdults;
    }
}
