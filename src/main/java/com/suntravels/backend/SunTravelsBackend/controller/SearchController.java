package com.suntravels.backend.SunTravelsBackend.controller;

import com.suntravels.backend.SunTravelsBackend.DTO.SearchRespond;
import com.suntravels.backend.SunTravelsBackend.DTO.SearchRoomDTO;
import com.suntravels.backend.SunTravelsBackend.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController
{
    private final SearchService searchService;

    @Autowired
    public SearchController( SearchService searchService ){
        this.searchService = searchService;
    }

    @PostMapping()
    public List<SearchRespond> searchRooms( @RequestBody SearchRoomDTO searchRoomDTO ){
        return searchService.searchRooms( searchRoomDTO );
    }
}
