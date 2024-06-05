package com.suntravels.backend.SunTravelsBackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "contractRoom")
public class ContractRoom
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer contractRoomID;
    @ManyToOne
    @JoinColumn(
            name = "contractID",
            referencedColumnName = "contractID")
    private Contract contract;
    @ManyToOne
    @JoinColumn(
            name = "roomTypeName",
            referencedColumnName = "roomTypeName")
    private RoomType roomType;
    private float price;
    private Integer numberOfRooms;
    private Integer maxAdults;

    public ContractRoom()
    {
    }

    public ContractRoom(Contract contract, RoomType roomType, float price, Integer numberOfRooms, Integer maxAdults)
    {
        super();
        this.contract = contract;
        this.roomType = roomType;
        this.price = price;
        this.numberOfRooms = numberOfRooms;
        this.maxAdults = maxAdults;
    }

    public Integer getContractRoomID()
    {
        return contractRoomID;
    }

    public void setContractRoomID( Integer contractRoomID )
    {
        this.contractRoomID = contractRoomID;
    }

    public Contract getContract()
    {
        return contract;
    }

    public void setContract( Contract contract )
    {
        this.contract = contract;
    }

    public RoomType getRoomType()
    {
        return roomType;
    }

    public void setRoomType( RoomType roomType )
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
