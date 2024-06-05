package com.suntravels.backend.SunTravelsBackend.ControllerTesting;

import com.suntravels.backend.SunTravelsBackend.controller.MarkupController;
import com.suntravels.backend.SunTravelsBackend.model.Markup;
import com.suntravels.backend.SunTravelsBackend.service.MarkupService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith( MockitoExtension.class )
public class MarkupControllerTest
{
    @Mock
    private MarkupService markupService;
    @InjectMocks
    private MarkupController markupController;

    @Test
    void testRegisterMarkup(){
        Markup markup = new Markup(15);
        when( markupService.addNewMarkup( markup )).thenReturn( "Markup Register Successfully" );

        ResponseEntity<Map<String,String>> response = markupController.registerMarkup( markup );

        assertThat( response.getStatusCode().value()).isEqualTo( 200 );
        assertThat( response.getBody().get( "status" ) ).isEqualTo( "Markup Register Successfully" );
    }

    @Test
    void testGetMarkup(){
        float markupValue = 15.0f;
        when( markupService.getMarkup()).thenReturn( markupValue );

        assertThat( markupController.getMarkup() ).isEqualTo( markupValue );
    }
}
