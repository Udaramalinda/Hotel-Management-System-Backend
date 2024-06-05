package com.suntravels.backend.SunTravelsBackend.repository;

import com.suntravels.backend.SunTravelsBackend.model.Contract;
import com.suntravels.backend.SunTravelsBackend.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Integer>
{
    List<Contract> getContractByHotel( Hotel hotel );
    List<Contract> findAllByStartDateLessThanEqualAndEndDateGreaterThanEqual( LocalDate checkInDate, LocalDate checkOutDate);
}
