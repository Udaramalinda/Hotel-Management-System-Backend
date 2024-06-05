package com.suntravels.backend.SunTravelsBackend.DTO;

import java.time.LocalDate;
import java.util.List;

public class ContractDTO
{
    private String hotelName;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<ContractRoomDTO> roomDetails;

    public ContractDTO() {}

    public ContractDTO(String hotelName, LocalDate startDate, LocalDate endDate, List<ContractRoomDTO> roomDetails){
        super();
        this.hotelName = hotelName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.roomDetails = roomDetails;
    }

    public String getHotelName()
    {
        return hotelName;
    }

    public void setHotelName( String hotelName )
    {
        this.hotelName = hotelName;
    }

    public LocalDate getStartDate()
    {
        return startDate;
    }

    public void setStartDate( LocalDate startDate )
    {
        this.startDate = startDate;
    }

    public LocalDate getEndDate()
    {
        return endDate;
    }

    public void setEndDate( LocalDate endDate )
    {
        this.endDate = endDate;
    }

    public List<ContractRoomDTO> getRoomDetails()
    {
        return roomDetails;
    }

    public void setRoomDetails( List<ContractRoomDTO> roomDetails )
    {
        this.roomDetails = roomDetails;
    }


}
