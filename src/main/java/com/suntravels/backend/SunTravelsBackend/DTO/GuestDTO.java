package com.suntravels.backend.SunTravelsBackend.DTO;

public class GuestDTO
{
    private Integer adults;
    private Integer rooms;

    public GuestDTO() {}
    public GuestDTO( Integer adults, Integer rooms){
        this.adults = adults;
        this.rooms = rooms;
    }
    public Integer getAdults() { return adults; }

    public void setAdults( Integer adults )
    {
        this.adults = adults;
    }

    public Integer getRooms()
    {
        return rooms;
    }

    public void setRooms( Integer rooms )
    {
        this.rooms = rooms;
    }


}
