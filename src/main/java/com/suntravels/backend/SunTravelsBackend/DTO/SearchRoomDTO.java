package com.suntravels.backend.SunTravelsBackend.DTO;

import java.time.LocalDate;
import java.util.List;

public class SearchRoomDTO
{
    private LocalDate checkInDate;
    private Integer numberOfNights;
    private List<GuestDTO> guests;

    public SearchRoomDTO( LocalDate checkInDate, Integer numberOfNights, List<GuestDTO> guests )
    {
        this.checkInDate = checkInDate;
        this.numberOfNights = numberOfNights;
        this.guests = guests;
    }

    public LocalDate getCheckInDate()
    {
        return checkInDate;
    }

    public void setCheckInDate( LocalDate checkInDate )
    {
        this.checkInDate = checkInDate;
    }

    public Integer getNumberOfNights()
    {
        return numberOfNights;
    }

    public void setNumberOfNights( Integer numberOfNights )
    {
        this.numberOfNights = numberOfNights;
    }

    public List<GuestDTO> getGuests()
    {
        return guests;
    }

    public void setGuests( List<GuestDTO> guests )
    {
        this.guests = guests;
    }
}
