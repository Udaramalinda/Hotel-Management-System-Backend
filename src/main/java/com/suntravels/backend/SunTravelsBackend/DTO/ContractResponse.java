package com.suntravels.backend.SunTravelsBackend.DTO;

import java.time.LocalDate;
import java.util.List;

public class ContractResponse
{
    private String hotelName;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<ContractRoomResponse> contractRoomList;

    public ContractResponse( String hotelName, LocalDate startDate, LocalDate endDate, List<ContractRoomResponse> contractRoomList )
    {
        this.hotelName = hotelName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.contractRoomList = contractRoomList;
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

    public List<ContractRoomResponse> getContractRoomList()
    {
        return contractRoomList;
    }

    public void setContractRoomList( List<ContractRoomResponse> contractRoomList )
    {
        this.contractRoomList = contractRoomList;
    }
}
