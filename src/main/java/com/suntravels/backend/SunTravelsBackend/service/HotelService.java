package com.suntravels.backend.SunTravelsBackend.service;

import com.suntravels.backend.SunTravelsBackend.model.Hotel;
import com.suntravels.backend.SunTravelsBackend.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HotelService
{
    private final HotelRepository hotelRepository;

    @Autowired
    public HotelService( HotelRepository hotelRepository){
        this.hotelRepository = hotelRepository;
    }

    public String addNewHotel( Hotel hotel ){
        Optional<Hotel> hotelOptional = hotelRepository.findHotelByHotelName( hotel.getHotelName() );
        if (hotelOptional.isPresent()){
            return "Hotel Already In System";
        }
        hotelRepository.save(hotel);
        return "Hotel Register Successfully";
    }

    public List<String> getHotelNames(){
        List<Hotel> hotelList = hotelRepository.findAll();
        List<String> hotelNames = new ArrayList<>();
        for (Hotel hotel: hotelList)
        {
            hotelNames.add( hotel.getHotelName() );
        }
        return hotelNames;
    }

    public List<Hotel> getHotel(){
        return hotelRepository.findAll();
    }




}
