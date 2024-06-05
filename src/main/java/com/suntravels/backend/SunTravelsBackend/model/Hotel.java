package com.suntravels.backend.SunTravelsBackend.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "hotel")
public class Hotel
{
    @Id
    private String hotelName;
    private String address;
    private String email;
    private long telephone;

    public Hotel()
    {
    }

    public Hotel(String hotelName, String address, String email, long telephone)
    {
        super();
        this.hotelName = hotelName;
        this.address = address;
        this.email = email;
        this.telephone = telephone;
    }


    public String getHotelName()
    {
        return hotelName;
    }

    public void setHotelName( String hotelName )
    {
        this.hotelName = hotelName;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress( String address )
    {
        this.address = address;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail( String email )
    {
        this.email = email;
    }

    public long getTelephone()
    {
        return telephone;
    }

    public void setTelephone( long telephone )
    {
        this.telephone = telephone;
    }

}
