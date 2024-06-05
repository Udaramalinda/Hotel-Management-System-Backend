package com.suntravels.backend.SunTravelsBackend.controller;

import com.suntravels.backend.SunTravelsBackend.model.RoomType;
import com.suntravels.backend.SunTravelsBackend.service.RoomTypeService;
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
@RequestMapping("/roomtype/")
public class RoomTypeController
{
    private final RoomTypeService roomTypeService;

    @Autowired
    public RoomTypeController(RoomTypeService roomTypeService){
        this.roomTypeService = roomTypeService;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerRoomType( @RequestBody RoomType roomType ){

        String message = roomTypeService.addNewRoomType(roomType);
        Map<String, String> response = new HashMap<>();
        response.put("status", message);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/names")
    public List<String> getRoomTypeNames(){
        return roomTypeService.getRoomTypeNames();
    }
}
