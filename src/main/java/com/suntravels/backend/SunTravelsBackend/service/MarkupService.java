package com.suntravels.backend.SunTravelsBackend.service;

import com.suntravels.backend.SunTravelsBackend.model.Markup;
import com.suntravels.backend.SunTravelsBackend.repository.MarkupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MarkupService
{
    private final MarkupRepository markupRepository;

    @Autowired
    public MarkupService( MarkupRepository markupRepository){
        this.markupRepository = markupRepository;
    }

    public String addNewMarkup( Markup markup ){

        markupRepository.save( markup );
        return "Markup Register Successfully";
    }

    public float getMarkup(){

        Markup markup = markupRepository.findTopByOrderByMarkupIDDesc();
        return markup.getMarkupPercentage();
    }


}
