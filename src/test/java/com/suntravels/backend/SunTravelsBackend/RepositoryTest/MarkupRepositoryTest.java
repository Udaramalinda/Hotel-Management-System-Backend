package com.suntravels.backend.SunTravelsBackend.RepositoryTest;

import com.suntravels.backend.SunTravelsBackend.model.Markup;
import com.suntravels.backend.SunTravelsBackend.repository.MarkupRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class MarkupRepositoryTest
{
    @Autowired
    private MarkupRepository markupRepository;

    private Markup markup;

    @BeforeEach
    public void setUpData(){
        markup = new Markup( 12 );
        markupRepository.save( markup );

        markup = new Markup(15 );
        markupRepository.save( markup );
    }

    @Test
    public void testFindTopByOrderByMarkupIDDesc() {

        assertThat ( markupRepository.findTopByOrderByMarkupIDDesc().getMarkupPercentage() ).isEqualTo( 15 );
        assertThat ( markupRepository.findTopByOrderByMarkupIDDesc().getMarkupPercentage() ).isNotEqualTo( 12 );
        assertThat ( markupRepository.findTopByOrderByMarkupIDDesc().getMarkupPercentage() ).isNotEqualTo( 19 );
    }

}
