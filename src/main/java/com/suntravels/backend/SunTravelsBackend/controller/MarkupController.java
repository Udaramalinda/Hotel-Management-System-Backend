package com.suntravels.backend.SunTravelsBackend.controller;

import com.suntravels.backend.SunTravelsBackend.model.Markup;
import com.suntravels.backend.SunTravelsBackend.service.MarkupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/markup/")
public class MarkupController
{
    private final MarkupService markupService;

    @Autowired
    public MarkupController( MarkupService markupService ){
        this.markupService = markupService;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerMarkup( @RequestBody Markup markup ){
        String message = markupService.addNewMarkup(markup);
        Map<String, String> response = new HashMap<>();
        response.put("status", message);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/value")
    public float getMarkup(){
        return markupService.getMarkup();
    }
}
