package com.suntravels.backend.SunTravelsBackend.ServiceTesting;

import com.suntravels.backend.SunTravelsBackend.model.Markup;
import com.suntravels.backend.SunTravelsBackend.repository.MarkupRepository;
import com.suntravels.backend.SunTravelsBackend.service.MarkupService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith( MockitoExtension.class )
public class MarkupServiceTesting
{
    @Mock
    private MarkupRepository markupRepository;
    @InjectMocks
    private MarkupService markupService;
    private Markup markup;

    @BeforeEach
    void setUpData(){
        markup = new Markup(15);
    }

    @Test
    void testAddNewMarkup(){
        assertThat( markupService.addNewMarkup( markup ) ).isEqualTo( "Markup Register Successfully" );
    }

    @Test
    void testGetMarkup(){
        when( markupRepository.findTopByOrderByMarkupIDDesc() ).thenReturn( markup );

        assertThat( markupService.getMarkup() ).isEqualTo( 15.0f );
    }
}
