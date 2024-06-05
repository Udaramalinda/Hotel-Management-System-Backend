package com.suntravels.backend.SunTravelsBackend.repository;

import com.suntravels.backend.SunTravelsBackend.model.Markup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MarkupRepository extends JpaRepository<Markup, Integer>
{
    Markup findTopByOrderByMarkupIDDesc();
}
