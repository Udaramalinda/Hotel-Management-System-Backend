package com.suntravels.backend.SunTravelsBackend.controller;

import com.suntravels.backend.SunTravelsBackend.model.Hotel;
import com.suntravels.backend.SunTravelsBackend.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/hotel/")
public class HotelController
{
    private final HotelService hotelService;

    @Autowired
    public HotelController ( HotelService hotelService){
        this.hotelService = hotelService;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerHotel( @RequestBody Hotel hotel ){
        String message = hotelService.addNewHotel( hotel );
        Map<String, String> response = new HashMap<>();
        response.put("status", message);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/names")
    public List<String> getHotelNames( ){
        return hotelService.getHotelNames();
    }

    @GetMapping("/details")
    public List<Hotel> getHotel( ){
        return hotelService.getHotel();
    }



}
