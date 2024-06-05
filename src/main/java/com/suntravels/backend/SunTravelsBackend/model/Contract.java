package com.suntravels.backend.SunTravelsBackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "contract")
public class Contract
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer contractID;
    @ManyToOne
    @JoinColumn(
            name = "hotelName",
            referencedColumnName = "hotelName")
    private Hotel hotel;

    private LocalDate startDate;
    private LocalDate endDate;

    public Contract()
    {
    }

    public Contract(Hotel hotel, LocalDate startDate, LocalDate endDate)
    {
        super();
        this.hotel = hotel;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Integer getContractID()
    {
        return contractID;
    }

    public void setContractID( Integer contractID )
    {
        this.contractID = contractID;
    }

    public Hotel getHotel()
    {
        return hotel;
    }

    public void setHotel( Hotel hotel )
    {
        this.hotel = hotel;
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
}